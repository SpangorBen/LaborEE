package com.labor.laboree.listners;

import com.labor.laboree.dao.DepatmentDAOImpl;
import com.labor.laboree.dao.EmployeeDAOImpl;
import com.labor.laboree.dao.interfaces.DepartmentDAO;
import com.labor.laboree.dao.interfaces.EmployeeDAO;
import com.labor.laboree.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

public class AppContextListener implements ServletContextListener {

    private final Logger logger = Logger.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(sessionFactory);
            DepartmentDAO departmentDAO = new DepatmentDAOImpl(sessionFactory);

            sce.getServletContext().setAttribute("employeeDAO", employeeDAO);
            sce.getServletContext().setAttribute("departmentDAO", departmentDAO);
            logger.info("Application initialized successfully!");
        } catch (Exception e) {
            logger.severe("Failed to initialize application: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("AppContextListener.contextDestroyed");
    }
}
