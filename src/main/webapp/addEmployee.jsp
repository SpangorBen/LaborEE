<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 03/10/2024
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
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

        form div { /* Style each form field container */
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
            width: 100%; /* Make input fields fill the container width */
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
    <h1>Add New Employee</h1>

    <form action="${pageContext.request.contextPath}/employees/add" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone">
        </div>
        <div>
            <label for="position">Position:</label>
            <input type="text" id="position" name="position">
        </div>
        <div>
            <label for="department">Department:</label>
            <select id="department" name="departmentId" required>
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit">Add Employee</button>
    </form>

    <a href="${pageContext.request.contextPath}/employees/list">Back to Employee List</a> </div>
</body>
</html>