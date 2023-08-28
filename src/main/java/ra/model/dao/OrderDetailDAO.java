package ra.model.dao;

import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;

import java.util.List;

public interface OrderDetailDAO extends ShopDAO<OrderDetail, Integer> {
    List<OrderDetail> findByOrderId(int orderId);

}
