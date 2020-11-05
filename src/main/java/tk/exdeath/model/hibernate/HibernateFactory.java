package tk.exdeath.model.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.workbook.Informatics.grade7.page8.Informatics78;


public class HibernateFactory {

    private static SessionFactory sessionFactory;

    private HibernateFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.addAnnotatedClass(Student.class);
                config.addAnnotatedClass(Teacher.class);
                config.addAnnotatedClass(Task.class);
                addWorkbooks(config);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            } catch (Exception ex) {
                System.out.println("Exception! " + ex);
            }
        }
        return sessionFactory;
    }


    private static void addWorkbooks(Configuration config) {
        config.addAnnotatedClass(Informatics78.class);
    }
}