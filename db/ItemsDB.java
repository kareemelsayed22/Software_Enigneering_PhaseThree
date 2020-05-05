
package db;

import controller.WebDataScrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.DataItem;
import model.Query;

/**
 * 
 */
public class ItemsDB implements Serializable {
    private List<DataItem> items;

    public ItemsDB() {
        items = new ArrayList<DataItem>();
    }
    
    public static ItemsDB loadFromFile(String dbFilename){
        ItemsDB itemsDB = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(dbFilename)));
            itemsDB = (ItemsDB)ois.readObject();
            ois.close();
            System.out.println("Items loaded from database file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println(dbFilename + " file not found!, items database will be initialized from website.");
            itemsDB = new ItemsDB();
            System.out.println("Scrapping items from website " + WebDataScrapper.WEBSITE + "...");
            WebDataScrapper scrapper = new WebDataScrapper();
            itemsDB = scrapper.scrapAllDataItems();
            System.out.println(itemsDB.getAllItems().size() + " data items scrapped.\n");
        } catch (IOException ex) {
            System.out.println("Transactions database file I/O error: " + ex.getMessage() + ", database will be initialized from website.");
            itemsDB = new ItemsDB();
            System.out.println("Scrapping items from website " + WebDataScrapper.WEBSITE + "...");
            WebDataScrapper scrapper = new WebDataScrapper();
            itemsDB = scrapper.scrapAllDataItems();
        } catch (ClassNotFoundException ex) {
            System.out.println("System ERROR: " + ex.getMessage());
            Runtime.getRuntime().exit(0);
        }
        return itemsDB;
    }
    
    public void addItem(DataItem item){
        items.add(item);
    }
    
    public List<String> getAllCategories(){
        List<String> categories = new ArrayList<String>();
        for(DataItem item : items){
            categories.add(item.getCategory());
        }
        return categories;
    }
    
    public List<DataItem> getAllItems(){
        return items;
    }
    
    public List<DataItem> search(Query q){
        List<DataItem> matched = new ArrayList<DataItem>();
       
       for(DataItem item : items){
           String title = item.getTitle();
           String category = item.getCategory();
           double price = item.getPrice();
           boolean match = true;
           match = title.toLowerCase().contains(q.getTerm());           
           if(!match) continue;
           if(!q.getCategory().equals("All")){
               match = category.equals(q.getCategory());
           }
           if(!match) continue;
           if(q.getMinPrice() >= 0){
               match = price >= q.getMinPrice();
           }
           if(!match) continue;
           if(q.getMaxPrice() >= 0){
               match = price <= q.getMaxPrice();
           }
           if(match){
               matched.add(item);
           }
       }
       return matched;
    }
    
    public void saveToFile(String dbFilename){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dbFilename)));
            oos.writeObject(this);
            oos.flush();
            oos.close();
            System.out.println("Items database saved to file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println("Items database failed to save, " + ex.getMessage());
            //ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("File I/O error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
