<%@ page import="javax.servlet.annotation.WebServlet" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="Filters.NewFilter" %><%--
  Created by IntelliJ IDEA.
  User: Анатолий
  Date: 28.09.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset=\"utf-8\">
  <title>Обработка данных форм</title>
  </head>
<body>
<p>Имя: <%=(request.getParameter("TEXT_1") == "" ? "не заполнено": request.getParameter("TEXT_1"))%></p>
<p>Фамилия: <%=(request.getParameter("TEXT_2") == "" ? "не заполнено": request.getParameter("TEXT_2"))%></p>
<p>Дата рождения: <%=(request.getParameter("TEXT_3") == "" ? "не заполнено": request.getParameter("TEXT_3"))%></p>
<p>Пол: <%=request.getParameter("TEXT_4")%></p>
<p>О баге: <%=request.getParameter("TEXT_6")%></p>
<p>Комментарий: <%=request.getParameter("TEXT_5")%></p>
<form action="hello" method="post">
  <table width="200%" cellspacing="0" cellpadding="4">
    <tr>
      <td align="right" width="100">Имя:</td>
      <td><input type="text" name="TEXT_1" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Фамилия:</td>
      <td><input type="text" name="TEXT_2" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Дата рождения:</td>
      <td><input type="text" name="TEXT_3" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Пол:</td>
      <td><select type="select" name="TEXT_4" rows="1">
        <option>мужской</option>
        <option>женский</option>
        <option>не определилось</option>
      </select>
      </td>
    </tr>
    <tr>
      <td align="right">Хотите поговорить о баге?</td>
      <td>
        <input name="TEXT_6" type="radio" value="Аминь"> Аминь
        <input name="TEXT_6" type="radio" value="Алюминь"> Алюминь
        <input name="TEXT_6" type="radio" value="Нет" checked> Нет
      </td>
    </tr>
    <tr>
      <td align="right" valign="top">Комментарий</td>
      <td><textarea name="TEXT_5" cols="35" rows="10"></textarea></td>
    </tr>
  </table>
  <br>
  <input type="submit">
</form>
</body>
</html>
