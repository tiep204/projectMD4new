package ra.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserLoginDTO {
    private String username;
    private String password;
    private int role;
    private boolean status;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password, int role, boolean status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
