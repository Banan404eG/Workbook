package tk.exdeath.model.database.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.hibernate.HibernateFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PageDAOImpl implements PageDAO {

    private final Session session = HibernateFactory.getSessionFactory().openSession();
    private final CriteriaBuilder builder = session.getCriteriaBuilder();
    private final CriteriaQuery<Page> criteria = builder.createQuery(Page.class);
    private final Root<Page> root = criteria.from(Page.class);

    @Override
    public void create(Page page) {
        Transaction transaction = session.beginTransaction();
        session.save(page);
        transaction.commit();
    }

    @Override
    public Page read(String lesson, int grade, int page) {
        Predicate lessonPredicate = builder.like(root.get("lesson"), lesson);
        Predicate gradePredicate = builder.equal(root.get("grade"), grade);
        Predicate pagePredicate = builder.equal(root.get("page"), page);

        return session.createQuery(criteria.select(root).where(lessonPredicate, gradePredicate, pagePredicate)).uniqueResult();
    }

    @Override
    public List<Page> readAllPages() {
        return session.createQuery(criteria.select(root)).list();
    }

    @Override
    public List<Page> readPages(String lesson, int grade) {
        Predicate lessonPredicate = builder.like(root.get("lesson"), lesson);
        Predicate gradePredicate = builder.equal(root.get("grade"), grade);

        return session.createQuery(criteria.select(root).where(lessonPredicate, gradePredicate)).list();
    }

    @Override
    public void update(Page page) {
        Transaction transaction = session.beginTransaction();
        session.update(page);
        transaction.commit();
    }

    @Override
    public void delete(Page page) {
        Transaction transaction = session.beginTransaction();
        session.delete(page);
        transaction.commit();
    }

    @Override
    public void closeSession() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void finalize() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
