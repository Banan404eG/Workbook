package tk.exdeath.model.database.service;

import tk.exdeath.model.database.DAO.PageDAO;
import tk.exdeath.model.database.DAO.PageDAOImpl;
import tk.exdeath.model.database.entities.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<String> readLessons() {
        Set<String> lessons = new HashSet<>();

        for (Page page : DAO.readAllPages()) {
            lessons.add(page.getLesson());
        }

        return lessons;
    }

    public Set<Integer> readGrades(String lesson) {
        Set<Integer> grades = new HashSet<>();

        for (Page page : DAO.readAllPages()) {
            if (page.getLesson().equals(lesson)) {
                grades.add(page.getGrade());
            }
        }

        return grades;
    }

    public List<Page> readPages(String lesson, int grade) {
        return DAO.readPages(lesson, grade);
    }

    public void closeSession() {
        DAO.closeSession();
    }
}
