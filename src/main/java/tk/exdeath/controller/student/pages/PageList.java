package tk.exdeath.controller.student.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Page;
import tk.exdeath.model.service.PageService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageList {

    final String PATH = "workbook/pageList";

    @GetMapping("/pageList")
    public String getPages(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade, Model model) {

        if (paramsIsInvalid(lesson, grade)) {
            model.addAttribute("Error", "Параметры тетради неверны (класс или предмет)");
            return "errorPage";
        }

        PageService pageService = new PageService();
        model.addAttribute("lesson", lesson);
        model.addAttribute("grade", grade);
        model.addAttribute("pages", getPages(pageService.readPages(lesson, grade)));
        pageService.closeSession();
        return PATH;
    }


    private boolean paramsIsInvalid(String lesson, int grade) {
        return lesson.equals("null") || grade == -1;
    }

    private List<Integer> getPages(List<Page> pageEntities) {
        List<Integer> pages = new ArrayList<>();

        for (Page page : pageEntities) {
            pages.add(page.getPage());
        }
        return pages;
    }
}
