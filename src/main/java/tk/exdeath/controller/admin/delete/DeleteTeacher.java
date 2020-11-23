package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

@Controller
public class DeleteTeacher {

    final String PATH = "admin/delete/deleteTeacher";

    @GetMapping("/deleteTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(
            @RequestParam(defaultValue = "null") String login, Model model) {

        if (incorrectInput(login)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.readByLogin(login);

        if (loginIsInvalid(teacher)) {
            model.addAttribute("Message", "Учителя с таким логином не существует");
            return PATH;
        }

        teacherService.delete(teacher);
        teacherService.closeSession();
        model.addAttribute("Message", login + " успешно удален");
        return PATH;
    }


    private boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private boolean loginIsInvalid(Teacher teacher) {
        return teacher.getLogin().equals("null");
    }
}
