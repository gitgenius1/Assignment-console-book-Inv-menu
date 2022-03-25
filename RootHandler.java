import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;

import java.io.BufferedWriter;
import java.io.IOException;

public class RootHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        String response = "Welcome to Book Library:";
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                             new OutputStreamWriter(he.getResponseBody()));

        
        BookDAO books = new BookDAO();
        
        try {
            ArrayList<Book> allBooks = books.getAllBooks();

            out.write(

              "<html>" +
                  "<head> <title>The Book Library</title> "+
                  "<link rel=\"stylesheet\"href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
              "</head>" +
                  "<body>" +
                  "<h1><b>The Book Collection</b>:</h1>"+ 
                  "<hr/>" +
                  "<p>" +
                  "<a href=\"/login\"> <i>Login</i> </a>" +  
                  "</p>" +
                  "<body>" +
                  "<table class=\"table\">" +
                  "<thead>" +
                  "  <tr>" +
                  "    <th><big>book_id</big></th>" +
                  "    <th>title</th>" +
                  "    <th>author</th>" +
                  "    <th>year</th>" +
                  "    <th>edition</th>" +
                  "    <th>publisher</th>" +
                  "    <th>isbn</th>" +
                  "    <th>cover</th>" +
                  "    <th>condition</th>" +
                  "    <th>price</th>" +
                  "    <th>notes</th>" +
                  "  </tr>" +
                  "</thead>" +
                  "<tbody>"
                        );

            // ArrayList B    
            for (Book B : allBooks){
                out.write(
                     "  <tr>"       +
                      "    <td>"+ B.getBook_id() + "</td>" +
                      "    <td>"+ B.getTitle() + "</td>" +
                      "    <td>"+ B.getAuthor() + "</td>" +
                      "    <td>"+ B.getYear() + "</td>" +
                      "    <td>"+ B.getEdition() + "</td>" +
                      "    <td>"+ B.getPublisher() + "</td>" +
                      "    <td>"+ B.getIsbn() + "</td>" +
                      "    <td>"+ B.getCover() + "</td>" +
                      "    <td>"+ B.getCondition() + "</td>" +
                      "    <td>"+ B.getPrice() + "</td>" +
                      "    <td>"+ B.getNotes() + "</td>" +                    
                      "  </tr>" 
                         );
                
            }
            out.write (
                "</tbody>" +
                "</table>" +
                "</body>" +
                "</html>");
            
            } catch (SQLException se){
                System.out.println(se.getMessage());
            }
        
            out.close();
        
    }
}