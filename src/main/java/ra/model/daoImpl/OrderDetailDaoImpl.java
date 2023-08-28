package ra.model.daoImpl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import ra.model.dao.OrderDetailDAO;
import ra.model.entity.Category;
import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;
import ra.model.entity.Product;
import ra.model.service.OrderService;
import ra.model.service.ProductService;
import ra.model.service.UserService;
import ra.model.serviceImpl.OrdersServiceImpl;
import ra.model.serviceImpl.ProductServiceImpl;
import ra.model.serviceImpl.UserServiceImpl;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDAO {
    private OrderService orderService = new OrdersServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public List<OrderDetail> getAll() {
        return null;
    }

    @Override
    public void saveOfUpdate(OrderDetail orderDetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (orderDetail.getOrDId() == 0){
                //crewate
                callSt = conn.prepareCall("{call proc_saveOrderDetail(?,?,?)}");
                callSt.setInt(1,orderDetail.getOrderId().getOrderId());
                callSt.setInt(2,orderDetail.getProductId().getProductId());
                callSt.setInt(3,orderDetail.getQuantity());
            }else {
                //update
                callSt = conn.prepareCall("{call proc_UpdateOrderDetail(?,?,?,?)}");
                callSt.setInt(1,orderDetail.getOrDId());
                callSt.setInt(2,orderDetail.getOrderId().getOrderId());
                callSt.setInt(3,orderDetail.getProductId().getProductId());
                callSt.setInt(4,orderDetail.getQuantity());
            }
            callSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public OrderDetail getById(Integer id) {
        return null;
    }

    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail ordersdt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findByOrderId(?)}");
            callSt.setInt(1, orderId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ordersdt = new OrderDetail();
                ordersdt.setOrDId(rs.getInt("order_detail_id"));
                ordersdt.setOrderId(orderService.getById(rs.getInt("order_id")));
                ordersdt.setProductId(productService.getById(rs.getInt("product_id")));
                ordersdt.setQuantity(rs.getInt("quantity"));
                orderDetails.add(ordersdt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return orderDetails;
    }
}
