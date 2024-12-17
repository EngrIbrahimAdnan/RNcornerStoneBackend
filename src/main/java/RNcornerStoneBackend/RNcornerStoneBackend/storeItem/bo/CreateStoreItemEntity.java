package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public class CreateStoreItemEntity {

    @NotNull(message = "The 'name' field is required for adding item and it's missing")
    private String name;

    @NotNull(message = "The 'description' field for adding item is required and it's missing")
    private String description;

    @NotNull(message = "The 'price' field is required and it's missing")
    private Integer price;

    @NotNull(message = "The 'image' Url String field is required and it's missing")
    private String image;

    @NotNull(message = "The 'parentUserEntity' Url String field is required and it's missing")
    private UserEntity parentUserEntity;

    @NotNull(message = "The 'childUserEntity' Url String field is required and it's missing")
    private UserEntity childUserEntity;

    @NotNull(message = "The 'date' field is required and it's missing")
    @PastOrPresent(message = "The 'date' must be in the present or past")
    private Date date;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
