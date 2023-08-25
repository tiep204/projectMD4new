package ra.model.serviceImpl;

import org.springframework.stereotype.Service;
import ra.model.dao.CategoryDAO;
import ra.model.daoImpl.CategoryDaoImpl;
import ra.model.entity.Category;
import ra.model.service.CategoryService;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO = new CategoryDaoImpl();

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public void saveOfUpdate(Category category) {
        categoryDAO.saveOfUpdate(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDAO.delete(id);
    }

    @Override
    public Category getById(Integer id) {
        return categoryDAO.getById(id);
    }

    @Override
    public List<Category> searchByName(String name) {
        return categoryDAO.searchByName(name);
    }
}