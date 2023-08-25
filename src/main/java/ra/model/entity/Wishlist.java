package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Wishlist {
    private int wishlistId;
    private User userId;
    private Product productId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt = new Date();

    public Wishlist() {
    }

    public Wishlist(int wishlistId, User userId, Product productId, Date createAt) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.productId = productId;
        this.createAt = createAt;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
