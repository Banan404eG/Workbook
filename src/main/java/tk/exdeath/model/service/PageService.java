package tk.exdeath.model.service;

import tk.exdeath.model.DAO.PageDAO;
import tk.exdeath.model.DAO.PageDAOImpl;
import tk.exdeath.model.Page;

public class PageService {

    private final PageDAO DAO = new PageDAOImpl();

    public void create(Page page) {
        DAO.create(page);
    }

    public Page read(String lesson, int grade, int page) {
        Page pageEntity = DAO.read(lesson, grade, page);
        return nullCheck(pageEntity);
    }

    public void update(Page page) {
        DAO.update(page);
    }

    public void delete(Page page) {
        DAO.delete(page);
    }

    private Page nullCheck(Page page) {
        if (page == null) {
            page = new Page();
            page.setLesson("null");
        }
        return page;
    }

    public void closeSession() {
        DAO.closeSession();
    }
}
