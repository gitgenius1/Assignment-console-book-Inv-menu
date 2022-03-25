import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.sql.*;

public class DeleteHandler implements HttpHandler{
    public void handle(HttpExchange he) throws IOException {
        String query = he.getRequestURI().getQuery();
        System.out.println("Deleting book, query: " + query);
        Map<String, String> map = Util.requestStringToMap(query);
        if (map.containsKey("id")) {
            String idStr = map.get("id");
            System.out.println("Deleting book with id " + idStr);

            try {
                BookDAO db = new BookDAO();
                boolean deleted = db.deleteBook(Integer.parseInt(idStr));
                if (!deleted) {
                    System.out.println("Failed to delete book");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Cannot delete book with non-integer ID");
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        new AdministratorHandler().handle(he);
    }
}