package ra.model.dao;

import ra.model.entity.Category;

import java.util.List;

public interface ShopDAO<T,E> {
    List<T> getAll();
    void saveOfUpdate(T t);
    void delete(E id);
    T getById(E id);
}