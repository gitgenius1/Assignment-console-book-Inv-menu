import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.*;
import java.io.*;
import java.util.*;

public class ProcessUpdateBookHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        System.out.println("ProcessUpdateBookHandler");

          BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        
        String line;
        String request = "";
        while( (line = in.readLine()) != null ){
            request = request + line; 
        }

        System.out.println("Request: " + request);
        HashMap<String,String> params = Util.requestStringToMap(request); 
        System.out.println(params);

        BookDAO books = new BookDAO();

         int id = Integer.parseInt(params.get("book_id"));
         String title = params.get("title");; 
         String author = params.get("author");; 
         int year = Integer.parseInt(params.get("year"));
         int edition = Integer.parseInt(params.get("edition")); 
         String publisher = params.get("publisher");; 
         String isbn = params.get("isbn");; 
         String cover = params.get("cover");; 
         String condition = params.get("condition");; 
         String price = params.get("price"); 
         String notes = params.get("notes");; 
         System.out.println("about to update Book"); // Debugging message 
         Book book = new Book(id,title,author,year,edition,publisher,isbn,cover,condition,price,notes);
         System.out.println("Book to update" + book);

        try {
            books.updateBook(book);
            new AdministratorHandler().handle(he);
        }
        catch(SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}