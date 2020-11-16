package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.TeacherService;

@Controller
public class DeleteTeacher {

    final String PATH = "admin/deleteTeacher";

    TeacherService teacherService;

    @GetMapping("/deleteTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(
            @RequestParam String login, Model model) {

        teacherService = new TeacherService();

        if (loginIsInvalid(login)) {
            model.addAttribute("Message", "Учителя с таким логином не существует");
            return PATH;
        }

        teacherService.delete(teacherService.readByLogin(login));
        teacherService.closeSession();

        model.addAttribute("Message", login + " успешно удален");
        return PATH;
    }

    private boolean loginIsInvalid(String login) {
        return teacherService.readByLogin(login).getLogin().equals("null");
    }
}
