package com.labor.laboree.dao;

import com.labor.laboree.dao.interfaces.DepartmentDAO;
import com.labor.laboree.models.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DepatmentDAOImpl implements DepartmentDAO {

    private static final Logger logger = Logger.getLogger(DepatmentDAOImpl.class.getName());
    private final SessionFactory sessionFactory;

    public DepatmentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDepartment(Department department) {
        logger.info("Adding department: " + department);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            logger.info("Department added successfully");
        } catch (Exception e) {
            logger.severe("Failed to add department: " + e.getMessage());
        }
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        logger.info("Getting department by id: " + id);
        try (Session session = sessionFactory.openSession()) {
            Department department = session.get(Department.class, id);
            return Optional.ofNullable(department);
        } catch (Exception e) {
            logger.severe("Failed to get department by id: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        logger.info("Getting all departments");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Department", Department.class).list();
        } catch (Exception e) {
            logger.severe("Failed to get all departments: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteDepartment(Integer id) {
        logger.info("Deleting department by id: " + id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, id);
            if (department != null) {
                session.delete(department);
                transaction.commit();
                logger.info("Department deleted successfully");
            } else {
                logger.warning("Department not found");
            }
        } catch (Exception e) {
            logger.severe("Failed to delete department: " + e.getMessage());
        }
    }
}
