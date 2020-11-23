package tk.exdeath.controller.student.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.student.pages.StudentPage;

@Controller
public class StudentPageController {

    final String PATH = "workbook/page";

    @GetMapping("/page")
    public String returnPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "-1") int page, Model model) {

        if (StudentPage.invalidParams(lesson, grade, page)) {
            model.addAttribute("Error", "Параметры страницы неверны (класс, предмет или страница)");
            return "errorPage";
        }
        model.addAttribute("picture", StudentPage.getPicture());
        model.addAttribute("inputs", StudentPage.getInputs());
        model.addAttribute("lesson", lesson);
        model.addAttribute("grade", grade);
        model.addAttribute("page", page);
        return PATH;
    }
}
