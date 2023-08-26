package ra.model.serviceImpl;

import org.springframework.stereotype.Service;
import ra.model.dao.UserDAO;
import ra.model.daoImpl.UserDaoImpl;
import ra.model.entity.Category;
import ra.model.entity.User;
import ra.model.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDaoImpl();

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void saveOfUpdate(User user) {
        userDAO.saveOfUpdate(user);
    }

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }

    @Override
    public User getById(Integer id) {
        return userDAO.getById(id);
    }

    @Override
    public Boolean register(User user) {
        return userDAO.register(user);
    }

    @Override
    public boolean updateUserBlock(int id) {
        return userDAO.updateUserBlock(id);
    }

    @Override
    public boolean updateUserUnlock(int id) {
        return userDAO.updateUserUnlock(id);
    }

    @Override
    public List<User> searchByName(String name) {
        return userDAO.searchByName(name);
    }

    @Override
    public User login(String userName, String password) {
        return userDAO.login(userName,password);
    }
}