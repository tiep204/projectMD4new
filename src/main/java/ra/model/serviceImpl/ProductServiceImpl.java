package ra.model.serviceImpl;

import org.springframework.stereotype.Service;
import ra.model.dao.ProductDAO;
import ra.model.daoImpl.ProductDaoImpl;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.model.service.ProductService;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO = new ProductDaoImpl();
    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public void saveOfUpdate(Product product) {
        productDAO.saveOfUpdate(product);
    }

    @Override
    public void delete(Integer id) {
        productDAO.delete(id);
    }

    @Override
    public Product getById(Integer id) {
        return productDAO.getById(id);
    }
}