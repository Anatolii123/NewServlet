<%--
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
<p>Имя: <%=(request.getAttribute("name") == "" ? "не заполнено": request.getAttribute("name"))%></p>
<p>Фамилия: <%=(request.getAttribute("surname") == "" ? "не заполнено": request.getAttribute("surname"))%></p>
<p>Email: <%=(request.getAttribute("email") == "" ? "не заполнено": request.getAttribute("email"))%></p>
<p>Пароль: <%=(request.getAttribute("password") == "" ? "не заполнено": request.getAttribute("password"))%></p>
<p>Дата рождения: <%=(request.getAttribute("dateOfBirth") == "" ? "не заполнено": request.getAttribute("dateOfBirth"))%></p>
<p>Пол: <%=(request.getAttribute("gender") == "" ? "не заполнено": request.getAttribute("gender"))%></p>
<p>О баге: <%=(request.getAttribute("bug") == "" ? "не заполнено": request.getAttribute("bug"))%></p>
<p>Комментарий: <%=(request.getAttribute("comments") == "" ? "не заполнено": request.getAttribute("comments"))%></p>
<form action="Controller" method="post">
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
