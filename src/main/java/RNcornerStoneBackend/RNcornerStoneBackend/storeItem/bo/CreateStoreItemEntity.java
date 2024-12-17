package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo;

import jakarta.validation.constraints.NotNull;

public class CreateStoreItemEntity {

    @NotNull(message = "The 'name' field is required for adding item and it's missing")
    private String name;

    @NotNull(message = "The 'description' field for adding item is required and it's missing")
    private String description;

    @NotNull(message = "The 'price' field is required and it's missing")
    private Double price;

    @NotNull(message = "The 'image' Url String field is required and it's missing")
    private String image;

    @NotNull(message = "The 'child_id' String field is required and it's missing")
    private Long child_id;

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

    public Long getChild_id() {
        return child_id;
    }

    public void setChild_id(Long child_id) {
        this.child_id = child_id;
    }
}
