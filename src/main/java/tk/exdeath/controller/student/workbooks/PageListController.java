package tk.exdeath.controller.student.workbooks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.student.workbooks.PageList;

import javax.annotation.Resource;

@Controller
public class PageListController {

    final String PATH = "student/pages/pageList";

    @Resource(name = "getPageList")
    private PageList pageList;

    @GetMapping("/pageList")
    public String getPages(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade, Model model) {
        try {
            model.addAttribute("lesson", lesson);
            model.addAttribute("grade", grade);
            model.addAttribute("pages", pageList.getPages(lesson, grade));
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }
}
