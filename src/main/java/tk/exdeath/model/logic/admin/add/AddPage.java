package tk.exdeath.model.logic.admin.add;

import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;
import tk.exdeath.model.logic.admin.account.LoggedAdmin;

import javax.annotation.Resource;

public class AddPage {

    @Resource(name = "getLoggedAdmin")
    private LoggedAdmin loggedAdmin;

    public void validationCheck() {
        loggedAdmin.validationCheck();
    }

    public void addPage(String lesson, int grade, int page, int numberOfInputs, String pictureURL) {
        if (incorrectInput(lesson, grade, page, numberOfInputs, pictureURL)) {
            throw new RuntimeException("Некорректный ввод");
        }
        PageService pageService = new PageService();
        if (pageExist(lesson, grade, page, pageService)) {
            pageService.closeSession();
            throw new RuntimeException("Страница с такими данными уже существует: " + lesson + " " + grade + " " + page);
        }
        pageService.create(new Page(lesson, grade, page, numberOfInputs, pictureURL));
        pageService.closeSession();
    }


    private boolean incorrectInput(String lesson, int grade, int page, int numberOfInputs, String pictureURL) {
        return lesson.equals("null") || grade < 1 || page < 1 || numberOfInputs < 1 || pictureURL.equals("null");
    }

    private boolean pageExist(String lesson, int grade, int page, PageService pageService) {
        return !pageService.read(lesson, grade, page).getLesson().equals("null");
    }
}
