package tk.exdeath.model.student.pages;

import tk.exdeath.model.database.service.PageService;

import java.util.Set;

public abstract class LessonList {

    public static Set<String> getLessons() {
        PageService pageService = new PageService();
        Set<String> lessons = pageService.readLessons();
        pageService.closeSession();
        return lessons;
    }
}
