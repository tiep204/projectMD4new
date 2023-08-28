package ra.model.dao;

import ra.model.entity.Product;

import java.util.List;

public interface ProductDAO extends ShopDAO<Product,Integer> {
    List<Product> searchByName(String name);
    List<Product> getAllProductStatus();
}
