<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import =" dblib.Dblib, java.util.* " %>
<html>
<head>
    <title>Classes</title>
</head>
<body>
<%
    Dblib db = new Dblib("ism6236", "ism6236bo");
    List<String> sectionId = db.List(Integer.parseInt("year"), "semester");
%>
</body>
</html>
