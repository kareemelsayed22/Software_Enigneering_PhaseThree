package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * 
 */
public class WebPageReader {
    
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";
    
    public static String getPageContent(String pageUrl){
        StringBuilder htmlStringBuffer = new StringBuilder();
        try {
            URL url = new URL(pageUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String htmlLine;
            while((htmlLine = reader.readLine()) != null){
                //System.out.println(htmlLine);
                htmlStringBuffer.append(htmlLine);
            }
            reader.close();
            return htmlStringBuffer.toString();
        } catch (MalformedURLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }
    
}
