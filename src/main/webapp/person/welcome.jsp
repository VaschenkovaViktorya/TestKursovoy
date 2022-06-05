<%--
  Created by IntelliJ IDEA.
  User: gorvi
  Date: 04.06.2022
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String name = request.getParameter("uname");
out.print("Привет " + name);
%>
