package tk.exdeath.model.DAO;

import tk.exdeath.model.Page;

public interface PageDAO {
    void create(Page page);

    Page read(String lesson, int grade, int page);

    void update(Page page);

    void delete(Page page);

    void closeSession();
}
