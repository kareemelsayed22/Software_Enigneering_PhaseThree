package model;

import java.io.Serializable;

/**
 * Model class to store details of query parameter.
 */
public class Query implements Serializable {
    
    /** search term */
    private String term;
    
    /** search category */
    private String category;

    /** minimum price of item */
    private double minPrice;
    
    /** maximum price of item */
    private double maxPrice;
    
    /**
     * Constructor that creates a new Query object.
     * 
     * @param term a string to match with item title
     * @param category the category of item
     */
    public Query(String term, String category, double minPrice, double maxPrice) {
        this.term = term;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static Query fromString(String s){
        String[] fields = s.split(",");
        String term = fields[0];
        String category = fields[1];
        double minPrice, maxPrice;
        try{
            minPrice = Double.parseDouble(fields[2]);
            maxPrice = Double.parseDouble(fields[3]);
            Query q = new Query(term, category, minPrice, maxPrice);
            return q;
        }catch(NumberFormatException e){
            return null;
        }
    }
    
    // Getter/Setter of term and category
    public String getTerm() {
        return term;
    }

    public String getCategory() {
        return category;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "Term="+term+",Category="+category+",MinPrice="+minPrice+",MaxPrice="+maxPrice;
    }
            
    
}
