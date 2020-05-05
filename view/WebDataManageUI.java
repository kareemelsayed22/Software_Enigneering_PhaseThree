package view;

import controller.SearchController;
import controller.URLImageReader;
import controller.WebDataScrapper;
import db.ItemsDB;
import db.TransactionsDB;
import db.UsersDB;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.DataItem;
import model.Query;
import model.QueryResult;
import model.Transaction;
import model.User;

/**
 * 
 */
public class WebDataManageUI extends javax.swing.JFrame implements Observer {

    private UsersDB usersDB;
    private User user;
    private ItemsDB itemsDB;
    private TransactionsDB transDB;
    private SearchController searchController;
    private List<QueryResult> savedQueries;
    private List<DataItem> tblItems;
    private WebDataManageUI thisFrame;
    static final String[] tblColNames = {"SL","Title","Category","Price"};
    
    DefaultTableModel tblModel = new DefaultTableModel(tblColNames, 0);
    DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<String>();
    DefaultComboBoxModel<String> cmbSavedQueryModel = new DefaultComboBoxModel<String>();
    DefaultComboBoxModel<String> cmbUsersModel = new DefaultComboBoxModel<String>();
    
    /**
     * Creates new form WebDataManageUI
     */
    public WebDataManageUI(ItemsDB itemsDB, UsersDB userDB, TransactionsDB transDB, User user) {
        initComponents();
        thisFrame = this;
        this.user = user;
        this.usersDB = userDB;
        this.itemsDB = itemsDB;
        this.transDB = transDB;
        tblItems = itemsDB.getAllItems();
        savedQueries = user.getSavedQuery();
        searchController = new SearchController(itemsDB, user, this);
        searchController.addObserver(this);
        btnSearch.addActionListener(searchController);
        btnSearchSaved.addActionListener(searchController);
        //cmbSavedSearch.addActionListener(searchController);
        
        //enableSearchFields(false);                       
        
        tblResult.setModel(tblModel);
        cmbCategory.setModel(cmbModel);
        cmbSavedSearch.setModel(cmbSavedQueryModel);
        cmbUsers.setModel(cmbUsersModel);
        
        if(user.getRole().equals("Admin")){
            lblUser.setText("You are logged in as ADMIN");            
            for(String username : userDB.getUsernames()){
                cmbUsersModel.addElement(username);
            }
        }
        else{
            lblUser.setText("You are logged in as " + user.getUsername().toUpperCase());
            lblFromUser.setVisible(false);
            cmbUsers.setVisible(false);
            btnSeeLog.setVisible(false);
        } 
        
        TableColumnModel columnModel = tblResult.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(20);
        
        updateResultTable(tblItems);
        cmbModel.addElement("All");
        for (String category : itemsDB.getAllCategories()) {
            cmbModel.addElement(category);
        }
    }

    public void updateResultTable(List<DataItem> items) {
        int serialNum = 1;
        tblModel.setRowCount(0);
        for (DataItem item : items) {
            Object[] row = new String[]{serialNum + "", item.getTitle(), item.getCategory(), item.getPrice() + ""};
            tblModel.addRow(row);
            serialNum++;
        }
        tblItems = items;
    }
    
    public void updateSavedQueryCombo(){
        cmbSavedQueryModel.removeAllElements();
        if(savedQueries != null){
            for(QueryResult qRes: savedQueries){
                cmbSavedQueryModel.addElement(qRes.getName());
            }
        }
    }
    
    public void enableSearchFields(boolean enable){
        btnSearch.setEnabled(enable);
        txtKeyword.setEnabled(enable);
        cmbCategory.setEnabled(enable);       
        txtMinPrice.setEnabled(enable);
        txtMaxPrice.setEnabled(enable);
        cmbSavedSearch.setEnabled(enable);
        cmbUsers.setEnabled(enable);
    }
    
    public String getKeywordInput(){
        return txtKeyword.getText();
    }
    
