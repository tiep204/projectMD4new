package ra.model.serviceImpl;

import org.springframework.stereotype.Service;
import ra.model.dao.OrderDAO;
import ra.model.daoImpl.OrderDaoImpl;
import ra.model.entity.Category;
import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;
import ra.model.service.OrderDetailService;
import ra.model.service.OrderService;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrderService {
    OrderDAO orderDAO = new OrderDaoImpl();
    @Override
    public List<Orders> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public void saveOfUpdate(Orders orders) {
        orderDAO.saveOfUpdate(orders);
    }

    @Override
    public void delete(Integer id) {
        orderDAO.delete(id);
    }

    @Override
    public Orders getById(Integer id) {
        return orderDAO.getById(id);
    }

    @Override
    public Orders searchOrderByUserIdAndStatus(int userId) {
        return orderDAO.searchOrderByUserIdAndStatus(userId);
    }
}
