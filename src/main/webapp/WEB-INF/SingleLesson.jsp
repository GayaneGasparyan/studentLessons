<%@ page import="com.example.studentLessons.model.Lesson" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%
    Lesson lesson = (Lesson) request.getAttribute("lesson");%>

<a href="/home">Go Home</a> | <a href="/logout">Logout</a>


<h1><%=lesson.getName()%>
</h1>
description: <%=lesson.getDescription()%> <br>

Student name: <%=lesson.getStudent().getName()%> <br>
Student surname: <%=lesson.getStudent().getSurname()%> <br>

</body>
</html>
