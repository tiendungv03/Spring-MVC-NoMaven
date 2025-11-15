<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Form</title>
</head>
<body>
<h2>Nhập sinh viên</h2>

<form action="<c:url value='/student/save' />" method="post">
    ID: <input type="number" name="id"/><br/>
    Name: <input type="text" name="name"/><br/>
    Email: <input type="email" name="email"/><br/>
    Score: <input type="number" step="0.1" name="score"/><br/>
    <button type="submit">Lưu</button>
</form>

<p>
    <a href="<c:url value='/student/list' />">Xem danh sách từ DB</a> |
    <a href="<c:url value='/student/from-txt' />">Xem từ TXT</a> |
    <a href="<c:url value='/student/from-json' />">Xem từ JSON</a>
</p>

</body>
</html>
