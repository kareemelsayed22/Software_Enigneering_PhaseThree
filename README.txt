/**

CS370 : Software Engineering
Phase 3 : Obtain data from a set of webpages from www.kaiyo.com
Author : Kareem El Sayed 
-------------------------


Compile & Run Code:
--------------------
Step 1: Inside the project folder type the following command

javac -d . -cp . Main.java

Step 2: After compilation of step 1 then run the following command

java -cp . Main  -i data/query.txt  -o data/results.txt 


Files in folder:
-----------------

WebDataProject
  - Main.java       -> The main java class that runs the project
  - view            -> the package with all GUI screens
    - LoginUI.java           -> login UI screen
    - ItemDetailsUI.java     -> item details UI screen
    - WebDataManageUI.java   -> main GUI screen where user does search
  - controller      -> the controller package       
    - CommandLineParser.java -> class that parses command line arguments for flags
    - LoginController.java   -> controls login UI screen with backend storage for Login/Signup
    - SearchController.java  -> controls to execute user search and display it in WebDataManageUI screen
    - URLImageReader.java    -> reads an image from URL
    - WebDataScrapper.java   -> scraps data items from the assigned website 
    - WebPageReader.java     -> reads html from a website URL

  - db               -> contains database classes for items, users and transactions
    - ItemsDB.java           -> model class to store all website DataItem objects
    - TransactionsDB.java    -> model class to store all user transactios 
    - UsersDB.java           -> model class to store all User's
  - model            -> package for all model classes
    - DataItem.java          -> model class for an item from website
    - User.java              -> model class for an user of the application
    - Transaction.java       -> model class for a SEARCH/LOGIN/SIGNUP transaction 
    - Query.java             -> model class for a user query
    - QueryResult.java       -> model class for a user query and search results (list of DataItem)
  -data              -> data folder where all database files are saved. It also contains initial query file
                        named query.txt
    - query.txt


Database Files:
----------------
data/users.dat     -> database file to store users 
data/items.data    -> database file to store all website items
data/transactions.dat  -> database file to store all transactions

All above database files are serialized data of instances UsersDB, ItemsDB and TransactionsDB respectively.


Remarks:
----------
1. No database files will be there inside data/ folder when project run for first time
2. On first run the project will scrap all items from the website and store them in ItemsDB class instance
3. If step 2 is run with -p flag at the end of the argument on command prompt then ItemsDB is not saved to database file, else its written

**/