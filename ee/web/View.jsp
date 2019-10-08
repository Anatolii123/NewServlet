<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Анатолий
  Date: 28.09.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cd" %>
<!DOCTYPE html>
<html>
<head>
  <title>Обработка данных форм</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
        http-equiv="Cache-Control" content="no-cache">
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
      top: calc(50% - 325px);
      left: calc(50% - 380px);
    }
    caption {
      font-size: 150%;
      font-family: Verdana, Arial, Helvetica, sans-serif;
      text-align: center
    }
    /*td {*/
    /*  font-size: 120%;*/
    /*  font-family: Verdana, Helvetica, sans-serif;*/
    /*  text-align: center;*/
    /*  border-collapse: collapse;*/
    /*  transition: .5s;*/
    /*  background-color: #ddd;*/
    /*  border: #ddd;*/
    /*  width: 40px;*/
    /*  height: 40px;*/
    /*}*/
    /*td:hover {*/
    /*  font-size: 100%;*/
    /*  font-weight: 600;*/
    /*  color: white;*/
    /*  border: #4285F4;*/
    /*  background-color: #4285F4;*/
    /*  transition: .5s;*/
    /*  width: 60px;*/
    /*  height: 60px;*/
    /*}*/
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
    input[type=text]{
      width: 30%;
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
  </style>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
  <script async src="main.js"></script>
  </head>
<body>
<form name="account" id="account">
  <h3><%=request.getAttribute("registration") != null ? request.getAttribute("registration"):""%></h3>
  <p>Имя: <%=(request.getAttribute("name") == "" ? "не заполнено": request.getAttribute("name"))%></p>
  <p>Фамилия: <%=(request.getAttribute("surname") == "" ? "не заполнено": request.getAttribute("surname"))%></p>
  <p>Email: <%=(request.getAttribute("email") == "" ? "не заполнено": request.getAttribute("email"))%></p>
  <p>Дата рождения: <%=(request.getAttribute("dateOfBirth") == "" ? "не заполнено": request.getAttribute("dateOfBirth"))%></p>
  <p>Пол: <%=(request.getAttribute("gender") == "" ? "не заполнено": request.getAttribute("gender"))%></p>
  <p >О баге: <%=(request.getAttribute("bug") == "" ? "не заполнено": request.getAttribute("bug"))%></p>
  <p>Комментарий: <%=(request.getAttribute("comments") == "" ? "не заполнено": request.getAttribute("comments"))%></p>
</form>
<form name="myform" id="form" oninput="range1value.value = range1.valueAsNumber">
  <input name="range1" type="range" id="r1" step="1" min="1" oninput="fun()" max="16" list="tickmarks" value="4">
  <output name="range1value" for="range1">4</output>
</form>
<datalist id="tickmarks">
  <option value="1">
  <option value="2">
  <option value="3">
  <option value="4">
  <option value="5">
  <option value="6">
  <option value="7">
  <option value="8">
  <option value="9">
  <option value="10">
  <option value="11">
  <option value="12">
  <option value="13">
  <option value="14">
  <option value="15">
  <option value="16">
</datalist>
<form name="myform2" id="form2" oninput="range2value.value = range2.valueAsNumber">
  <input name="range2" type="range" id="r2" step="1" min="1" oninput="fun2()" max="16" list="tickmarks2" value="4">
  <output name="range2value" for="range2" id="rv2">4</output>
</form>
<datalist id="tickmarks2">
  <option value="1">
  <option value="2">
  <option value="3">
  <option value="4">
  <option value="5">
  <option value="6">
  <option value="7">
  <option value="8">
  <option value="9">
  <option value="10">
  <option value="11">
  <option value="12">
  <option value="13">
  <option value="14">
  <option value="15">
  <option value="16">
</datalist>
<div class="center">
  <input type="checkbox" id="cbx" style="display:none"/>
  <label for="cbx" class="toggle"><span></span></label>
</div>
<%--  <c:if test="${}">--%>

<%--  </c:if>--%>
</form>
</body>
</html>
