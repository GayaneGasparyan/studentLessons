<%@ page import="com.example.studentLessons.model.Lesson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<% String msg = (String) session.getAttribute("msg");%>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<% if (msg != null && !"".equals(msg)) { %>
<span><%=msg%></span>
<%
        session.removeAttribute("msg");
    }%>
<h1>AddLesson</h1> | <a href="/logout">Logout</a>


<form action="/updateLesson" method="post">
    <input type="hidden" name="id" value="<%=lesson.getId()%>" >
   name: <input type="text" name="name" value="<%=lesson.getName()%>"/> <br>
  description: <input type="text" name="description" value="<%=lesson.getDescription()%>"/> <br>
    <input type="submit" value="update lesson">
</form>
</body>
</html>

