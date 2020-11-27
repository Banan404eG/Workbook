package tk.exdeath.model.student.workbooks;

import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

import java.util.ArrayList;
import java.util.List;

public abstract class PageList {

    public static List<Integer> getPages(String lesson, int grade) {
        if (invalidParams(lesson, grade)) {
            throw new RuntimeException("Параметры неверны (класс или предмет)");
        }
        PageService pageService = new PageService();
        List<Integer> pages = new ArrayList<>();
        for (Page page : pageService.readPages(lesson, grade)) {
            pages.add(page.getPage());
        }
        pageService.closeSession();
        return pages;
    }

    private static boolean invalidParams(String lesson, int grade) {
        return lesson.equals("null") || grade == -1;
    }
}
