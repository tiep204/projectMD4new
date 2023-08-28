package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
    private int orderId;
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date createOrder = new Date();
    private float totalPrice;
    private int status;
    private String note;
    private List<OrderDetail> orderDetails = new ArrayList<>();
    public Orders() {
    }

    public Orders(int orderId, User user, Date createOrder, float totalPrice, int status, String note, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.user = user;
        this.createOrder = createOrder;
        this.totalPrice = totalPrice;
        this.status = status;
        this.note = note;
        this.orderDetails = orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(Date createOrder) {
        this.createOrder = createOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
