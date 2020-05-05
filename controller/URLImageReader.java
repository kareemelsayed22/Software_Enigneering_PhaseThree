
package controller;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 */
public class URLImageReader extends Observable {

    private final String imgUrl;
    private Image image;
    
    public URLImageReader(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    public void readImage(){
        try {        
            URL url = new URL(imgUrl);
            image = ImageIO.read(url);
            setChanged();
            notifyObservers(image);
            //System.out.println(imgUrl);
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            setChanged();
            notifyObservers(null);
            //System.out.println(imgUrl);
        }
    }
}
