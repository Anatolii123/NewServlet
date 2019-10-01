import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";

    public static void connectToDB () throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");

        Class.forName(JDBC_DRIVER);

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
        System.out.println("Connection completed...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        try {
            connectToDB();
        } catch (Exception e) {
            System.out.println(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }
}
