
package controller;

import db.ItemsDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import model.DataItem;
import model.Query;
import model.QueryResult;
import model.User;
import view.WebDataManageUI;

/**
 * 
 */
public class SearchController extends Observable implements ActionListener {
    
   private ItemsDB itemsDB;
   private User user;
   private WebDataManageUI ui;

    public SearchController(ItemsDB itemsDB, User user, WebDataManageUI ui) {
        this.itemsDB = itemsDB;
        this.user = user;
        this.ui = ui;
    }
   
   public void search(){
       String keyword = ui.getKeywordInput();
       String categoryInput = ui.getCategoryInput();
       String minPriceStr = ui.getMinPriceInput();
       String maxPriceStr = ui.getMaxPriceInput();
       double minPriceInput, maxPriceInput;
       try{
           minPriceInput = Double.parseDouble(minPriceStr);
       }catch(NumberFormatException ex){
           minPriceInput = -1;
       }
       try{
           maxPriceInput = Double.parseDouble(maxPriceStr);
       }catch(NumberFormatException ex){
           maxPriceInput = -1;
       }
       Query query = new Query(keyword, categoryInput, minPriceInput, maxPriceInput);
       List<DataItem> matched = new ArrayList<DataItem>();
       
       for(DataItem item : itemsDB.getAllItems()){
           String title = item.getTitle();
           String category = item.getCategory();
           double price = item.getPrice();
           boolean match = true;
           match = title.toLowerCase().contains(keyword.toLowerCase());           
           if(!match) continue;
           if(!categoryInput.equals("All")){
               match = category.equals(categoryInput);
           }
           if(!match) continue;
           if(minPriceInput >= 0){
               match = price >= minPriceInput;
           }
           if(!match) continue;
           if(maxPriceInput >= 0){
               match = price <= maxPriceInput;
           }
           if(match){
               matched.add(item);
           }
       }
       QueryResult qResult = new QueryResult(System.currentTimeMillis(), "", query, matched);
       setChanged();
       notifyObservers(qResult);
       //System.out.println(matched.size());
   }

   public void searchSavedQuery(){
       QueryResult qResult = ui.getSelectedSavedQuery();
       if(qResult != null){
           setChanged();
           notifyObservers(qResult.getResult());
       }
   }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Search")){ // Search button was pressed 
            search();
        }
        else{                       // an item from saved search combobox was selected
            searchSavedQuery();
        }
    }
   
}
