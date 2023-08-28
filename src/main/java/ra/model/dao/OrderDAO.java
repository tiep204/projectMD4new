package ra.model.dao;

import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;

public interface OrderDAO extends ShopDAO<Orders,Integer> {
     Orders searchOrderByUserIdAndStatus(int userId);
}
