package tk.exdeath.controller.student.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.pages.PageList;

@Controller
public class PageListController {

    final String PATH = "student/pages/pageList";

    @GetMapping("/pageList")
    public String getPages(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade, Model model) {

        if (PageList.invalidParams(lesson, grade)) {
            model.addAttribute("Error", "Параметры неверны (класс или предмет)");
            return "errorPage";
        }
        model.addAttribute("lesson", lesson);
        model.addAttribute("grade", grade);
        model.addAttribute("pages", PageList.getPages(lesson, grade));
        return PATH;
    }
}
