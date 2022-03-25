import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

public class LoginHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    he.sendResponseHeaders(200,0);
    BufferedWriter out = new BufferedWriter(  
        new OutputStreamWriter(he.getResponseBody() ));
    
    out.write(
      "<html>" +
      "<head> <title>The Book Shop</title> </head>" +
      "<body>" +
      "<form method=\"Post\" action=\"/loginaction\">" +
      "<label>Username:</label>" +
      "<input name=\"username\"> <br \\> "+ 
      "<label>Password:</label>" +
      "<input name=\"password\">  <br \\>"+ 
      "<input type=\"submit\" value=\"Login\">  "+
      "</form>" + 
      "<a href=\"/\"> click here to return to main page </a" +   
      "</body>" +
      "</html>");
    
    out.close();
    }   
}