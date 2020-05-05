package model;

import java.io.Serializable;
import java.util.List;

/**
 * Model class to store a query and matched results.
 */
public class QueryResult implements Serializable {
    
    /** name of the query */
    private String name;
    
    /** time stamp at which query was made */
    private long timeStamp;
    
    /** the Query instance containing parameters */
    private Query query;
    
    /** list of DataItem as part of the search results */
    private List<DataItem> result;

    /**
     * Constructor that creates a new QueryResult object.
     * 
     * @param timeStamp the time stamp of operation
     * @param name name of the query which is unique
     * @param query the Query instance 
     * @param result a list of DataItem for the search results
     */
    public QueryResult(long timeStamp, String name, Query query, List<DataItem> result) {
        this.timeStamp = timeStamp;
        this.query = query;
        this.result = result;
    }

    // Getter methods
    public String getName() {
        return name;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public Query getQuery() {
        return query;
    }
    public List<DataItem> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
