package tk.exdeath.model.student.pages;

import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

public abstract class StudentPage {

    static final String INPUT = "<input name=\"answers[]\" placeholder=\"Ответ: \" type=\"text\"><br><br>";
    private static Page pageEntity;

    public static boolean invalidParams(String lesson, int grade, int page) {
        if (invalidInputs(lesson, grade, page)) {
            return true;
        }

        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);
        if (pageDoesNotExist(pageEntity)) {
            pageService.closeSession();
            return true;
        }

        StudentPage.pageEntity = pageEntity;
        pageService.closeSession();
        return false;
    }

    public static String getPicture() {
        return pageEntity.getPicture();
    }

    public static String getInputs() {
        StringBuilder inputs = new StringBuilder();

        for (int i = 0; i < pageEntity.getNumberOfInputs(); i++) {
            inputs.append(INPUT);
        }

        return inputs.toString();
    }


    private static boolean invalidInputs(String lesson, int grade, int page) {
        return lesson.equals("null") || grade == -1 || page == -1;
    }

    private static boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }
}
