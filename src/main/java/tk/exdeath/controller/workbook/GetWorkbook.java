package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Page;
import tk.exdeath.model.service.PageService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GetWorkbook {

    @GetMapping("/workbook")
    public String getPages(
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "NULL") String lesson, Model model) {

        if (paramsIsInvalid(grade, lesson)) {
            model.addAttribute("Error", "Параметры тетради неверны (класс или предмет)");
            return "errorPage";
        }

        PageService pageService = new PageService();
        List<Page> pages = pageService.readPages(lesson, grade);

        model.addAttribute("lesson", lesson);
        model.addAttribute("grade", grade);
        model.addAttribute("pages", getPages(pages));
        pageService.closeSession();
        return "workbook/pageList";
    }

    private boolean paramsIsInvalid(int grade, String lesson) {
        return grade == -1 || lesson.equals("NULL");
    }

    private List<Integer> getPages(List<Page> pageEntities) {
        List<Integer> pages = new ArrayList<>();

        for (Page page : pageEntities) {
            pages.add(page.getPage());
        }

        return pages;
    }
}
