package controller;

import db.ItemsDB;
import db.TransactionsDB;
import db.UsersDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import model.Transaction;
import model.User;
import view.LoginUI;
import view.WebDataManageUI;

/**
 */
public class LoginController implements ActionListener, WindowListener {
    
    private UsersDB userDB;
    private TransactionsDB transDB;
    private ItemsDB itemsDB;
    private LoginUI loginUI;

    public LoginController(UsersDB userDB, TransactionsDB transDB, ItemsDB itemsDB, LoginUI loginUI) {
        this.userDB = userDB;
        this.loginUI = loginUI;
        this.transDB = transDB;
        this.itemsDB = itemsDB;
        loginUI.setController(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        String username = loginUI.getInputUsername();
        String password = loginUI.getInputPassword();
        String role = loginUI.getInputRole();
        
        if(command.equals("EXIT")){
            loginUI.dispose();
            userDB.saveToFile("data/users.dat");
            itemsDB.saveToFile("data/items.dat");
            transDB.saveToFile("data/transactions.dat");
            Runtime.getRuntime().exit(0);
        }
        if(command.equals("LOGIN")){                        
            User user = userDB.get(username);
            if(user == null){
                loginUI.showError("Invalid Username/Password!");
                transDB.add(new Transaction(System.currentTimeMillis(), user, Transaction.Type.LOGIN, "Failed"));
                return;
            }
            if(!user.getPassword().equals(password)){
                loginUI.showError("Invalid Username/Password!");
                transDB.add(new Transaction(System.currentTimeMillis(), user, Transaction.Type.LOGIN, "Failed"));
                return;
            }
            if(!user.getRole().equals(role)){
                loginUI.showError("Invalid Username/Password!");
                transDB.add(new Transaction(System.currentTimeMillis(), user, Transaction.Type.LOGIN, "Failed"));
                return;
            }            
            Transaction t = new Transaction(System.currentTimeMillis(), user, Transaction.Type.LOGIN, "Success");
            transDB.add(t);
            //ItemsDB itemsDB = new ItemsDB();
            WebDataScrapper scrapper = new WebDataScrapper();
            WebDataManageUI mainUI = new WebDataManageUI(itemsDB, userDB, transDB, user);
            mainUI.setLocationRelativeTo(null);
            mainUI.setVisible(true);
            scrapper.addObserver(mainUI);            
            loginUI.dispose();
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    scrapper.scrapAllDataItems();
//                }
//            });
        }
        if(command.equals("SIGNUP")){
            User u = userDB.get(username);
            if(u != null){
                loginUI.showError("Username " + username + " already exists!");
                transDB.add(new Transaction(System.currentTimeMillis(), null, Transaction.Type.SIGNUP, "Failed"));
                return;
            }
            User newUser = new User(username, password, role);
            userDB.put(newUser);
            transDB.add(new Transaction(System.currentTimeMillis(),
                    null, Transaction.Type.SIGNUP,
                    "username="+newUser.getUsername()+",password="+newUser.getPassword()));
            loginUI.showMessage("You have successfully registered! Please sign in.");
        }
    }

    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        userDB.saveToFile("data/users.dat");
        Runtime.getRuntime().exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    
}
