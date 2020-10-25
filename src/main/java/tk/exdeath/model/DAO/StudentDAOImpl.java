package tk.exdeath.model.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tk.exdeath.model.Student;
import tk.exdeath.model.hibernate.HibernateFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentDAOImpl implements StudentDAO {

    private final Session session = HibernateFactory.getSessionFactory().openSession();
    private final CriteriaBuilder builder = session.getCriteriaBuilder();
    private final CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
    private final Root<Student> root = criteria.from(Student.class);

    @Override
    public void create(Student student) {
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
    }

    @Override
    public void update(Student student) {
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
    }

    @Override
    public Student readByName(String firstName, String secondName) {
        Predicate firstNamePredicate = builder.like(root.get("firstName"), firstName);
        Predicate secondNamePredicate = builder.like(root.get("secondName"), secondName);

        return session.createQuery(criteria.select(root).where(firstNamePredicate, secondNamePredicate)).uniqueResult();
    }

    protected void finalize() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
