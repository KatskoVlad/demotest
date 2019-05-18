<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="css/choices.css">
    <link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
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
                                <a href="registration.jsp"><span>Регистрация</span></a>
                            </li>
                        </ul>
                        <div class="form-content tab-content">
                            <div id="tab15_1" class="tab-pane active">
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="action" value="authorization" /> Логин:<br />
                                    <input type="text" name="login" value="" /><br /> Пароль:<br />
                                    <input type="password" name="password" value="" /><br />
                                    <input type="submit" value="Войти" /> <br />
                                    <c:if test="${not empty  requestScope.errorMessage }">
                                        <script type="text/javascript" >alert("${  requestScope.errorMessage }");</script>
                                    </c:if>
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