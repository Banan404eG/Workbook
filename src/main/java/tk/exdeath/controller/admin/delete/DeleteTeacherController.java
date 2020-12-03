package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.delete.DeleteTeacher;

import javax.annotation.Resource;

@Controller
public class DeleteTeacherController {

    final String PATH = "admin/delete/deleteTeacher";

    @Resource(name = "getDeleteTeacher")
    private DeleteTeacher deleteTeacher;

    @GetMapping("/deleteTeacher")
    public String returnPage(Model model) {
        try {
            deleteTeacher.validationCheck();
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
            deleteTeacher.deleteTeacher(login);
            model.addAttribute("Message", login + " успешно удален");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
