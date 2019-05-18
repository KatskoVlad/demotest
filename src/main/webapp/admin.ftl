<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Перевод с карты на карту</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="./css/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="./css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="./css/theme.css">
</head>
<body class="d-body">

<%--<input type="hidden" name="command" value="authorization" /><br />--%>
<%--<c:if test="${not empty  requestScope.errorMessage }">--%>
    <%--<c:out value="${  requestScope.errorMessage }" />--%>
<%--</c:if>--%>

<div class="main container">
    <div class="row">
        <div class="r-block col-md-8">

            <!-- Start: left block -->
            <div class="l-block">
                <div class="admin-form">

                    <div class="tab-block mb25">
                        <ul class="form-tabs nav nav-tabs nav-justified">
                            <li class="active">
                                <a href="" data-toggle="tab"><span>Данные авторизации</span></a>
                            </li>

                            <li>
                                <a href="" data-toggle="tab"><span>Регистрация</span></a>
                            </li>

                        </ul>
                        <div class="form-content tab-content">
                            <div id="tab15_1" class="tab-pane active">

                                <form class="login-form form-input" action="https://" method="POST">
                                    <input name="login" placeholder="Пользователь" autocomplete="off" class="form-control" value="">
                                    <input type="password" placeholder="Пароль" autocomplete="off" name="password" class="form-control" value="">
                                    <button type="button" class="form-but">Войти</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>