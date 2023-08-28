package ra.model.service;

import ra.model.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product,Integer> {
    List<Product> searchByName(String name);
    List<Product> getAllProductStatus();

}
