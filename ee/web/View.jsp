<%--
  Created by IntelliJ IDEA.
  servlets.User: Анатолий
  Date: 28.09.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Обработка данных форм</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
        http-equiv="Cache-Control" content="no-cache">
<%--  <вынести стили в отдельный файл>--%>
<%--  выровнять через divы--%>
<%--  хранить сессию--%>
  <style>
    body{
      background-color: white;
      cursor: default;
    }
    body.Change{
      background: rgb(51,54,57);
    }
    body.Unchange{
      background: white;
    }
    form {
      font-size: 150%;
      font-family: Verdana, sans-serif;
    }
    form.Change{
      color: white;
    }
    form.Unchange{
      color: black;
    }
    table {
      border: white;
      position: absolute;
      /*top: calc(50% - 325px);*/
      /*left: calc(50% - 380px);*/
    }

    caption {
      font-size: 150%;
      font-family: Verdana, Arial, Helvetica, sans-serif;
      text-align: center
    }
    td.Change{
      borderColor: rgb(51,54,57);
      color: white;
    }
    td.Unchange{
      color: black;
    }
    .toggle {
      position: relative;
      display: block;
      width: 40px;
      height: 20px;
      cursor: pointer;
      -webkit-tap-highlight-color: transparent;
      transform: translate3d(0, 0, 0);
    }
    .toggle:before {
      content: "";
      position: relative;
      top: 3px;
      left: 3px;
      width: 34px;
      height: 14px;
      display: block;
      background: #9A9999;
      border-radius: 8px;
      transition: background 0.2s ease;
    }
    .toggle span {
      position: absolute;
      top: 0;
      left: 0;
      width: 20px;
      height: 20px;
      display: block;
      background: white;
      border-radius: 10px;
      box-shadow: 0 3px 8px rgba(154, 153, 153, 0.5);
      transition: all 0.2s ease;
    }
    .toggle span:before {
      content: "";
      position: absolute;
      display: block;
      margin: -18px;
      width: 56px;
      height: 56px;
      background: rgba(79, 46, 220, 0.5);
      border-radius: 50%;
      transform: scale(0);
      opacity: 1;
      pointer-events: none;
    }

    #cbx:checked + .toggle:before {
      background: #00BFFF;
    }
    #cbx:checked + .toggle span {
      background: #4285F4;
      transform: translateX(20px);
      transition: all 0.2s cubic-bezier(0.8, 0.4, 0.3, 1.25);
      box-shadow: 0 3px 8px rgba(79, 46, 220, 0.2);
    }
    #cbx:checked + .toggle span:before {
      transform: scale(1);
      opacity: 0;
      transition: all 0.4s ease;
    }
    .center {
      position: absolute;
      /*450, -800*/
      top: calc(50% - 450px);
      left: calc(50% - -800px);
    }

    input[id=logout] {
      position: absolute;
      /*450, -800*/
      top: calc(50% - 450px);
      left: calc(50% - -850px);
    }

    input[type=text]{
      padding: 15px;
      display: inline-block;
      border: none;
      background: #ddd;
      font-size: 100%;
      width: 20px;
      height: 20px;
      font-family: Verdana, Helvetica, sans-serif;
      text-align: center;
      border-collapse: collapse;
    }

    input[type=text]:focus{
      background-color: #ddd;
      outline: none;
    }

    table[name=size] {
      border: white;
      position: absolute;
      top: 440px;
      left: 0px;
    }

    input[id=calc] {
      position: absolute;
      top: 610px;
      left: 0px;
    }

    input[name=size11] {
      padding: 15px;
      display: inline-block;
      border: none;
      background: #ddd;
      font-size: 70%;
      width: 14px;
      height: 14px;
      font-family: Verdana, Helvetica, sans-serif;
      text-align: center;
      border-collapse: collapse;
    }

    input[name=size12] {
      padding: 15px;
      display: inline-block;
      border: none;
      background: #ddd;
      font-size: 70%;
      width: 14px;
      height: 14px;
      font-family: Verdana, Helvetica, sans-serif;
      text-align: center;
      border-collapse: collapse;
    }

    input[name=size21] {
      padding: 15px;
      display: inline-block;
      border: none;
      background: #ddd;
      font-size: 70%;
      width: 14px;
      height: 14px;
      font-family: Verdana, Helvetica, sans-serif;
      text-align: center;
      border-collapse: collapse;
    }

    input[name=size22] {
      padding: 15px;
      display: inline-block;
      border: none;
      background: #ddd;
      font-size: 70%;
      width: 14px;
      height: 14px;
      font-family: Verdana, Helvetica, sans-serif;
      text-align: center;
      border-collapse: collapse;
    }
    h4 {
      font-family: Verdana, Arial, Helvetica, sans-serif;
      color: red;
    }

  </style>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
  <script async src="main.js"></script>
  </head>
