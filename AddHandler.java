import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class AddHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
   
        System.out.println("Add Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(  
            new OutputStreamWriter(he.getResponseBody() ));
    
        BookDAO books = new BookDAO();

        out.write(
            "<html>" +
            "<head> <title>Book Library</title> "+
             "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
            "</head>" +
            "<body>" +
            "<div class=\"container\">"+
            "<h1>Add Book</h1>"+
            "<form method=\"POST\" action=\"/processAdd\">" +
            "<div class=\"form-group\"> "+
            
            "<label for=\"title\">Title</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"title\" id=\"title\"> " + 
            
            "<label for=\"author\">Author</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"author\" id=\"author\"> " + 
            
            "<label for=\"year\">Year</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"year\" id=\"year\"> " + 
            
            "<label for=\"edition\">Edition</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"edition\" id=\"edition\" >" +
            
            "<label for=\"publisher\">Publisher</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"publisher\" id=\"publisher\"> " +
            
            "<label for=\"isbn\">ISBN</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"isbn\" id=\"isbn\"> " + 
             
            "<label for=\"cover\">Cover</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"cover\" id=\"cover\"> " +  
            
            "<label for=\"price\">Price</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" >" +   
            
            "<label for=\"condition\">Condition</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"condition\" id=\"condition\"> " + 

            "<label for=\"notes\">Notes</label> " + 
            "<input type=\"text\" class=\"form-control\" name=\"notes\" id=\"notes\"> " + 

            "<p>" + 
            "</p>" +
            
            "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " + 
            "</div>" + 
            "</form>" +
            "<a href=\"/Admin\">Back to List </a>"+
            "</div>" +
            "</body>" +
            "</html>"
        );
   
        out.close();
    }
}