<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 13.02.2019
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=windows-1256">
    <title>   User Logged Successfully   </title>
    <link href="bootstrap.css" type="text/css" rel="stylesheet">
</head>

<body>

<center>
    <div class="alert alert-info">
        <strong>    <h1>SERVER MONITORING SERVICE</h1></strong>
    </div>

    <% String currentUser = (String) (session.getAttribute("currentSessionUser"));%>
    <div class="alert alert-success">
        <strong> Welcome <%= currentUser %></strong> <br>Enter the Dragon.
    </div>
    <br>
    <%
        response.setIntHeader("Refresh", 50);
        String jspPath = "C:\\";
        String fileName = "log.txt";
        String txtFilePath = jspPath + fileName;
        BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
        //BufferedReader br = new InputStreamReader(new FileInputStream(txtFilePath));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine())!= null){
            sb.append(line+"\n");
        }
        // out.println(sb.toString());
    %>


    <%--<input type="text" value="<%= sb.toString() %>" />--%>
    <textarea rows="20" cols="79">
<%=sb.toString()%>
</textarea>

</center>

</body>

</html>
