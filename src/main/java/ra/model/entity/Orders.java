package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Orders {
    private int orderId;
    private User userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date createOrder = new Date();
    private float totalPrice;
    private int status;
    private String note;
    public Orders() {
    }

    public Orders(int orderId, User userId, Date createOrder, float totalPrice, int status, String note) {
        this.orderId = orderId;
        this.userId = userId;
        this.createOrder = createOrder;
        this.totalPrice = totalPrice;
        this.status = status;
        this.note = note;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
