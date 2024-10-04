package com.labor.laboree.servlets;

import com.labor.laboree.dao.interfaces.DepartmentDAO;
import com.labor.laboree.dao.interfaces.EmployeeDAO;
import com.labor.laboree.models.Department;
import com.labor.laboree.models.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmployeeServlet.class.getName());
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public void init() throws ServletException {

        employeeDAO = (EmployeeDAO) getServletContext().getAttribute("employeeDAO");
        departmentDAO = (DepartmentDAO) getServletContext().getAttribute("departmentDAO");
        if (employeeDAO == null || departmentDAO == null) {
            throw new ServletException("DAO is not initialized");
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getPathInfo();
        logger.severe("action: " + action);
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "/list":
                listEmployee(req, res);
                break;
            case "/add":
                showAddEmployee(req, res);
                break;
            case "/edit":
                 showEditEmployee(req, res);
                break;
            case "/delete":
                deleteEmployee(req, res);
                break;
            default:
                listEmployee(req, res);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        switch (action) {
            case "/add":
                try {
                    addEmployee(request, response);
                } catch (Exception e) {
                    handleException(response, "Error adding employee", e);
                }
                break;
            case "/edit":
                try {
                    updateEmployee(request, response);
                } catch (Exception e) {
                    handleException(response, "Error updating employee", e);
                }
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().println("Action not found.");
        }
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        logger.severe("deleteEmployee" + req.getParameter("id"));
        Integer employeeId = Integer.valueOf(req.getParameter("id"));
        employeeDAO.deleteEmployee(employeeId);

        res.sendRedirect(req.getContextPath() + "/employees/list");
    }

    private void showAddEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("/addEmployee.jsp").forward(req, res);
    }

    private void showEditEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer employeeId = Integer.valueOf(request.getParameter("id"));
        Employee employee = employeeDAO.getEmployeeById(employeeId)
                .orElseThrow(() -> new ServletException("Employee not found"));

        List<Department> departments = departmentDAO.getAllDepartments();

        request.setAttribute("employee", employee);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/updateEmployee.jsp").forward(request, response);
    }

    private void addEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String position = req.getParameter("position");
        Integer departmentId = Integer.parseInt(req.getParameter("departmentId"));

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || departmentId == null) {
            throw new IllegalArgumentException("Name, email, and department are required.");
        }

        Department department = departmentDAO.getDepartmentById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + departmentId));
        Employee newEmployee = new Employee(name, email, phone, position, department);
        employeeDAO.addEmployee(newEmployee);

        res.sendRedirect(req.getContextPath() + "/employees/list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Integer employeeId = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String position = request.getParameter("position");
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || departmentId == null) {
            throw new IllegalArgumentException("Name, email, and department are required.");
        }

        Department department = departmentDAO.getDepartmentById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + departmentId));

        Employee employee = employeeDAO.getEmployeeById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setPosition(position);
        employee.setDepartment(department);

        employeeDAO.updateEmployee(employee);

        response.sendRedirect(request.getContextPath() + "/employees/list");
    }

    public void listEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");
        List<Employee> employees;
        List<Department> departments = departmentDAO.getAllDepartments();
//        for (Employee employee : employees) {
//            logger.info("Employee: " + employee);
//            if (employee.getDepartment() != null) {
//                String departmentName = employee.getDepartment().getName();
//                employee.setDepartmentName(departmentName);
//            }
//        }

        if ("search".equals(action)) {
            String keyword = req.getParameter("keyword");
            employees = employeeDAO.searchEmployees(keyword);
        } else if ("filterDepartment".equals(action)) {
            Integer departmentId = Integer.parseInt(req.getParameter("departmentId"));
            employees = employeeDAO.filterEmployeesByDepartment(departmentId);
        } else if ("filterPosition".equals(action)) {
            String position = req.getParameter("position");
            employees = employeeDAO.filterEmployeesByPosition(position);
        } else {
            employees = employeeDAO.getAllEmployees();
        }

        req.setAttribute("departments", departments);
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/employees.jsp")
                .forward(req, res);
    }

    private void handleException(HttpServletResponse response, String message, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println(message + ": " + e.getMessage());
        e.printStackTrace(); // Print stack trace for debugging
    }
}
