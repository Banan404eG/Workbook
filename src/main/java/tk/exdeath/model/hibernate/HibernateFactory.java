package tk.exdeath.model.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import tk.exdeath.model.*;


public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private HibernateFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(Student.class);
                config.addAnnotatedClass(Task.class);
                config.addAnnotatedClass(Teacher.class);
                config.addAnnotatedClass(Mark.class);
                config.addAnnotatedClass(Page.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            } catch (Exception ex) {
                System.out.println("Exception! " + ex);
            }
        }
        return sessionFactory;
    }


}