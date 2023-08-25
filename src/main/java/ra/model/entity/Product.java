package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {
    private int productId;
    private String productName;
    private int category;
    private String description;
    private float price;
    private String image;
    private int stock;
    private boolean status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createProduct =new Date();
    private List<String> listImage = new ArrayList<>();

    public Product() {
    }

    public Product(int productId, String productName, int category, String description, float price, String image, int stock, boolean status, Date createProduct, List<String> listImage) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.image = image;
        this.stock = stock;
        this.status = status;
        this.createProduct = createProduct;
        this.listImage = listImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateProduct() {
        return createProduct;
    }

    public void setCreateProduct(Date createProduct) {
        this.createProduct = createProduct;
    }

    public List<String> getListImage() {
        return listImage;
    }

    public void setListImage(List<String> listImage) {
        this.listImage = listImage;
    }
}