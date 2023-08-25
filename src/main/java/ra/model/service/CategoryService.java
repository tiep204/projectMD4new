package ra.model.service;

import ra.model.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category,Integer>{
    List<Category> searchByName(String name);
}
