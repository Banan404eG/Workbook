package tk.exdeath.model.student.pages;

import tk.exdeath.model.database.service.PageService;

import java.util.Set;

public class GradeList {

    public static boolean invalidLesson(String lesson) {
        return lesson.equals("null");
    }

    public static Set<Integer> getGrades(String lesson) {
        PageService pageService = new PageService();
        Set<Integer> grades = pageService.readGrades(lesson);
        pageService.closeSession();
        return grades;
    }
}
