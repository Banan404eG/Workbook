package tk.exdeath.model.logic.student.workbooks;

import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

public class StudentPage {

    private Page pageEntity;

    public void studentPage(String lesson, int grade, int page) {
        if (invalidInputs(lesson, grade, page)) {
            throw new RuntimeException("Параметры страницы неверны (класс, предмет или страница)");
        }
        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);
        if (pageDoesNotExist(pageEntity)) {
            pageService.closeSession();
            throw new RuntimeException("Страница не существует");
        }
        this.pageEntity = pageEntity;
        pageService.closeSession();
    }


    private boolean invalidInputs(String lesson, int grade, int page) {
        return lesson.equals("null") || grade == -1 || page == -1;
    }

    private boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }


    public String getPicture() {
        return pageEntity.getPicture();
    }

    public String getInputs(String INPUT) {
        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < pageEntity.getNumberOfInputs(); i++) {
            inputs.append(INPUT);
        }
        return inputs.toString();
    }
}
