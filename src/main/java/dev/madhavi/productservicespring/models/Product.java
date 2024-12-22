package dev.madhavi.productservicespring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    @ManyToOne
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
/*Product ------Category
    1             1
    m ------------1 --->> manytoone

 */