package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.Teacher;
import tk.exdeath.model.service.TeacherService;

@Controller
public class AddTeacher {

    final String PATH = "admin/add/addTeacher";

    TeacherService teacherService;

    @GetMapping("/addTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String secondName, Model model) {

        teacherService = new TeacherService();

        if (loginIsInvalid(login)) {
            model.addAttribute("Message", "Учитель с таким логином уже существует");
            return PATH;
        }

        teacherService.create(new Teacher(login, password, firstName, secondName));
        teacherService.closeSession();

        model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
        return PATH;
    }

    private boolean loginIsInvalid(String login) {
        return !teacherService.readByLogin(login).getLogin().equals("null");
    }
}
