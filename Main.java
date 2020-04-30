
import controller.CommandLineParser;
import controller.LoginController;
import controller.QueryProcessor;
import db.ItemsDB;
import db.TransactionsDB;
import db.UsersDB;
import java.io.File;
import java.util.Map;
import view.LoginUI;

/**
 *
 */
public class Main {

    public static void showUsageAndExit(){
        System.out.println("Usage: java Main -i QUERY-FILE -o OUTPUT-FILE [-p]");
        System.out.println("where the flags are described below:");
        System.out.println("-i QUERY-FILE   file containing initial queries to process");
        System.out.println("-o OUTPUT-FILE  file where query results will be written to");
        System.out.println("-p              by pass the GUI and exit the program");
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        // parse the command line flags (must provide -i and -o flags)
        Map<String,String> flags = CommandLineParser.parseFflags(args);
        if(!flags.containsKey("-i") || !flags.containsKey("-o")){
            showUsageAndExit();
        }
        File queryFile = new File(flags.get("-i"));
        File outputFile = new File(flags.get("-o"));
        
        
        UsersDB userDB = UsersDB.loadFromFile("data/users.dat");
        TransactionsDB transDB = TransactionsDB.loadFromFile("data/transactions.dat");
        ItemsDB itemsDB = ItemsDB.loadFromFile("data/items.dat");
        
        try {
            System.out.println("\nBatch processing query file " +  queryFile.getAbsolutePath());
            QueryProcessor qProcessor = new QueryProcessor(queryFile, outputFile, itemsDB);
            qProcessor.batchProcess();
            System.out.println("Processing Done.\nResults written to file " + outputFile.getAbsolutePath()+"\n\n");
        } catch (Exception ex) {
            System.out.println("Query processing failed, " + ex.getMessage());
            ex.printStackTrace();
        }
        
        if(!flags.containsKey("-p")){
             LoginUI loginUI = new LoginUI();
            loginUI.setLocationRelativeTo(null);

            LoginController controller = new LoginController(userDB, transDB, itemsDB, loginUI);
            loginUI.setVisible(true);
        }
       
    }
    
}
