import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
//        writer.write("<h1>Hello, World!</h1>");
//        writer.write(req.getParameter("TEXT_1"));
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Обработка данных форм 2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Name: " + req.getParameter("TEXT_1") + "\n" +
                "Surname: " + req.getParameter("TEXT_2") + "\n" +
                "Date of Birth: " + req.getParameter("TEXT_3") + "\n" +
                "Gender: " + req.getParameter("TEXT_4") + "\n" +
                "About bug: " + req.getParameter("TEXT_6") + "\n" +
                "Comments: " + req.getParameter("TEXT_5") + "\n" +
                "</body>\n" +
                "</html>");
    }
}
