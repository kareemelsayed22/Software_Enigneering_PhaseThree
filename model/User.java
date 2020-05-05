package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class to store username and password of an user.
 */
public class User implements Serializable {
    
    /** login username */
    private String username;
    
    /** login password */
    private String password;

    /** role (admin/user) */
    private String role;
    
    /** saved query and results */
    private List<QueryResult> savedQuery;
    
    /**
     * Constructor to create a new User object.
     * 
     * @param username the login username
     * @param password the login password
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        savedQuery = new ArrayList<QueryResult>();
    }

    public void saveQuery(QueryResult qResult){
        savedQuery.add(qResult);
    }
    // Getter/Setter methods
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<QueryResult> getSavedQuery() {
        return savedQuery;
    }
            
}
