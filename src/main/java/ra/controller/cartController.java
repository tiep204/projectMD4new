package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.*;
import ra.model.service.OrderDetailService;
import ra.model.service.OrderService;
import ra.model.service.ProductService;
import sun.awt.image.GifImageDecoder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartController")
public class cartController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;

    @PostMapping("/addCart")
    public String addCart(@RequestParam int productId, @RequestParam int quantity, HttpSession session) {
        Product productAdd = productService.getById(productId);
        Orders orders = (Orders) session.getAttribute("order");
        User user = (User) session.getAttribute("userLogin");
        boolean check = false;
        OrderDetail orderDetail =  new OrderDetail();
        for (OrderDetail od : orders.getOrderDetails()) {
                if (od.getProductId().getProductId()==productId){
                    orderDetail = od;
                    orderDetail.setQuantity(od.getQuantity()+quantity);
                    check= true;
                    break;
                }
        }
        if (!check){
            orderDetail.setQuantity(quantity);
            orderDetail.setOrderId(orders);
            orderDetail.setProductId(productAdd);
        }
        orderDetailService.saveOfUpdate(orderDetail);
        orders = orderService.searchOrderByUserIdAndStatus(user.getUserId());
        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orders.getOrderId());
        orders.setOrderDetails(orderDetailList);
        session.setAttribute("order",orders);
        return "redirect:/cartController/cart";
    }


    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Orders orders = (Orders) session.getAttribute("order");

        if (orders != null && !orders.getOrderDetails().isEmpty()) {
            List<OrderDetail> orderDetails = orders.getOrderDetails();
            model.addAttribute("orderDetails", orderDetails);
            System.out.println(orders.getTotalPrice());
            // Có thể tính toán tổng số tiền, tổng số lượng sản phẩm, và các thông tin khác ở đây
            // Tính tổng giá tiền từ danh sách orderDetails
            float totalPrice = 0;
            for (OrderDetail orderDetail : orderDetails) {
                totalPrice += orderDetail.getOrderId().getTotalPrice();
            }
            orders.setTotalPrice(totalPrice);  // Cập nhật tổng giá tiền trong đối tượng Orders
            System.out.println(totalPrice);    // In tổng giá tiền để kiểm tra
        }

        return "user/cart";
    }

    public static float calTotalAmount(List<Cart> listCart) {
        float totalAmount = 0;
        for (Cart cart : listCart) {
            totalAmount += cart.getQuantity() * cart.getProduct().getPrice();
        }
        return totalAmount;
    }

    @PostMapping("/updateCart")
    public String updateCart(@RequestParam int productId, @RequestParam int quantity, HttpSession session) {
        Orders orders = (Orders) session.getAttribute("order");

        if (orders != null) {
            for (OrderDetail od : orders.getOrderDetails()) {
                if (od.getProductId().getProductId() == productId) {
                    int oldQuantity = od.getQuantity();
                    od.setQuantity(quantity);
                    orderDetailService.saveOfUpdate(od);

                    // Cập nhật lại thông tin tổng tiền và tổng số lượng sản phẩm
                    orders.setTotalPrice(orders.getTotalPrice() + (od.getProductId().getPrice() * (quantity - oldQuantity)));
                    break;
                }
            }
            session.setAttribute("order", orders);
        }

        return "redirect:/cartController/cart";
    }

    @GetMapping("/removeFromCart")
    public String removeFromCart(@RequestParam int productId, HttpSession session) {
        Orders orders = (Orders) session.getAttribute("order");
        if (orders != null) {
            List<OrderDetail> orderDetails = orders.getOrderDetails();
            orderDetails.removeIf(od -> od.getProductId().getProductId() == productId);
            // Cập nhật thông tin giỏ hàng
            orders.setOrderDetails(orderDetails);
            // Cập nhật lại thông tin tổng tiền
            orders.setTotalPrice(calculateTotalPrice(orderDetails));

            // Cập nhật lại session
            session.setAttribute("order", orders);
        }
        return "redirect:/cartController/cart";
    }



    private float calculateTotalPrice(List<OrderDetail> orderDetails) {
        float total = 0;
        for (OrderDetail od : orderDetails) {
            total += od.getProductId().getPrice() * od.getQuantity();
        }
        return total;
    }

    private int calculateTotalQuantity(List<OrderDetail> orderDetails) {
        int total = 0;
        for (OrderDetail od : orderDetails) {
            total += od.getQuantity();
        }
        return total;
    }

    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam int productId, HttpSession session) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getProduct().getProductId() == productId) {
                listCart.remove(i);
            }
        }
        session.setAttribute("listCart", listCart);
        session.setAttribute("totalAmount", calTotalAmount(listCart));
        return null;
    }
}
