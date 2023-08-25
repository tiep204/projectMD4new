package ra.model.service;

import ra.model.entity.Category;

import java.util.List;

public interface IService<T,E> {
    List<T> getAll();
    void saveOfUpdate(T t);
    void delete(E id);
    T getById(E id);
}