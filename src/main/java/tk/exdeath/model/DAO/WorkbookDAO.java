package tk.exdeath.model.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tk.exdeath.model.hibernate.HibernateFactory;

public class WorkbookDAO<E> {

    Session session = HibernateFactory.getSessionFactory().openSession();

    public void create(E workbook) {
        Transaction transaction = session.beginTransaction();
        session.save(workbook);
        transaction.commit();
    }

    public void update(E workbook) {
        Transaction transaction = session.beginTransaction();
        session.update(workbook);
        transaction.commit();
    }

    public void delete(E workbook) {
        Transaction transaction = session.beginTransaction();
        session.delete(workbook);
        transaction.commit();
    }

    protected void finalize() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
