package RNcornerStoneBackend.RNcornerStoneBackend.child.bo;

import java.util.Date;

public class AddChildRequest {
    private String username;
    private String email;
    private String password;
    private Double initialBalance;
    private Date dateOfBirth;
    private String avatarUrl;

    public AddChildRequest(String username, String email, String password, Double initialBalance, Date dateOfBirth,
            String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.initialBalance = initialBalance;
        this.dateOfBirth = dateOfBirth;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