    public String getCategoryInput(){
        return (String)cmbCategory.getSelectedItem();
    }
    
    public String getMinPriceInput(){
        return txtMinPrice.getText();
    }
    
    public String getMaxPriceInput(){
        return txtMaxPrice.getText();
    }
    
    public QueryResult getSelectedSavedQuery(){
        int index = cmbSavedSearch.getSelectedIndex();
        if(index < 0) return null;
        return savedQueries.get(index);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnGroup = new javax.swing.ButtonGroup();
        jPanelNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jPanelCenter = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtKeyword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMinPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaxPrice = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbSavedSearch = new javax.swing.JComboBox<>();
        lblFromUser = new javax.swing.JLabel();
        cmbUsers = new javax.swing.JComboBox<>();
        btnSearchSaved = new javax.swing.JButton();
        jPanelResult = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jPanelSouth = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnShowDetails = new javax.swing.JButton();
        btnSeeLog = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WebDataManagement");
        setPreferredSize(new java.awt.Dimension(900, 700));

        jPanelNorth.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNorth.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelNorth.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/logo.png"))); // NOI18N
        jPanelNorth.add(jLabel1, java.awt.BorderLayout.CENTER);

        lblUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblUser.setText("You are logged in as GUEST");
        jPanelNorth.add(lblUser, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelNorth, java.awt.BorderLayout.NORTH);

        jPanelCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelCenter.setLayout(new java.awt.BorderLayout(0, 10));

        jPanelSearch.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Search"), javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Items with keyword:");
        jPanelSearch.add(jLabel3, new java.awt.GridBagConstraints());

        txtKeyword.setColumns(25);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelSearch.add(txtKeyword, gridBagConstraints);

        jLabel4.setText("in Category ");
        jPanelSearch.add(jLabel4, new java.awt.GridBagConstraints());

        cmbCategory.setBackground(new java.awt.Color(255, 255, 255));
        cmbCategory.setPreferredSize(new java.awt.Dimension(150, 24));
        jPanelSearch.add(cmbCategory, new java.awt.GridBagConstraints());

        jLabel6.setText("MIn Price:");
        jPanel2.add(jLabel6);

        txtMinPrice.setColumns(10);
        jPanel2.add(txtMinPrice);

        jLabel7.setText("Max Price:");
        jPanel2.add(jLabel7);

        txtMaxPrice.setColumns(10);
        jPanel2.add(txtMaxPrice);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanelSearch.add(jPanel2, gridBagConstraints);

        btnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        jPanelSearch.add(btnSearch, gridBagConstraints);

