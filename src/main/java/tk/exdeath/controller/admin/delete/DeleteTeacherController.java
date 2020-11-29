package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.delete.DeleteTeacher;

@Controller
public class DeleteTeacherController {

    final String PATH = "admin/delete/deleteTeacher";

    @GetMapping("/deleteTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {
        try {
            DeleteTeacher.keyCheck(key);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(
            @RequestParam(defaultValue = "null") String login, Model model) {
        try {
            DeleteTeacher.deleteTeacher(login);
            model.addAttribute("Message", login + " успешно удален");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
