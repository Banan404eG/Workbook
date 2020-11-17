package tk.exdeath.model.DAO;

import tk.exdeath.model.Page;

import java.util.List;

public interface PageDAO {
    void create(Page page);

    Page read(String lesson, int grade, int page);

    void update(Page page);

    void delete(Page page);

    List<Page> readPages(String lesson, int grade);

    void closeSession();
}
