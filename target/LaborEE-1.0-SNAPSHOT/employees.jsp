<%--
  Created by IntelliJ IDEA.
  User: hamza
  Date: 03/10/2024
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        body {
            font-family: sans-serif;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            margin-bottom: 10px;
        }
        input[type="text"], input[type="email"], input[type="tel"], select {
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
        .employee-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .employee-table th, .employee-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .employee-table th {
            background-color: #f2f2f2;
        }
        .employee-table tr:nth-child(even) {
            background-color: #f9f9f9;
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
    <h1>Employee List</h1>

    <a href="${pageContext.request.contextPath}/employees/add">Add New Employee</a>

    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/employees/list" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" placeholder="Search by name, email, phone, or position">
        <button type="submit">Search</button>
    </form>

    <!-- Filter by Department -->
    <form action="${pageContext.request.contextPath}/employees/list" method="get">
        <input type="hidden" name="action" value="filterDepartment">
        <select name="departmentId">
            <option value="">All Departments</option>
            <c:forEach items="${departments}" var="department">
                <option value="${department.id}">${department.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Filter</button>
    </form>

    <!-- Filter by Position -->
    <form action="${pageContext.request.contextPath}/employees/list" method="get">
        <input type="hidden" name="action" value="filterPosition">
        <input type="text" name="position" placeholder="Filter by position">
        <button type="submit">Filter</button>
    </form>

    <!-- Employee Table -->
    <table class="employee-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Position</th>
            <th>Department Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.phone}</td>
                <td>${employee.position}</td>
                <td>${employee.department.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees/edit?id=${employee.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/employees/delete?id=${employee.id}"
                       onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>