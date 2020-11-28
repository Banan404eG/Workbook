package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.admin.delete.DeletePage;

@Controller
public class DeletePageController {

    final String PATH = "admin/delete/deletePage";

    @GetMapping("/deletePage")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {
        try {
            DeletePage.keyCheck(key);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/deletePage")
    public String deletePage(
            @RequestParam(defaultValue = "null") String lesson,
            @RequestParam(defaultValue = "0") int grade,
            @RequestParam(defaultValue = "0") int page, Model model) {
        try {
            DeletePage.deletePage(lesson, grade, page);
            model.addAttribute("Message", lesson + " " + grade + " " + page + " успешно удален");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
