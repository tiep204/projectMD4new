package ra.model.dao;

import ra.model.entity.User;

import java.util.List;

public interface UserDAO extends ShopDAO<User,Integer> {
    Boolean register(User user);
    boolean updateUserBlock(int id);
    boolean updateUserUnlock(int id);
    List<User> searchByName(String name);
    User login(String userName, String password);
}