        jLabel2.setText("Select saved search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        jPanelSearch.add(jLabel2, gridBagConstraints);

        cmbSavedSearch.setBackground(new java.awt.Color(255, 255, 255));
        cmbSavedSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSavedSearch.setPreferredSize(new java.awt.Dimension(300, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 3;
        jPanelSearch.add(cmbSavedSearch, gridBagConstraints);

        lblFromUser.setText(" Users: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        jPanelSearch.add(lblFromUser, gridBagConstraints);

        cmbUsers.setBackground(new java.awt.Color(255, 255, 255));
        cmbUsers.setPreferredSize(new java.awt.Dimension(80, 24));
        cmbUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelSearch.add(cmbUsers, gridBagConstraints);

        btnSearchSaved.setText("Search Saved");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        jPanelSearch.add(btnSearchSaved, gridBagConstraints);

        jPanelCenter.add(jPanelSearch, java.awt.BorderLayout.NORTH);

        jPanelResult.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(870, 403));

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "SL", "Title", "Category", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblResult);

        jPanelResult.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelCenter.add(jPanelResult, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelSouth.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelSouth.setLayout(new java.awt.BorderLayout());

        lblStatus.setToolTipText("");
        jPanelSouth.add(lblStatus, java.awt.BorderLayout.WEST);

        btnShowDetails.setText("Item Details...");
        btnShowDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDetailsActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowDetails);

        btnSeeLog.setText("Transaction Logs....");
        btnSeeLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeLogActionPerformed(evt);
            }
        });
        jPanel1.add(btnSeeLog);

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuit);

        jPanelSouth.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        // TODO add your handling code here:
        usersDB.saveToFile("data/users.dat");
        transDB.saveToFile("data/transactions.dat");
        itemsDB.saveToFile("data/items.dat");
        Runtime.getRuntime().exit(0);
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnShowDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDetailsActionPerformed
        // TODO add your handling code here:
        int selectedTableRow = tblResult.getSelectedRow();
        if (selectedTableRow >= 0) {
            DataItem item = tblItems.get(selectedTableRow);
            URLImageReader imageReader = new URLImageReader(item.getImageUrl());
            ItemDetailsUI detailsUI = new ItemDetailsUI(item, thisFrame, false);
            imageReader.addObserver(detailsUI);
            detailsUI.setLocationRelativeTo(null);
            detailsUI.setVisible(true);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    imageReader.readImage();
                }
            });

        }
    }//GEN-LAST:event_btnShowDetailsActionPerformed

    private void btnSeeLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeLogActionPerformed
        // TODO add your handling code here:
        String log = "";
        for(Transaction t : transDB.getAllTranactions()){
            log += t.toString()+"\n";
        }
        JOptionPane.showMessageDialog(this, log);
    }//GEN-LAST:event_btnSeeLogActionPerformed

    private void cmbUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsersActionPerformed
        // TODO add your handling code here:
        String selectedUser = (String)cmbUsers.getSelectedItem();
        if(selectedUser != null){
            User u = usersDB.get(selectedUser);
            savedQueries = u.getSavedQuery();
            updateSavedQueryCombo();
        }
    }//GEN-LAST:event_cmbUsersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchSaved;
    private javax.swing.JButton btnSeeLog;
    private javax.swing.JButton btnShowDetails;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSavedSearch;
    private javax.swing.JComboBox<String> cmbUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelNorth;
    private javax.swing.JPanel jPanelResult;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSouth;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFromUser;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtKeyword;
    private javax.swing.JTextField txtMaxPrice;
    private javax.swing.JTextField txtMinPrice;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WebDataScrapper){
            ItemsDB argItemsDB = (ItemsDB)arg;
            for(DataItem item : argItemsDB.getAllItems()){
                itemsDB.addItem(item);
            }
            updateResultTable(itemsDB.getAllItems());
            enableSearchFields(true);
            //lblStatus.setText(itemsDB.getAllItems().size()+ " data items loaded.");
            cmbModel.addElement("All");
            for(String category : itemsDB.getAllCategories()){
                cmbModel.addElement(category);
            }
        }
        if(o instanceof SearchController){
            if(arg instanceof QueryResult){
                QueryResult qResult = (QueryResult)arg;
                updateResultTable(qResult.getResult());
                Query q = qResult.getQuery();
                String logData = "Term="+q.getTerm()+",Category="+q.getCategory()+",MinPrice="+q.getMinPrice()+",MaxPrice="+q.getMaxPrice();
                Transaction t = new Transaction(System.currentTimeMillis(), user, Transaction.Type.SEARCH, logData);
                transDB.add(t);
                promptToSaveSearch(qResult);
            }
            if(arg instanceof List){
                List<DataItem> items = (List<DataItem>)arg;
                updateResultTable(items);
                //System.out.println("Search results " + items.size());                
            }
            
        }
    }
    
    private void promptToSaveSearch(QueryResult qResult){
        String name = JOptionPane.showInputDialog(this, "Save this search with name?:", "Save", JOptionPane.YES_NO_OPTION);
        if(name != null && !name.isEmpty()){
            qResult.setName(name);
            user.saveQuery(qResult);
            usersDB.put(user);
            savedQueries = user.getSavedQuery();
            updateSavedQueryCombo();
            JOptionPane.showMessageDialog(this, "Query saved to user database.");
        }
        
    }
}
