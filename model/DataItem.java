package model;

import java.io.Serializable;

/**
 * Model class to store details of an item obtained from the
 * web site page.
 */
public class DataItem implements Serializable {
    
    private String title;
    private double price;
    private String category;
    private String tags;
    private String imageUrl;
    private String url;
    
    public DataItem() {
    }

    
    public DataItem(String title, double price, String category, String tags) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.tags = tags;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    @Override
    public String toString() {
        String s = title+" ($" + price + "), Category: " + category+"\nImageURL: ";      
        s += imageUrl;
        return s;
    }
    
    
    
}
