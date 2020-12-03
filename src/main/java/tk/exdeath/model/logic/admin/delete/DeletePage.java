package tk.exdeath.model.logic.admin.delete;

import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;
import tk.exdeath.model.logic.admin.account.LoggedAdmin;

import javax.annotation.Resource;

public class DeletePage {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }

    public void deletePage(String lesson, int grade, int page) {
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


    private boolean incorrectInput(String lesson, int grade, int page) {
        return lesson.equals("null") || grade < 1 || page < 1;
    }

    private boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }
}
