<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 04/10/2024
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
    <style>
        body {
            font-family: sans-serif;
        }

        .container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        form div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="tel"],
        select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        button {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Edit Employee</h1>

    <form action="${pageContext.request.contextPath}/employees/edit" method="post">
        <input type="hidden" name="id" value="${employee.id}">

        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${employee.name}" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${employee.email}" required>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" value="${employee.phone}">
        </div>
        <div>
            <label for="position">Position:</label>
            <input type="text" id="position" name="position" value="${employee.position}">
        </div>
        <div>
            <label for="department">Department:</label>
            <select id="department" name="departmentId" required>
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}"
                            <c:if test="${employee.department.id == department.id}">selected</c:if>>
                            ${department.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit">Update Employee</button>
    </form>

    <a href="${pageContext.request.contextPath}/employees/list">Back to Employee List</a>
</div>
</body>
</html>