<body>
<h4><c:out value="${requestScope.CalcError ne null ? requestScope.CalcError : ''}"/></h4>
<div style="display: grid">
<form name="account" id="account" action="LogOut">
  <h3><c:out value="${requestScope.registration ne null ? requestScope.registration : ''}"/></h3>
  <p>Имя: <c:out value="${requestScope.name eq '' ? 'не заполнено' : requestScope.name}"/></p>
  <p>Фамилия: <c:out value="${requestScope.surname eq '' ? 'не заполнено' : requestScope.surname}"/></p>
  <p>Email: <c:out value="${requestScope.email eq '' ? 'не заполнено' : requestScope.email}"/></p>
  <p>Дата рождения: <c:out value="${requestScope.dateOfBirth eq '' ? 'не заполнено' : requestScope.dateOfBirth}"/></p>
  <p>Пол: <c:out value="${requestScope.gender eq '' ? 'не заполнено' : requestScope.gender}"/></p>
  <p >О баге: <c:out value="${requestScope.bug eq '' ? 'не заполнено' : requestScope.bug}"/></p>
  <p>Комментарий: <c:out value="${requestScope.comments eq '' ? 'не заполнено' : requestScope.comments}"/></p>
  <input type="submit" value="Выйти" id="logout">
</form>
</div>
<br><br><br>
<div style="display: grid">
<form name="mtx" id="mtx" action="MatrixCalc" method="post">
  <div>
    <table name="size">
      <tr>
        <td><b>Матрица 1: </b></td>
        <td><input type="text" name="size11" id="sz11" size="5" oninput="fun()"
                   value="4"
                   onkeyup="return check(this);" onchange="return check(this);"></td>
        <td align="center"><b>x</b></td>
        <td><input type="text" name="size12" id="sz12" size="5" oninput="fun()" value="4"
                   onkeyup="return check(this);" onchange="return check(this);"></td>
      </tr>
      <tr>
        <td><b>Матрица 2:</b></td>
        <td><input type="text" name="size21" id="sz21" size="5" oninput="fun2()" value="4"
                   onkeyup="return check(this);" onchange="return check(this);"></td>
        <td align="center"><b>x</b></td>
        <td><input type="text" name="size22" id="sz22" size="5" oninput="fun2()" value="4"
                   onkeyup="return check(this);" onchange="return check(this);"></td>
      </tr>
      <tr>
        <td><b>Операция: </b></td>
        <td><input name="Operation" type="radio" value="Sum" checked>+</td>
        <td><input name="Operation" type="radio" value="Sub">-</td>
        <td><input name="Operation" type="radio" value="Mult">*</td>
      </tr>
    </table>
  </div>
  <div style="display: table">
    <div style="display: table-row">
      <div style="display: table-cell"><table name="matrix1" id="matrix1" ></table></div>
      <div style="display: table-cell"><table name="matrix2" id="matrix2" ></table></div>
    </div>
  </div>
  <input type="submit" value="Вычислить" id="calc">
</form>
</div>
<table>

<%--  <c:forEach var="i" begin = "1" end = "${}">--%>
<%--    <tr>--%>
<%--      <td><input type="text" maxlength="50" size="5" name="11"--%>
<%--                 onkeyup="return check(this);" onchange="return check(this);"></td>--%>
<%--    </tr>--%>
<%--  </c:forEach>--%>
</table>
<table></table>
<div class="center">
  <input type="checkbox" id="cbx" style="display:none"/>
  <label for="cbx" class="toggle"><span></span></label>
</div>
</form>
</body>
</html>
