package ra.model.service;

import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;

import java.util.List;

public interface OrderDetailService extends IService<OrderDetail,Integer> {
   List<OrderDetail> findByOrderId(int orderId);
}
