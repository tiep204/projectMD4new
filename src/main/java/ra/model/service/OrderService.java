package ra.model.service;

import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;

public interface OrderService extends IService<Orders,Integer> {
    public Orders searchOrderByUserIdAndStatus(int userId);

}