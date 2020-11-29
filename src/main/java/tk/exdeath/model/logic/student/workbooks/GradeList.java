package tk.exdeath.model.logic.student.workbooks;

import tk.exdeath.model.database.service.PageService;

import java.util.Set;

public class GradeList {

    public Set<Integer> getGrades(String lesson) {
        if (invalidLesson(lesson)) {
            throw new RuntimeException("Параметры неверны (предмет)");
        }
        PageService pageService = new PageService();
        Set<Integer> grades = pageService.readGrades(lesson);
        pageService.closeSession();
        return grades;
    }

    private boolean invalidLesson(String lesson) {
        return lesson.equals("null");
    }
}
