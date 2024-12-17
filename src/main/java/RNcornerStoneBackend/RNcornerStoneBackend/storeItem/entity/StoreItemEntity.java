package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.entity;

import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class StoreItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "Parent_user_id")
    private UserEntity parentUserEntity;

    @ManyToOne
    @JoinColumn(name = "Childser_id")
    private UserEntity childUserEntity;

    private Date purchasedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserEntity getParentUserEntity() {
        return parentUserEntity;
    }

    public void setParentUserEntity(UserEntity parentUserEntity) {
        this.parentUserEntity = parentUserEntity;
    }

    public UserEntity getChildUserEntity() {
        return childUserEntity;
    }

    public void setChildUserEntity(UserEntity childUserEntity) {
        this.childUserEntity = childUserEntity;
    }

    public Date getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(Date purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
