package ra.model.serviceImpl;

import org.springframework.stereotype.Service;
import ra.model.dao.OrderDetailDAO;
import ra.model.daoImpl.OrderDetailDaoImpl;
import ra.model.entity.Category;
import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;
import ra.model.service.OrderDetailService;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailDAO orderDetailDAO = new OrderDetailDaoImpl();

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailDAO.getAll();
    }

    @Override
    public void saveOfUpdate(OrderDetail orderDetail) {
        orderDetailDAO.saveOfUpdate(orderDetail);
    }

    @Override
    public void delete(Integer id) {
        orderDetailDAO.delete(id);
    }

    @Override
    public OrderDetail getById(Integer id) {
        return orderDetailDAO.getById(id);
    }


    @Override
    public List<OrderDetail> findByOrderId(int orderId) {
        return orderDetailDAO.findByOrderId(orderId);
    }
}
