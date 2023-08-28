package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.dto.UserLoginDTO;
import ra.dto.UserRegisterDTO;
import ra.model.entity.*;
import ra.model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/", "/homeController"})
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("user/login", "userLogin", new UserLoginDTO());
    }

    @PostMapping("/loginn")
    public String handleLogin(@ModelAttribute("userLogin") UserLoginDTO formLoginDto, HttpSession session, BindingResult errors) {
        User user = formLoginDto.checkValidate(errors, userService);
        if (user.getRole() == 0) {
            session.setAttribute("userLogin", user);
            return "redirect:catalogController/index";
        } else {
            if (user.isStatus() == true) {
                Orders orders = orderService.searchOrderByUserIdAndStatus(user.getUserId());
                if (orders == null) {
                    orders = new Orders();
                    orders.setStatus(0);
                    orders.setUser(user);
                    orderService.saveOfUpdate(orders);
                    orders = orderService.searchOrderByUserIdAndStatus(user.getUserId());
                }
                List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orders.getOrderId());
                orders.setOrderDetails(orderDetailList);
                session.setAttribute("order", orders);
                session.setAttribute("userLogin", user);
                System.out.println(user.getFirstName());
                return "redirect:/index";
            } else {
                return "user/login";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userLogin");
        request.getSession().removeAttribute("order");
        return "redirect:/";
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session, @ModelAttribute("messageSuccess") String message) {
        if (message.length() != 0) {
            model.addAttribute("message", message);
        }
        List<Product> productList = productService.getAllProductStatus();
        model.addAttribute("productList", productList);
        return "user/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("listCatalog", categoryList);
        model.addAttribute("product", product);
        return "user/product_list";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userRe, Model model, @RequestParam("confirmPassword") String confirmPassword) {
        Boolean check = false;
        if (userRe.getPassword().equals(confirmPassword)) {
            check = userService.register(userRe);
        }
        if (check) {
            return "redirect:/login";
        } else {
            return "admin/error";
        }
    }


    @GetMapping("/about")
    public String abount() {
        return "user/about";
    }

    @GetMapping("/cart")
    public String cart() {
        return "user/cart";
    }

    @GetMapping("/Catagori")
    public String catagori() {
        return "user/Catagori";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "user/checkout";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "user/confirmation";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user/contact";
    }


    @GetMapping("/single-product")
    public String product() {
        return "user/product_list";
    }

}