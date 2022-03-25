import java.sql.*;
import java.util.*;

// 1)Import the package
// 2)Load and Register driver
// 3)Create Connection getDBConnection
// 4)Create Statement
// 5)Execute the query
// 6)process the results
// 7)close connection
// encapsulation 

// private static BookDAO theBookDAO;
//     ArrayList<Book> books = new List<Book>();
//     private BookDAO(){
        
//     }
//     public static BookDAO getInstance(){
//         if(theBookDAO == null){
//             theBookDAO = new BookDao();
//         }
//         return theBookDAO;
//         }

public class BookDAO {
    private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		try {
			String dbURL = "jdbc:sqlite:books.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return dbConnection;
	}

    
    public ArrayList<Book> getAllBooks() throws SQLException {
		System.out.println("[BookDAO] Retrieving all books...");
		ArrayList<Book> books = new ArrayList<>();

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM books;");
        ) {
            while (result.next()) { // loop over bufferreader
				books.add(new Book(
                    result.getInt("ID"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getInt("year"),
                    result.getInt("edition"),
                    result.getString("publisher"),
                    result.getString("isbn"),
                    result.getString("cover"),
                    result.getString("condition"),
                    result.getString("price"),
                    result.getString("notes")
                ));
			}
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to retrieve books: " + e.getMessage());
        }
        return books;
	}

    public Book getBook(int id) throws SQLException {
        System.out.println("[BookDAO] Retrieving book " + id + "...");
		Book book = null; // nullpointer book

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM books WHERE ID=" + id + ";");
        ) {
            while (result.next()) {
				book = new Book(
                    result.getInt("ID"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getInt("year"),
                    result.getInt("edition"),
                    result.getString("publisher"),
                    result.getString("isbn"),
                    result.getString("cover"),
                    result.getString("condition"),
                    result.getString("price"),
                    result.getString("notes")
                );
			}
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to retrieve book: " + e.getMessage());
        }

        return book;   
    }

    public boolean deleteBook(int id) throws SQLException {
        System.out.println("[BookDAO] Deleting book " + id + "...");

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
        ) {
            statement.execute("DELETE FROM books WHERE ID=" + id + ";");
            return true;
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to delete book: " + e.getMessage());
        }

        return false;
    }

    public boolean insertBook(Book book) throws SQLException {
        System.out.println("[BookDAO] Inserting book " + book.getBook_id() + "...");

        StringBuilder values = new StringBuilder();
        values.append("'" + book.getTitle() + "'" + ",");
        values.append("'" + book.getAuthor() + "'" + ",");
        values.append(book.getYear() + ",");
        values.append(book.getEdition() + ",");
        values.append("'" + book.getPublisher() + "'" +  ",");
        values.append("'" + book.getIsbn() + "'" +  ",");
        values.append("'" + book.getCover() + "'" + ",");
        values.append("'" + book.getCondition() + "'" + ",");
        values.append(book.getPrice() + ",");
        values.append("'" + book.getNotes() + "'");

        String query = "INSERT INTO books(Title, Author, Year, Edition, Publisher, ISBN, Cover, Condition, Price, Notes) VALUES(" + values + ");";
        System.out.println("[BookDAO] Query: " + query);
        
        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
        ) {
            statement.execute(query);
            System.out.println("[BookDAO] Added book OK");
            return true;
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to insert book: " + e.getMessage());
        }
        return false;
    }

    public boolean updateBook(Book book) throws SQLException {
        System.out.println("[BookDAO] Updating book " + book.getBook_id() + "...");
        
        StringBuilder stmt = new StringBuilder("UPDATE books SET");
        stmt.append(" ID = " + book.getBook_id() + ",");
        stmt.append(" Title = '" + book.getTitle() + "'" + ",");
        stmt.append(" Author = '" + book.getAuthor() + "'" + ",");
        stmt.append(" Year = " + book.getYear() + ",");
        stmt.append(" Edition = " + book.getEdition() + ",");
        stmt.append(" Publisher = '" + book.getPublisher() + "'" + ",");
        stmt.append(" ISBN = '" + book.getIsbn() + "'" + ",");
        stmt.append(" Cover = '" + book.getCover() + "'" + ",");
        stmt.append(" Condition = '" + book.getCondition() + "'" + ",");
        stmt.append(" Price = '" + book.getPrice() + "'" + ",");
        stmt.append(" Notes = '" + book.getNotes() + "'");
        
        stmt.append(" WHERE ID = " + book.getBook_id());

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
        ) {
            statement.execute(stmt.toString());
            return true;
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to update book: " + e.getMessage());
        }
        return false;
    }

    public boolean checkLoginCredentials(String username, String password) {
        return true;
    }

    public int checkUserPrivilages(String username, String password) {
        return -1;
    }

