package ra.model.entity;

public class OrderDetail {
    private int orDId;
    private Orders orderId;
    private Product productId;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orDId, Orders orderId, Product productId, int quantity) {
        this.orDId = orDId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrDId() {
        return orDId;
    }

    public void setOrDId(int orDId) {
        this.orDId = orDId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
