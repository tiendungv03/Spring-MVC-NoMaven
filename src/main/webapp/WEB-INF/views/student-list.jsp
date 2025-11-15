<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h2>Danh sách sinh viên</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Score</th>
    </tr>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.score}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="<c:url value='/student/form' />">Quay lại form</a></p>
</body>
</html>
