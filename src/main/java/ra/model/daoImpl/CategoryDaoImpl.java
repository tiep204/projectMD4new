package ra.model.daoImpl;

import org.springframework.stereotype.Repository;
import ra.model.dao.CategoryDAO;
import ra.model.entity.Category;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDaoImpl implements CategoryDAO {
    @Override
    public List<Category> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> list = null;
        list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAll()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCatalogId(rs.getInt("catalog_id"));
                category.setCatalogName(rs.getString("catalog_name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public void saveOfUpdate(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (category.getCatalogId()==0){
                //crewate
                callSt = conn.prepareCall("{call proc_insertCategory(?)}");
                callSt.setString(1,category.getCatalogName());
            }else {
                //update
                callSt = conn.prepareCall("{call proc_updateCategory(?,?,?)}");
                callSt.setInt(1,category.getCatalogId());
                callSt.setString(2,category.getCatalogName());
                callSt.setBoolean(3,category.isStatus());
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
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn =ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_changeStatusCategory(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
    }

    @Override
    public Category getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Category category = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findById(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            category = new Category();
            while (rs.next()){
                category.setCatalogId(rs.getInt("catalog_id"));
                category.setCatalogName(rs.getString("catalog_name"));
                category.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return category;
    }

    @Override
    public List<Category> searchByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> categoryList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findByCatalogName(?)}");
            categoryList = new ArrayList<>();
            callSt.setString(1,name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCatalogId(rs.getInt("catalog_id"));
                category.setCatalogName(rs.getString("catalog_name"));
                category.setStatus(rs.getBoolean("status"));
                categoryList.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return categoryList;
    }
}
