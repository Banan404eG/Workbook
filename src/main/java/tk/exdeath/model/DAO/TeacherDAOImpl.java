package tk.exdeath.model.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.hibernate.HibernateFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeacherDAOImpl implements TeacherDAO {

    private final Session session = HibernateFactory.getSessionFactory().openSession();
    private final CriteriaBuilder builder = session.getCriteriaBuilder();
    private final CriteriaQuery<Teacher> criteria = builder.createQuery(Teacher.class);
    private final Root<Teacher> root = criteria.from(Teacher.class);

    @Override
    public void create(Teacher teacher) {
        Transaction transaction = session.beginTransaction();
        session.save(teacher);
        transaction.commit();
    }

    @Override
    public void update(Teacher teacher) {
        Transaction transaction = session.beginTransaction();
        session.update(teacher);
        transaction.commit();
    }

    @Override
    public Teacher readByName(String firstName, String secondName) {
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
