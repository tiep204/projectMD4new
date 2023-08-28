package ra.model.daoImpl;

import ra.model.dao.OrderDAO;
import ra.model.entity.Orders;
import ra.model.entity.Product;
import ra.model.service.UserService;
import ra.model.serviceImpl.UserServiceImpl;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class OrderDaoImpl implements OrderDAO {
    private UserService userService = new UserServiceImpl();
    @Override
    public List<Orders> getAll() {
        return null;
    }

    @Override
    public void saveOfUpdate(Orders orders) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (orders.getOrderId()==0){
                //crewate
                callSt = conn.prepareCall("{call proc_saveOrderss(?,?,?)}");
                callSt.setInt(1,orders.getUser().getUserId());
                callSt.setFloat(2,  orders.getTotalPrice());
                callSt.setString(3,orders.getNote());

            }else {
                callSt = conn.prepareCall("{call proc_UpdateOrder(?,?,?,?,?)}");
                callSt.setInt(1,orders.getOrderId());
                callSt.setInt(2,orders.getUser().getUserId());
                callSt.setFloat(3, orders.getTotalPrice());
                callSt.setInt(4,orders.getStatus());
                callSt.setString(5,orders.getNote());
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
    public Orders getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Orders orders = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findByOrderId(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                orders = new Orders();
                orders.setOrderId(rs.getInt("order_id"));
                orders.setUser(userService.getById(rs.getInt("user_id")));
                orders.setCreateOrder(rs.getDate("order_at"));
                orders.setTotalPrice(rs.getInt("total_price"));
                orders.setStatus(rs.getInt("status"));
                orders.setNote(rs.getString("note"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return orders;
    }


    @Override
    public Orders searchOrderByUserIdAndStatus(int userId){
        Connection conn = null;
        CallableStatement callSt = null;
        Orders orders = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_searchOrderByUserIdAndStatus(?)}");
            callSt.setInt(1,userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                orders = new Orders();
                orders.setOrderId(rs.getInt("order_id"));
                orders.setUser(userService.getById(rs.getInt("user_id")));
                orders.setCreateOrder(rs.getDate("order_at"));
                orders.setTotalPrice(rs.getInt("total_price"));
                orders.setStatus(rs.getInt("status"));
                orders.setNote(rs.getString("note"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return orders;
    }
}
