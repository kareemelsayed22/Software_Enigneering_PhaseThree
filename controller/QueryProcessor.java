
package controller;

import db.ItemsDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.DataItem;
import model.Query;

/**
 * This class process a batch of queries read from input file and then writes the
 * query results to an output file.
 * 
 */
public class QueryProcessor {
    
    /** input query file */
    private File queryFile;
    
    /** output file to save results */
    private File outputFile;

    /** items database */
    private ItemsDB itemsDB;
    
    private BufferedReader in;
    private PrintWriter out;
    
    /**
     * Constructor that initializes with the query and output file.
     * 
     * @param queryFile the query file
     * @param outputFile the output file
     */
    public QueryProcessor(File queryFile, File outputFile, ItemsDB itemsDB) throws Exception {
        this.queryFile = queryFile;
        this.outputFile = outputFile;
        this.itemsDB = itemsDB;
        try {
            in = new BufferedReader(new FileReader(queryFile));
            out = new PrintWriter(outputFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Could not access query file " + queryFile.getAbsolutePath());
            throw ex;
        }
    }
    
    /**
     * Process all the queries from input file and save results
     * to output file.
     */
    public void batchProcess(){
        String queryLine;
        try{
            in.readLine();
            int qNum = 1;
            while((queryLine = in.readLine()) != null){
                Query q = Query.fromString(queryLine);                
                List<DataItem> result = itemsDB.search(q);
                out.print("Query #" + qNum + ": ");
                out.println(q.toString());
                for(DataItem item : result){
                    out.println("Title: "+item.getTitle());
                    out.println("Category: " + item.getCategory());
                    out.println("Price: $" + item.getPrice());
                    out.println("URL: " + item.getUrl());
                    out.println("ImageURL: " + item.getImageUrl()+"\n");
                }
                out.println("Total " + result.size()+" matches found.");
                out.println();
            }
        }catch(IOException ioe){
            System.out.println("Failed to batch process queries, " + ioe.getMessage());
        }
        try {
            in.close();
            out.close();
        } catch (IOException ex) {
        }
        
    }
}
