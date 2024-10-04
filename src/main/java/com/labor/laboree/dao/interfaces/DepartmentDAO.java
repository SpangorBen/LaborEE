package com.labor.laboree.dao.interfaces;

import com.labor.laboree.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDAO {
    void addDepartment(Department department);
    Optional<Department> getDepartmentById(Integer id);
    List<Department> getAllDepartments();
    void deleteDepartment(Integer id);
}
