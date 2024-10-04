package com.labor.laboree.services.interfaces;

import com.labor.laboree.models.Employee;

import java.util.List;

public interface EmplyeeService {
    void registerEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    List<Employee> searchEmployees(String keyword);
    List<Employee> filterEmployeesByDepartment(Integer departmentId);
    List<Employee> filterEmployeesByPosition(String position);
}
