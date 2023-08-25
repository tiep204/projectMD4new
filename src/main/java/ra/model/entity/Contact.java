package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Contact {
    private int id;
    private String fullName;
    private String email;
    private String content;
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create = new Date();
    private int updateById;

    public Contact() {
    }

    public Contact(int id, String fullName, String email, String content, int status, Date create, int createById, Date updateAt, int updateById) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.content = content;
        this.status = status;
        this.create = create;
        this.updateById = updateById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public int getUpdateById() {
        return updateById;
    }

    public void setUpdateById(int updateById) {
        this.updateById = updateById;
    }
}
