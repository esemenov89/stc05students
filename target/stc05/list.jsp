<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>

    <form method="get">
        <td><input type="button" value="Добавить" onclick="location.href = '<%= request.getContextPath() %>/student'"></td>

        <table>
        <c:forEach items="${list}" var="student">
            <tr>
                <td><c:out value="${student.id}"></c:out></td>
                <td><c:out value="${student.name}"></c:out></td>
                <td><c:out value="${student.age}"></c:out></td>
                <td><c:out value="${student.groupId}"></c:out></td>
                <td><input type="button" value="Редактировать" onclick="location.href = '<%= request.getContextPath() %>/student?id=<c:out value="${student.id}" />'"></td>
                <td><input type="button" name="edit" value="Удалить" onclick="location.href = '/delete=<c:out value="${student.id}" />'"></td>
            </tr>
        </c:forEach>
        </table>
    </form>
    <a href="<c:url value="/j_spring_security_logout" />" > Logout</a></h2>
</body>
</html>
