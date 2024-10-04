package com.labor.laboree.dao.interfaces;

import com.labor.laboree.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Integer id);
    public Optional<Employee> getEmployeeById(Integer id);
    public List<Employee> getAllEmployees();
    List<Employee> searchEmployees(String keyword);
    List<Employee> filterEmployeesByDepartment(Integer departmentId);
    List<Employee> filterEmployeesByPosition(String position);
}
