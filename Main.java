import java.util.*;
import java.sql.SQLException;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;


// BOOK DAO (MVC) usermanager login actionhandler util ,, packages ,, 
// advanced search in  


class Main {
  static final private int PORT = 8080;

  public static void main(String[] args) throws SQLException, IOException {

     //HttpServer object bound to an IP address and port listens for incoming requests:
     HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0); 
     server.createContext("/", new RootHandler());
     server.createContext("/login", new LoginHandler());
     server.createContext("/loginaction", new LoginActionHandler());
     server.createContext("/add", new AddHandler()); 
     server.createContext("/processAdd", new ProcessAddBookHandler()); 
     server.createContext("/Delete", new DeleteHandler()); 
     server.createContext("/update", new UpdateHandler()); 
     server.createContext("/processUpdate", new ProcessUpdateBookHandler()); 
     server.createContext("/Admin", new AdministratorHandler()); 
     server.setExecutor(null);
     server.start();
     System.out.println("Server is listening on port: " + PORT);

     //-------------------------------------------
     Scanner input = new Scanner(System.in);
     String Selection;     
     BookDAO bookDb = new BookDAO();
     new UserManager().debug_printAllUsers();

      do { 
        System.out.println("------------");
        System.out.println("Book Inventory");
        System.out.println("------------");
        System.out.println("Choose from these options");
        System.out.println("[1] List all Books");
        System.out.println("[2] Search Book by ID");
        System.out.println("[3] Add a new Book");
        System.out.println("[4] Update a Book");
        System.out.println("[5] Delete a Book by ID");
        System.out.println("[6] List all Customers");
        System.out.println("[7] Search Customer by ID");
        System.out.println("[8] Add a new Customer");
        System.out.println("[9] Update a Customer");
        System.out.println("[10] Delete a Customer by ID");
        System.out.println("[11] Exit");
        System.out.println();
        //----------------------------------------------------------------
        Selection = input.nextLine();
        
        switch(Selection) {
            case "1":
                System.out.println("List all Books");
                ArrayList<Book> allBooks = bookDb.getAllBooks();
                
                for(int i = 0; i < allBooks.size(); i++){
                    System.out.println(allBooks.get(i));
                }
                System.out.println();    
                break; 
            
            case "2":
                System.out.println("Search book by Id");
                int search = readInteger(input);
                System.out.println(bookDb.getBook(search));
                break;
            
            case "3":
                System.out.print("Enter details for new Book");
                Book newB = CreateANewBook();
                bookDb.insertBook(newB);
                System.out.println("New Book inserted");
                break;
            
            case "4":
                System.out.print("Update book ID: ");
                int id = input.nextInt();
                Book originalBook = bookDb.getBook(id);
                if (originalBook == null) {
                    System.out.println("This book doesn't exist");
                } else {
                    Book updatedBook = UpdatedBook(originalBook);
                        if (bookDb.updateBook(updatedBook)) {
                            System.out.println("Book updated.");
                    }
                }
                break;
            
            case "5":
                System.out.println("Delete Book");
                System.out.println("Enter the Book ID to remove:");
                int delID = Integer.parseInt(input.nextLine());
                bookDb.deleteBook(delID);
                break;

            case "6":
                System.out.println("Listing all Customers");
                ArrayList<Customer> allCustomers = bookDb.getAllCustomers();
                for (int i = 0; i < allCustomers.size(); i++) {
                        System.out.println(allCustomers.get(i));
                } 
                System.out.println();
                break;

            case "7":
                System.out.println("Search Customer by Id");
                int searchCust = readInteger(input);
                    System.out.println(bookDb.getCustomerByID(searchCust));
                break;

            case "8":
                System.out.print("New Customer details");
                Customer newCust = CreateANewCustomer();
                bookDb.insertCustomer(newCust);
                    System.out.println("New Customer inserted");
                break;

            case "9":
                System.out.println("Update Customer ID");
                int custid = input.nextInt();
                Customer originalCustomer = bookDb.getCustomerByID(custid);
                if (originalCustomer == null) {
                    System.out.println("This Customer doesn't exist");
                } else {
                    Customer updatedcustomer = UpdateACustomer(originalCustomer);
                        if (bookDb.updateCustomer(updatedcustomer)) {
                            System.out.println("Customer details updated.");
                    }
                }
                break;

            case "10":
                System.out.println("Enter a Customer ID to delete:");
                int delcust = input.nextInt();
                bookDb.DeleteCustomerID(delcust); 
                System.out.println("Customer ID:" + delcust + "Deleted from [BookDAO]");
                break;
               
            case "11":
                System.out.println("Quitting");
                break;                
            }  
        } while(!Selection.equals("12"));
    }

     // Private functionalities:
    
     private static Book CreateANewBook() {

        int id;
        String title;
        String author;
        int year;
        int edition;
        String publisher;
        String isbn; 
        String cover; 
        String condition; 
        String price; 
        String notes; 

        Scanner in = new Scanner(System.in);        

        
        System.out.println("\nEnter an ID");
        id = Integer.parseInt(in.nextLine().trim());
      
        System.out.println("\nEnter the Title");
        title = in.nextLine().trim();
      
        System.out.println("\nEnter the Author");
        author = in.nextLine().trim();
      
        System.out.println("\nEnter the Year");
        year = Integer.parseInt(in.nextLine().trim());
      
        System.out.println("\nEnter an Edition");
        edition = Integer.parseInt(in.nextLine().trim());
   
        System.out.println("\nEnter a Publisher");
        publisher = in.nextLine().trim();

        System.out.println("\nEnter an Isbn");
        isbn = in.nextLine().trim();

        System.out.println("\nEnter the Cover");
        cover = in.nextLine().trim();

        System.out.println("\nEnter the Condition");
        condition = in.nextLine().trim();
        
        System.out.println("\nEnter a Price");
        price = in.nextLine().trim();

        System.out.println("\nEnter Notes");
        notes = in.nextLine().trim();
        
        return new Book(id,
                        title,
                        author,
                        year,
                        edition,
                        publisher,
                        isbn,
                        cover,
                        condition,
                        price,
                        notes
                       );                  
    }

     //'OR 1=1;--
    
     // Private functionalities:

     private static Book UpdatedBook(Book book) {
        Scanner input = new Scanner(System.in);
        System.out.println("Updating Book " + book.getBook_id());
        
        // Title
        System.out.print("Title [" + book.getTitle() + "]: ");
        String newTitle = input.nextLine().trim();
        if (!newTitle.isEmpty()) {
            book.setTitle(newTitle);
        }

        // Author
        System.out.print("Author [" + book.getAuthor() + "]: ");
        String newAuthor = input.nextLine().trim();
        if (!newAuthor.isEmpty()) {
            book.setAuthor(newAuthor);
        }

        // Year
        while (true) {
            System.out.print("Year [" + book.getYear() + "]: ");
            String newYearLine = input.nextLine().trim();
            if (newYearLine.isEmpty()) {
               // break;
            }
            
            try {
                int newYear = Integer.parseInt(newYearLine);
                book.setYear(newYear);
                break;
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid year, enter an integer.");
                // break;
            }
        }

        // Edition
        while (true) {
            System.out.print("Edition [" + book.getEdition() + "]: ");
            String newEditionLine = input.nextLine().trim();
            if (newEditionLine.isEmpty()) {
               // break;
            }
            
            try {
                int newEdition = Integer.parseInt(newEditionLine);
                book.setEdition(newEdition);
                break;
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid edition value; enter an integer.");
            }
        }

        // Publisher
        System.out.print("Publisher [" + book.getPublisher() + "]: ");
        String newPublisher = input.nextLine().trim();
        if (!newPublisher.isEmpty()) {
            book.setPublisher(newPublisher);
        }

        // ISBN
        System.out.print("Isbn [" + book.getIsbn() + "]: ");
        String newISBN = input.nextLine().trim();
        if (!newISBN.isEmpty()) {
            book.setIsbn(newISBN);
        }

        // Cover
        System.out.print("Cover [" + book.getCover() + "]: ");
        String newCover = input.nextLine().trim();
        if (!newCover.isEmpty()) {
            book.setCover(newCover);
        }

        // Condition
        System.out.print("Condition [" + book.getCondition() + "]: ");
        String newCondition = input.nextLine().trim();
        if (!newCondition.isEmpty()) {
            book.setCondition(newCondition);
        }

        // Price
        System.out.print("Price [" + book.getPrice() + "]: ");
        String newPrice = input.nextLine().trim();
        if (!newPrice.isEmpty()) {
            book.setPrice(newPrice);
        }

        // Notes
        System.out.print("Notes [" + book.getNotes() + "]: ");
        String newNotes = input.nextLine().trim();
        if (!newNotes.isEmpty()) {
            book.setNotes(newNotes); 
        }
       
        return book;
    }


    // Private Try Catch functionality:

    
    private static int readInteger(Scanner input) {
        while (true) {
            String S = input.nextLine();
            try {
                return Integer.parseInt(S);
            }
            catch (NumberFormatException e) {
                System.out.println("Enter an integer (ID NO):");
            }
        }
    }

