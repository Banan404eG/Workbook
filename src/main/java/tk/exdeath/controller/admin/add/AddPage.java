package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

@Controller
public class AddPage {

    final String PATH = "admin/add/addPage";

    @GetMapping("/addPage")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/addPage")
    public String addPage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "0") int grade,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int numberOfInputs,
            @RequestParam(defaultValue = "null") String pictureURL, Model model) {

        if (incorrectInput(lesson, grade, page, numberOfInputs, pictureURL)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        PageService pageService = new PageService();
        if (pageExist(lesson, grade, page, pageService)) {
            model.addAttribute("Message", "Страница с такими данными уже существует: " + lesson + " " + grade + " " + page);
            return PATH;
        }

        pageService.create(new Page(lesson, grade, page, numberOfInputs, pictureURL));
        pageService.closeSession();
        model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно создан");
        return PATH;
    }


    private boolean incorrectInput(String lesson, int grade, int page, int numberOfInputs, String pictureURL) {
        return lesson.equals("null") || grade < 1 || page < 1 || numberOfInputs < 1 || pictureURL.equals("null");
    }

    private boolean pageExist(String lesson, int grade, int page, PageService pageService) {
        return !pageService.read(lesson, grade, page).getLesson().equals("null");
    }
}
