package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.Page;
import tk.exdeath.model.service.PageService;

@Controller
public class DeletePage {

    final String PATH = "admin/delete/deletePage";

    @GetMapping("/deletePage")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/deletePage")
    public String deletePage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam int grade,
            @RequestParam int page, Model model) {

        if (incorrectInput(lesson, grade, page)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        if (pageDoesNotExist(lesson, grade, page)) {
            model.addAttribute("Message", "Страницы с такими данными не найдено: " + lesson + " " + grade + " " + page);
            return PATH;
        }

        PageService pageService = new PageService();
        pageService.delete(pageService.read(lesson, grade, page));
        pageService.closeSession();

        model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно удален");
        return PATH;
    }

    private boolean incorrectInput(String lesson, int grade, int page) {
        return lesson.equals("null") || grade < 1 || page < 1;
    }

    private boolean pageDoesNotExist(String lesson, int grade, int page) {
        return lesson.equals("null") || grade == 0 || page == 0;
    }
}