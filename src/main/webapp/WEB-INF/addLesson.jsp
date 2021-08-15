<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<% String msg = (String) session.getAttribute("msg");%>
<% if (msg != null && !"".equals(msg)) { %>
<span><%=msg%></span>
<%
        session.removeAttribute("msg");
    }%>
<h1>AddLesson</h1> | <a href="/logout">Logout</a>


<form action="/addLesson" method="post">
    name: <input type="text" name="title"/> <br>
   description: <input type="text" name="authorName"/> <br>

    <input type="submit" value="add lesson">
</form>
</body>
</html>
