package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkbookController {

    @GetMapping("/workbook")
    public String getPages(
            @RequestParam(defaultValue = "-1") int grade,
            @RequestParam(defaultValue = "NULL") String lesson, Model model) {

        if (paramsIsInvalid(grade, lesson)) {
            model.addAttribute("Error", "Параметры тетради неверны (класс или предмет)");
            return "errorPage";
        }

        return "workbook/" + lesson + "/" + grade + "/pageList";
    }

    private boolean paramsIsInvalid(int grade, String lesson) {
        return grade == -1 || lesson.equals("NULL");
    }
}
