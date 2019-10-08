<%--
  Created by IntelliJ IDEA.
  User: Анатолий
  Date: 28.09.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Обработка данных форм</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
        http-equiv="Cache-Control" content="no-cache">
  <style>
    body {
      font-family: Arial, Helvetica, sans-serif;
    }

    * {
      box-sizing: border-box;
    }

    input[type=text], input[type=password] {
      width: 10%;
      padding: 15px;
      margin: 5px 0 22px 0;
      display: inline-block;
      border: none;
      background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
      background-color: #ddd;
      outline: none;
    }

    hr {
      border: 1px solid #f1f1f1;
      margin-bottom: 25px;
    }

    a {
      color: dodgerblue;
    }

  </style>
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
      <td align="right" width="150">Имя:</td>
      <td><input type="text" name="TEXT_1" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Фамилия:</td>
      <td><input type="text" name="TEXT_2" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Email:</td>
      <td><input type="text" name="TEXT_3" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Пароль:</td>
      <td><input type="text" name="TEXT_4" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Подтверждение пароля:</td>
      <td><input type="text" name="TEXT_5" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Дата рождения:</td>
      <td><input type="text" name="TEXT_6" maxlength="50" size="20"></td>
    </tr>
    <tr>
      <td align="right">Пол:</td>
      <td><select type="select" name="TEXT_7" rows="1">
        <option>мужской</option>
        <option>женский</option>
        <option>не определилось</option>
      </select>
      </td>
    </tr>
    <tr>
      <td align="right">Хотите поговорить о баге?</td>
      <td>
        <input name="TEXT_8" type="radio" value="Аминь"> Аминь
        <input name="TEXT_8" type="radio" value="Алюминь"> Алюминь
        <input name="TEXT_8" type="radio" value="Нет" checked> Нет
      </td>
    </tr>
    <tr>
      <td align="right" valign="top">Комментарий</td>
      <td><textarea name="TEXT_9" cols="49" rows="10"></textarea></td>
    </tr>
  </table>
  <br>
  <input type="submit">
<%--  <c:if test="${}">--%>

<%--  </c:if>--%>
</form>
</body>
</html>
