package ra.model.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.dao.ProductDAO;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.model.service.CategoryService;
import ra.model.serviceImpl.CategoryServiceImpl;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDAO {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public List<Product> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> productList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                /*product.setCategory(categoryService.getById(rs.getInt("catalog_id")));*/
                product.setCategory(rs.getInt("catalog_id"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
                product.setImage(rs.getString("image"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreateProduct(rs.getDate("created_at"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return productList;
    }

    @Override
    public void saveOfUpdate(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            if (product.getProductId() == 0) {
                callSt = conn.prepareCall("{call proc_insertProduct(?,?,?,?,?,?,?)}");
                callSt.setString(1, product.getProductName());
                callSt.setInt(2, product.getCategory());
                callSt.setString(3, product.getDescription());
                callSt.setFloat(4, product.getPrice());
                callSt.setString(5, product.getImage());
                callSt.setInt(6, product.getStock());
                callSt.registerOutParameter(7, Types.INTEGER);
                callSt.execute();

                int productId = callSt.getInt(7);
                for (String imgLink : product.getListImage()) {
                    CallableStatement callSt2 = conn.prepareCall("{call proc_insertImage(?,?)}");
                    callSt2.setString(1, imgLink);
                    callSt2.setInt(2, productId);
                    callSt2.executeUpdate();
                    callSt2.close();
                }

            } else {
                callSt = conn.prepareCall("{call proc_updateProduct(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, product.getProductId());
                callSt.setString(2, product.getProductName());
                callSt.setInt(3, product.getCategory());
                callSt.setString(4, product.getDescription());
                callSt.setFloat(5, product.getPrice());
                callSt.setString(6, product.getImage());
                callSt.setInt(7, product.getStock());
                callSt.setBoolean(8, product.isStatus());

                CallableStatement callableStatement = conn.prepareCall("{call proc_deleteimage(?)}");
                callableStatement.setInt(1, product.getProductId());
                callableStatement.executeUpdate();
                callableStatement.close();

                for (String imgLink : product.getListImage()) {
                    CallableStatement callSt2 = conn.prepareCall("{call proc_insertimage(?,?)}");
                    callSt2.setString(1, imgLink);
                    callSt2.setInt(2, product.getProductId());
                    callSt2.executeUpdate();
                    callSt2.close();
                }
                callSt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_changeStatusProductById(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public Product getById(Integer id) {
        Product pr = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findProductById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            pr = new Product();
            if (rs.next()) {
                pr.setProductId(rs.getInt("product_id"));
                pr.setProductName(rs.getString("product_name"));
                pr.setCategory(rs.getInt("catalog_id"));
                pr.setDescription(rs.getString("description"));
                pr.setPrice(rs.getFloat("price"));
                pr.setImage(rs.getString("image"));
                pr.setStock(rs.getInt("stock"));
                pr.setStatus(rs.getBoolean("status"));
                pr.setCreateProduct(rs.getDate("created_at"));
            }
            CallableStatement callSt2 = conn.prepareCall("{call proc_getimageById(?)}");
            callSt2.setInt(1, id);
            ResultSet rs2 = callSt2.executeQuery();
            while (rs2.next()) {
                pr.getListImage().add(rs2.getString("imageURL"));
            }
            callSt2.close();

        } catch (SQLException ex1) {
            ex1.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return pr;
    }

    @Override
    public List<Product> searchByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> productList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_findByProductName(?)}");
            productList = new ArrayList<>();
            callSt.setString(1,name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategory(rs.getInt("catalog_id"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
                product.setImage(rs.getString("image"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                product.setCreateProduct(rs.getDate("created_at"));
                productList.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return productList;
    }
}
