import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.*;
import java.util.HashMap;

public class LoginActionHandler extends LoginHandler {
    private UserManager userManager = new UserManager();
    
    public void handle(HttpExchange he) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
        
        String line;
        String request = "";
        while( (line = in.readLine()) != null ){
            request = request + line; 
        }
        
        HashMap<String,String> map = Util.requestStringToMap(request); 


        BufferedWriter out = new BufferedWriter(  
                    new OutputStreamWriter(he.getResponseBody() ));
        he.sendResponseHeaders(200,0);

        if (userManager.checkUserExists(map.get("username"), map.get("password"))) {
            System.out.println("Credentials correct");

            out.write(
                "<html>" +
                "<head> <title>Welcome Admin</title> </head>" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                "<body>" +
                "<h1> Login Successful</h1>" +
                "<a href=\"/Admin\"> click here for Admin rights </a>" +   
                "<br/>" +   
                "</body>" +
                "</html>");
            out.close();
        } else {
            System.out.println("Credentials incorrect");

    
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
}   
