package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo;

import java.util.Date;

public class StoreItemResponse {
  private Long id;
  private String name;
  private String description;
  private Double price;
  private String image;
  private Long parentId;
  private Long childId;
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

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Long getChildId() {
    return childId;
  }

  public void setChildId(Long childId) {
    this.childId = childId;
  }

  public Date getPurchasedAt() {
    return purchasedAt;
  }

  public void setPurchasedAt(Date purchasedAt) {
    this.purchasedAt = purchasedAt;
  }
}