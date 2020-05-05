package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import model.User;

/**
 *
 */
public class UsersDB implements Serializable {
    
    private Map<String,User> users;

    public UsersDB() {
        users = new Hashtable<String,User>();
    }
    
    public static UsersDB loadFromFile(String dbFilename){
        UsersDB userDB = null;
        
        try {
            ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(new File(dbFilename)));
            userDB = (UsersDB)ois.readObject();
            ois.close();
            System.out.println("Users loaded from database file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println(dbFilename + " file not found!, generating empty users database.");
            userDB = new UsersDB();
        } catch (IOException ex) {
            System.out.println("File I/O error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("File I/O error: " + ex.getMessage());
        }
        return userDB;
    } 
    
    public void saveToFile(String dbFilename){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dbFilename)));
            oos.writeObject(this);
            oos.flush();
            oos.close();
            System.out.println("Users database saved to file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: could not save users database in file " + dbFilename + "!");
        } catch (IOException ex) {
            System.out.println("File I/O error: " + ex.getMessage());
        }
    }
    
    
    public User get(String username){
        if(users.containsKey(username)){
            return users.get(username);
        }
        else{
            return null;
        }
    }
    
    public void put(User user){
        users.put(user.getUsername(), user);
    }
    
    public void put(String username, String password, String role){  
        put(new User(username, password, role));
    }
    
    public void remove(String username){
        if(users.containsKey(username)){
            users.remove(username);
        }
    }
    
    public List<String> getUsernames(){
        List<String> usernames = new ArrayList<String>();
        for(User u : users.values()){
            usernames.add(u.getUsername());
        }
        return usernames;
    }
}
