package com.labor.laboree.dao;

import com.labor.laboree.dao.interfaces.EmployeeDAO;
import com.labor.laboree.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger logger = Logger.getLogger(EmployeeDAOImpl.class.getName());
    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmployee(Employee employee) {
        logger.info("Adding employee: " + employee.getName());
        try (Session session = sessionFactory.openSession()) {
            session.save(employee);
            logger.info("Employee added successfully: " + employee.getName());
        } catch (Exception e) {
            logger.severe("Failed to add employee: " + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        logger.info("Updating employee: " + employee.getName());
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            logger.info("Employee updated successfully: " + employee.getName());
        } catch (Exception e) {
            logger.severe("Failed to update employee: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        logger.info("Deleting employee with id: " + id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                logger.info("Employee deleted successfully: " + employee.getName());

            } else {
                logger.warning("Employee not found with id: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.severe("Failed to delete employee: " + e.getMessage());
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        logger.info("Fetching employee with id: " + id);
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                logger.info("Employee fetched successfully: " + employee.getName());
                return Optional.of(employee);
            } else {
                logger.warning("Employee not found with id: " + id);
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.severe("Failed to fetch employee: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
            for (Employee employee : employees) {
                logger.info("Fetched employee: " + employee);
            }
            logger.info("Fetched all employees successfully");
            return employees;
        } catch (Exception e) {
            logger.severe("Failed to fetch all employees: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        logger.info("Searching for employees with keyword: " + keyword);
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee e WHERE " +
                    "e.name LIKE :keyword OR " +
                    "e.email LIKE :keyword OR " +
                    "e.phone LIKE :keyword OR " +
                    "e.position LIKE :keyword";

            List<Employee> employees = session.createQuery(hql, Employee.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
            for (Employee employee : employees) {
                logger.info("Fetched employee: " + employee);
            }
            return employees;
        } catch (Exception e) {
            logger.severe("Error searching for employees: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Employee> filterEmployeesByDepartment(Integer departmentId) {
        logger.info("Filtering employees by department ID: " + departmentId);
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee e WHERE e.department.id = :departmentId";
            List<Employee> employees = session.createQuery(hql, Employee.class)
                    .setParameter("departmentId", departmentId)
                    .list();
            for (Employee employee : employees) {
                logger.info("Fetched employee: " + employee);
            }
            return employees;
        } catch (Exception e) {
            logger.severe("Error filtering employees by department: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Employee> filterEmployeesByPosition(String position) {
        logger.info("Filtering employees by position: " + position);
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee e WHERE e.position LIKE :position";
            List<Employee> employees = session.createQuery(hql, Employee.class)
                    .setParameter("position", "%" + position + "%")
                    .list();
            for (Employee employee : employees) {
                logger.info("Fetched employee: " + employee);
            }
            return employees;
        } catch (Exception e) {
            logger.severe("Error filtering employees by position: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
