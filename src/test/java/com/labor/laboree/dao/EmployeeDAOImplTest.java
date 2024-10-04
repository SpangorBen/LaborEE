package com.labor.laboree.dao;

import com.labor.laboree.dao.interfaces.EmployeeDAO;
import com.labor.laboree.models.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeDAOImplTest {

    private EmployeeDAO employeeDAO;
    private SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-test.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
        employeeDAO = new EmployeeDAOImpl(sessionFactory);
    }

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testAddEmployee() {
//        Employee employee = new Employee("Test User", "test@example.com", "2345678", "boss", "HR");
//
//        employeeDAO.addEmployee(employee);
//
//        assertNotNull(employee.getId(), "Employee ID should be assigned after saving");


    }
}
