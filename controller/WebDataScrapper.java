
package controller;

import db.ItemsDB;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.DataItem;

/**
 * 
 */
public class WebDataScrapper extends Observable {
    
    private final int MAX_PAGES = 1;
    public static final String WEBSITE = "https://kaiyo.com/furniture/";
    private final String PATTERN_ITEM_LINK = "<div class=\"module(.*?)\" data-item-id=\"(.*?)\">(.*?)<a href=\"(.*?)\" title=\"(.*?)\" >";
    private final String PATTERN_TITLE = "<h1 class=\"h2\" itemprop=\"name\">(.*?)</h1>";
    private final String PATTERN_PRICE = "<span class=\"h4\" aria-label=\"Kaiyo price: \\$(.*?)\"><strong>\\$(.*?)</strong></span>";
    private final String PATTERN_CATEGORY = "<span itemprop=\"category\">(.*?)</span></a>";
    private final String PATTERN_IMAGE = "<div class=\"image\">(.*?)" +
"        <img src=\"(.*?)\" alt=\"(.*?)\">";
    
    
    public ItemsDB scrapAllDataItems(){     
        ItemsDB itemsDB = new ItemsDB();
        
        for(int page = 1; page <= MAX_PAGES; page++){
            String initialPageHtml = WebPageReader.getPageContent(WEBSITE + "?page=" + page);
            Pattern p = Pattern.compile(PATTERN_ITEM_LINK);
            Matcher m = p.matcher(initialPageHtml);
            List<String> detailsPageUrlList = new ArrayList<String>();

            while(m.find()){
                String detailsPageUrl = m.group(4);
                detailsPageUrlList.add(detailsPageUrl);
                //System.out.println(detailsPageUrl);
            }
            p = Pattern.compile(PATTERN_IMAGE);
            m = p.matcher(initialPageHtml);
            int i = 0;
            while(m.find()){
                String imgSrc = m.group(2);
                DataItem item = new DataItem();
                item.setImageUrl(imgSrc);
                item.setUrl(detailsPageUrlList.get(i));
                parseItemDetails(item, detailsPageUrlList.get(i));
                itemsDB.addItem(item);
                i++;
                //System.out.println(item.toString());
            }             
        }
               
        setChanged();
        notifyObservers(itemsDB);
        return itemsDB;
    }
    
    public void parseItemDetails(DataItem item, String detailsPageUrl){
        //System.out.println(detailsPageUrl);
        String detailsHtml = WebPageReader.getPageContent(detailsPageUrl);
        Pattern p = Pattern.compile(PATTERN_TITLE);
        Matcher m = p.matcher(detailsHtml);
        if(m.find()){
            String title = m.group(1);
            item.setTitle(title);
        }
        p = Pattern.compile(PATTERN_PRICE);
        m = p.matcher(detailsHtml);
        if(m.find()){
            String priceStr = m.group(2);
            priceStr = priceStr.replaceAll(",", "");
            double price = Double.parseDouble(priceStr);
            item.setPrice(price);
        }
        p = Pattern.compile(PATTERN_CATEGORY);
        m = p.matcher(detailsHtml);
        if(m.find()){
            String catetory = m.group(1);
            item.setCategory(catetory);
        }
    }
    
}
