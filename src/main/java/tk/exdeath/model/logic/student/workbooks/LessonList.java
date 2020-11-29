package tk.exdeath.model.logic.student.workbooks;

import tk.exdeath.model.database.service.PageService;

import java.util.Set;

public class LessonList {

    public Set<String> getLessons() {
        PageService pageService = new PageService();
        Set<String> lessons = pageService.readLessons();
        pageService.closeSession();
        return lessons;
    }
}