//=========================================================================================

                                // Customer DB in BOOKSQLITE: 
    
    public ArrayList<Customer> getAllCustomers() throws SQLException {
		System.out.println("[BookDAO] Retrieving all Customers...");
		ArrayList<Customer> customers = new ArrayList<>();

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Customer;");
        ) {
            while (result.next()) { // loop over bufferreader
				customers.add(new Customer(
                    result.getInt("customer_id"),
                    result.getString("firstName"),
                    result.getString("secondName"),
                    result.getString("houseNo"),
                    result.getString("addressLine1"),
                    result.getString("addressLine2"),
                    result.getString("country"),
                    result.getString("postCode")
                ));
			}
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to retrieve Customer: " + e.getMessage());
        }
        return customers;
	}

//========================================================================================
    
        public Customer getCustomerByID(int customer_id) throws SQLException {
            System.out.println("[BookDAO] Retrieving book " + customer_id + "...");
		        Customer customerbyID = null; // nullpointer customer

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Customer WHERE customer_id=" + customer_id + ";");
        ) {
           
            while (result.next()) {
				customerbyID = new Customer(
                    result.getInt("customer_id"),
                    result.getString("firstName"),
                    result.getString("secondName"),
                    result.getString("houseNo"),
                    result.getString("addressLine1"),
                    result.getString("addressLine2"),
                    result.getString("country"),
                    result.getString("postCode")
                
                );
			}
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to retrieve customer_id: " + e.getMessage());
        }

        return customerbyID;   
    }

//=========================================================================================
    
     public boolean insertCustomer(Customer customer) throws SQLException {
        System.out.println("[BookDAO] Inserting Customer " + customer.getCustomerID() + "...");

        StringBuilder values = new StringBuilder();
        values.append("'" + customer.getFirstName() + "'" + ",");
        values.append("'" + customer.getSecondName() + "'" + ",");
        values.append("'" + customer.getHouseNo() + "'" + ",");
        values.append("'" + customer.getAddressLine1() + "'" +  ",");
        values.append("'" + customer.getAddressLine2() + "'" +  ",");
        values.append("'" + customer.getCountry() + "'" +  ",");
        values.append("'" + customer.getPostcode()  + "'");
        
        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
        ) {
            statement.execute("INSERT INTO Customer(firstName, secondName, houseNo, addressLine1, addressLine2, country, postCode) VALUES(" + values + ")");
            return true;
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to insert Customer: " + e.getMessage());
        }
        return false;
    }

//=========================================================================================

    public boolean updateCustomer(Customer customer) throws SQLException {
        System.out.println("[BookDAO] Updating Customer " + customer.getCustomerID() + "...");
        
        StringBuilder stmt = new StringBuilder("UPDATE Customer SET");
        stmt.append(" firstName = '" + customer.getFirstName() + "'" + ",");
        stmt.append(" secondName = '" + customer.getSecondName() + "'" + ",");
        stmt.append(" houseNo = '" + customer.getHouseNo() + "'" + ",");
        stmt.append(" addressLine1 = '" + customer.getAddressLine1() + "'" + ",");
        stmt.append(" addressLine2 = '" + customer.getAddressLine2() + "'" + ",");
        stmt.append(" country = '" + customer.getCountry() + "'" + ",");
        stmt.append(" postCode = '" + customer.getPostcode() + "'");
        stmt.append("WHERE customer_id = " + customer.getCustomerID());
        
        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
        ) {
            statement.execute(stmt.toString());
            return true;
        }
        catch (Exception e) {
            System.out.println("[BookDAO] Failed to update Customer: " + e.getMessage());
        }
        return false;
    }

//=========================================================================================
    
    public boolean DeleteCustomerID(int id) throws SQLException {
        System.out.println("[BookDAO] Deleting ID " + id +"...." );
     
        try (
            Connection dbconnection = getDBConnection();
            Statement statement = dbconnection.createStatement();
        ) {
            statement.execute("DELETE FROM Customer WHERE id=" + id + ";");
            return true; 
        }    
        catch (Exception e) {
            System.out.println("Failed to delete Customer" + e.getMessage());
        } 
        return false;
    }
   
}