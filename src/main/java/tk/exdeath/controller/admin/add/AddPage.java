package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;

@Controller
public class AddPage {

    final String PATH = "admin/add/addPage";

    @GetMapping("/addPage")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/addPage")
    public String addPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam int grade,
            @RequestParam int page,
            @RequestParam int numberOfInputs,
            @RequestParam(defaultValue = "null") String pictureURL, Model model) {

        if (incorrectInput(lesson, grade, page, numberOfInputs, pictureURL)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        if (pageExist(lesson, grade, page)) {
            model.addAttribute("Message", "Страница с такими данными уже существует: " + lesson + " " + grade + " " + page);
            return PATH;
        }


        model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно создан");
        return PATH;
    }


    private boolean pageExist(String lesson, int grade, int page) {
        return lesson.equals("null") || grade == 0 || page == 0;
    }

    private boolean incorrectInput(String lesson, int grade, int page, int numberOfInputs, String pictureURL) {
        return lesson.equals("null") || grade < 1 || page < 1 || numberOfInputs < 1 || pictureURL.equals("null");
    }
}
