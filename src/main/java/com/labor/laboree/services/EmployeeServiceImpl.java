package com.labor.laboree.services;

import com.labor.laboree.dao.interfaces.DepartmentDAO;
import com.labor.laboree.dao.interfaces.EmployeeDAO;
import com.labor.laboree.models.Employee;
import com.labor.laboree.services.interfaces.EmplyeeService;
import com.labor.laboree.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

public class EmployeeServiceImpl implements EmplyeeService {

    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;
    private final SessionFactory sessionFactory;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void registerEmployee(Employee employee) {
        Integer departmentId = employee.getDepartment().getId();

        if (!departmentDAO.getDepartmentById(departmentId).isPresent()) {
            throw new IllegalArgumentException("Department not found with id: " + departmentId);
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            employeeDAO.addEmployee(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to register employee: " + e.getMessage());
        }

    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return Collections.emptyList();
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(Integer id) {

    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        return Collections.emptyList();
    }

    @Override
    public List<Employee> filterEmployeesByDepartment(Integer departmentId) {
        return Collections.emptyList();
    }

    @Override
    public List<Employee> filterEmployeesByPosition(String position) {
        return Collections.emptyList();
    }
}
