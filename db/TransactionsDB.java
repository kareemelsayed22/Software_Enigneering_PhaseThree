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
import java.util.List;
import model.Transaction;

/**
 * This class implements the database of all transactions.
 */
public class TransactionsDB implements Serializable {
    
    private List<Transaction> transactions;

    public TransactionsDB() {
        transactions = new ArrayList<Transaction>();
    }

    public static TransactionsDB loadFromFile(String dbFilename){
        TransactionsDB transDB = null;
        
        try {
            ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(new File(dbFilename)));
            transDB = (TransactionsDB)ois.readObject();
            ois.close();
            System.out.println("Transactions loaded from database file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println(dbFilename + " file not found!, generating empty transactions database.");
            transDB = new TransactionsDB();
        } catch (IOException ex) {
            System.out.println("File I/O error: " + ex.getMessage() + ", generating empty transactions database.");
            transDB = new TransactionsDB();
        } catch (ClassNotFoundException ex) {
            System.out.println("File I/O error: " + ex.getMessage() + ", generating empty transactions database.");
            transDB = new TransactionsDB();
        }
        return transDB;
    }
    
    public void add(Transaction t){
        transactions.add(t);
    }
    
    public List<Transaction> getAllTranactions(){
        return transactions;
    }
    
    public void saveToFile(String dbFilename){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dbFilename)));
            oos.writeObject(this);
            oos.flush();
            oos.close();
            System.out.println("Transaction logs saved to file " + dbFilename);
        } catch (FileNotFoundException ex) {
            System.out.println("Transactions database failed to save!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("File I/O error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
