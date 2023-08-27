package ra.model.dao;

import ra.dto.UserLoginDTO;
import ra.model.entity.User;

import java.util.List;

public interface UserDAO extends ShopDAO<User,Integer> {
    Boolean register(User user);
    boolean updateUserBlock(int id);
    boolean updateUserUnlock(int id);
    List<User> searchByName(String name);
    User login(UserLoginDTO userLoginDTO);

    User seachUsername(String name);
}
