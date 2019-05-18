<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 05.02.2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>

<form action="FrontController" method=post>
    <ul class="form-tabs nav nav-tabs nav-justified">
        <li>
            <a href="index.jsp"><span>Авторизация</span></a>
        </li>
    </ul>
    <input type="hidden" name="action" value="registration"/>
        <table cellpadding=4 cellspacing=2 border=0>

            <th bgcolor="#CCCCFF" colspan=2>
                <font size=5>Форма регистрации клиента</font>
                <br>
                <font size=1>* Поля обязательные для ввода</font>
            </th>
            <tr bgcolor="#c8d8f8">
                <td  valign=top>
                    * Логин
                    <input type="text" name="login" value="" size=20 maxlength=20></td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    * Имя
                    <input type="text" name="name" value="" size=20 maxlength=20></td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    * Фамилия
                    <input type="text" name="surname" value="" size=35  maxlength=35></td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    * E-Mail

                    <input type="text" name="email" value="" size=25  maxlength=100>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    * Пароль

                    <input type="password" name="password1" size=20 value=""
                           maxlength=20></td>
            </tr>
            <tr bgcolor="#c8d8f8">
            <td  valign=top>
                * Повторите пароль
                <input type="password" name="password2" size=20 value="" maxlength=20>
            </td>
            </tr>

            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    * Роль
                    <input type="text" name="role" size=20 value=""  maxlength=20>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td valign=top>
                    Пол *
                    <br>
                    Мужской
                    <input type= "radio" name="MALE" value="M"  maxlength=1>
                    Женский
                    <input type= "radio" name="FEMALE" value="Ж"  maxlength=1>
                </td>
            </tr>
            <tr bgcolor="#c8d8f8">
                <td  align=center colspan=2>
                    <input type="submit" value="Registration">
                    <input type="reset" value="Reset">
                </td>
            </tr>
        </table>
    <c:if test="${not empty  requestScope.errorMessage }">
        <script type="text/javascript" >alert("${  requestScope.errorMessage }");</script>
    </c:if>

</form>
</body>
</html>
