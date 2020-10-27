package tk.exdeath.controller.student.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkbookController {

    @GetMapping("/workbook")
    public String workbook(
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "NULL") String lesson,
            @RequestParam(defaultValue = "-1") int page, Model model) {

        if (paramsIsInvalid(grade, lesson, page)) {
            model.addAttribute("Error", "Параметры тетради неверны (класс, предмет или страница)");
            return "errorPage";
        }

        return "workbooks/" + lesson + "/" + grade + "/" + page;
    }

    private boolean paramsIsInvalid(int grade, String lesson, int page) {
        return grade == -1 || page == -1 || lesson.equals("NULL");
    }
}
