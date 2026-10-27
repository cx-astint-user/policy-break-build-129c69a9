import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vulnerable extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String command = request.getParameter("command");
        Runtime.getRuntime().exec(command);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/app");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE name = '" + userName + "'");
            response.getWriter().println("Hello " + userName + result.getString(1));
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}