//=-===========================================================================================//

    // private functionalities for Customer:

    
     private static Customer CreateANewCustomer() {

        int customer_id;
        String firstname;
        String secondName;
        String houseNo;
        String AddressLine1;
        String AddressLine2;
        String country; 
        String postCode; 
        

        Scanner in = new Scanner(System.in);        

        
        System.out.println("\nEnter an ID");
        customer_id = Integer.parseInt(in.nextLine().trim());
      
        System.out.println("\nEnter the firstName");
        firstname = in.nextLine().trim();
      
        System.out.println("\nEnter the secondName");
        secondName = in.nextLine().trim();
      
        System.out.println("\nEnter the houseNo");
        houseNo = in.nextLine().trim();
      
        System.out.println("\nEnter an addressLine1");
        AddressLine1 = in.nextLine().trim();
   
        System.out.println("\nEnter a addressLine2");
        AddressLine2 = in.nextLine().trim();

        System.out.println("\nEnter a country");
        country = in.nextLine().trim();

        System.out.println("\nEnter the postCode");
        postCode = in.nextLine().trim();
        
        return new Customer(customer_id,
                         firstname,
                         secondName,
                         houseNo,
                         AddressLine1,
                         AddressLine2,
                         country,
                         postCode
                       );                  
    
     }


     private static Customer UpdateACustomer(Customer customer) {

         Scanner input = new Scanner(System.in);
         System.out.println("Updating Customer " + customer.getCustomerID());

        // firstname 
         
        System.out.print("Firstname [" + customer.getFirstName() + "]: ");
        String newfirstname = input.nextLine().trim();
            if (!newfirstname.isEmpty()) {
                customer.setFirstName(newfirstname);
        }

        // secondname  
         
        System.out.print("SecondName [" + customer.getSecondName() + "]: ");
        String newsecondname = input.nextLine().trim();
            if (!newsecondname.isEmpty()) {
                customer.setSecondName(newsecondname);
        }
         
        // houseno 

            System.out.print("houseNo [" + customer.getHouseNo() + "]:");
            String newhouseno = input.nextLine().trim(); 
                if (!newhouseno.isEmpty()) {
                    customer.setHouseNo(newhouseno);
            }
         
        // addressLine1
         
        System.out.print("addressline1 [" + customer.getAddressLine1() + "]: ");
        String newaddress1 = input.nextLine().trim();
            if (!newaddress1.isEmpty()) {
                customer.setAddressLine1(newaddress1);
        }

        // addressline2
         
        System.out.print("addressline2 [" + customer.getAddressLine2() + "]: ");
        String newaddress2 = input.nextLine().trim();
            if (!newaddress2.isEmpty()) {
                customer.setAddressLine2(newaddress2);
        }

        // country
         
        System.out.print("Country [" + customer.getCountry() + "]: ");
        String newcountry = input.nextLine().trim();
            if (!newcountry.isEmpty()) {
                customer.setCountry(newcountry);
        }

        // postcode
         
        System.out.print("Postcode [" + customer.getPostcode() + "]: ");
        String newcPC = input.nextLine().trim();
            if (!newcPC.isEmpty()) {
                customer.setPostcode(newcPC);
        }
        return customer;    
     }

}


