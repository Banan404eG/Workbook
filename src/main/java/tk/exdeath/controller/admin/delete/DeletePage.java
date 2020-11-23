package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.service.PageService;

@Controller
public class DeletePage {

    final String PATH = "admin/delete/deletePage";

    @GetMapping("/deletePage")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/deletePage")
    public String deletePage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "0") int grade,
            @RequestParam(defaultValue = "0") int page, Model model) {

        if (incorrectInput(lesson, grade, page)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);

        if (pageDoesNotExist(pageEntity)) {
            model.addAttribute("Message", "Страницы с такими данными не найдено: " + lesson + " " + grade + " " + page);
            return PATH;
        }

        pageService.delete(pageEntity);
        pageService.closeSession();
        model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно удален");
        return PATH;
    }


    private boolean incorrectInput(String lesson, int grade, int page) {
        return lesson.equals("null") || grade < 1 || page < 1;
    }

    private boolean pageDoesNotExist(Page page) {
        return page.getLesson().equals("null");
    }
}
