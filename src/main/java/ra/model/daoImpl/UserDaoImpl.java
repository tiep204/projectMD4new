package ra.model.daoImpl;

import org.springframework.stereotype.Repository;
import ra.model.dao.UserDAO;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDAO{
    @Override
    public List<User> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> userList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllUser()}");
            ResultSet rs = callSt.executeQuery();
            userList = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getBoolean("gender"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getBoolean("status"));
                user.setAvatar(rs.getString("avatar"));
                user.setCreatedAt(rs.getDate("created_at"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return userList;
    }

    @Override
    public void saveOfUpdate(User user) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public Boolean register(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_register(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1,user.getUsername());
            callSt.setString(2,user.getEmail());
            callSt.setString(3, user.getFirstName());
            callSt.setString(4, user.getLastName());
            callSt.setString(5, user.getPassword());
            callSt.setString(6, user.getPhoneNumber());
            callSt.setBoolean(7,user.isGender());
            callSt.setDate(8,new Date(user.getBirthDate().getTime()));
            callSt.setInt(9,user.getRole());
            callSt.setDate(10,new Date(user.getCreatedAt().getTime()));
            callSt.executeUpdate();
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean updateUserBlock(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_blockUser(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean updateUserUnlock(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_unLockUser(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public List<User> searchByName(String name) {
        return null;
    }

    @Override
    public User login(String userName, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_login(?,?)}");
            callSt.setString(1,userName);
            callSt.setString(2,password);
            ResultSet rs = callSt.executeQuery();
            user = new User();
            while (rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setAddress(rs.getString("address"));
                user.setGender(rs.getBoolean("gender"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getBoolean("status"));
                user.setAvatar(rs.getString("avatar"));
                user.setCreatedAt(rs.getDate("created_at"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return user;
    }
}
