
package com.altcolorlab.rollcall;

/**
 *
 * @author justink
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

//Sets up the Hibernate session factory. This is the Hibernate 4.3.10 version of session factory setup and takes in the configuration xml.
public class HibernateUtil {
  private static final SessionFactory sessionFactory;

    static {
            try {
            	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");        	
            	StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
            	sb.applySettings(cfg.getProperties());
            	StandardServiceRegistry standardServiceRegistry = sb.build();           	
            	sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);      	
            } catch (Throwable th) {
                    System.err.println("Initial SessionFactory creation failed" + th);
                    throw new ExceptionInInitializerError(th);
            }
    }
    //returns the session factory that was established above.
    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }
}