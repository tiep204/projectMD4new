package ra.model.dao;

import ra.model.entity.Category;

import java.util.List;

public interface CategoryDAO extends ShopDAO<Category,Integer> {
    List<Category> searchByName(String name);
}