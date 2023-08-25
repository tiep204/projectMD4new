package ra.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.model.entity.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDTO {
    private int productId;
    private String productName;
    private int category;
    private String description;
    private float price;
    private MultipartFile image;
    private int stock;
    private boolean status = true;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createProduct =new Date();
    private List<MultipartFile> listImage ;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, int category, String description, float price, MultipartFile image, int stock, boolean status, Date createProduct, List<MultipartFile> listImage) {
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

    public int getCategoryId() {
        return category;
    }

    public void setCategoryId(int categoryId) {
        this.category = categoryId;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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

    public List<MultipartFile> getListImage() {
        return listImage;
    }

    public void setListImage(List<MultipartFile> listImage) {
        this.listImage = listImage;
    }
}