package tk.exdeath.model.logic.admin.delete;

import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

public abstract class DeletePage {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void deletePage(String lesson, int grade, int page) {
        if (incorrectInput(lesson, grade, page)) {
            throw new RuntimeException("Некорректный ввод");
        }
        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);
        if (pageDoesNotExist(pageEntity)) {
            pageService.closeSession();
            throw new RuntimeException("Страницы с такими данными не найдено: " + lesson + " " + grade + " " + page);
        }
        pageService.delete(pageEntity);
        pageService.closeSession();
    }


    private static boolean incorrectInput(String lesson, int grade, int page) {
        return lesson.equals("null") || grade < 1 || page < 1;
    }

    private static boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }
}
