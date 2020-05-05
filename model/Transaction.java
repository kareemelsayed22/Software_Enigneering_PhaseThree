package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class for a transaction.
 */
public class Transaction implements Serializable {
    
    public enum Type { SEARCH, LOGIN, SIGNUP };
    
    /** time stamp of the log */
    private long timeStamp;
    
    /** type of transaction */
    private Type type;
    
    /** owner of the transaction */
    private User user;
    
    /** formatted log data */
    private String logData;

    /**
     * Constructor that creates a new Transaction object.
     * 
     * @param timeStamp the time stamp of the log
     * @param logData the log data
     */
    public Transaction(long timeStamp, User user, Type type, String logData) {
        this.timeStamp = timeStamp;
        this.logData = logData;
        this.type = type;
        this.user = user;
    }

    @Override
    public String toString() {
        String s = new Date(timeStamp).toString()+","+type.toString()+",";
        if(user != null)
            s += "Owner="+user.getUsername()+","+logData;
        else
            s += "Owner=N/A,"+logData;
        return s;
    }        
    
}
