import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        Enumeration<String> parameterNames = req.getParameterNames();
        writer.write(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>Обработка данных форм</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Имя: " + (req.getParameter("TEXT_1") == "" ? "не заполнено": req.getParameter("TEXT_1")) + "</p>" +
                "<p>Фамилия: " + (req.getParameter("TEXT_2") == "" ? "не заполнено": req.getParameter("TEXT_2")) + "</p>" +
                "<p>Дата рождения: " + (req.getParameter("TEXT_3") == "" ? "не заполнено": req.getParameter("TEXT_3")) + "</p>" +
                "<p>Пол: " + req.getParameter("TEXT_4") + "</p>" +
                "<p>О баге: " + req.getParameter("TEXT_6") + "</p>" +
                "<p>Комментарий: " + req.getParameter("TEXT_5") + "</p>" +
                        "<form action=\"hello\" method=\"post\">\n" +
                        "    <table width=\"200%\" cellspacing=\"0\" cellpadding=\"4\">\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\" width=\"100\">Имя:</td>\n" +
                        "            <td><input type=\"text\" name=\"TEXT_1\" maxlength=\"50\" size=\"20\"></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\">Фамилия:</td>\n" +
                        "            <td><input type=\"text\" name=\"TEXT_2\" maxlength=\"50\" size=\"20\"></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\">Дата рождения:</td>\n" +
                        "            <td><input type=\"text\" name=\"TEXT_3\" maxlength=\"50\" size=\"20\"></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\">Пол:</td>\n" +
                        "            <td><select type=\"select\" name=\"TEXT_4\" rows=\"1\">\n" +
                        "                <option>мужской</option>\n" +
                        "                <option>женский</option>\n" +
                        "                <option>не определилось</option>\n" +
                        "            </select>\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\">Хотите поговорить о баге?</td>\n" +
                        "            <td>\n" +
                        "                <input name=\"TEXT_6\" type=\"radio\" value=\"Аминь\"> Аминь\n" +
                        "                <input name=\"TEXT_6\" type=\"radio\" value=\"Алюминь\"> Алюминь\n" +
                        "                <input name=\"TEXT_6\" type=\"radio\" value=\"Нет\" checked> Нет\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td align=\"right\" valign=\"top\">Комментарий</td>\n" +
                        "            <td><textarea name=\"TEXT_5\" cols=\"35\" rows=\"10\"></textarea></td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <br>\n" +
                        "    <input type=\"submit\">\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
    }
}
