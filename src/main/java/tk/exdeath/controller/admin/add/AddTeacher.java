package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

@Controller
public class AddTeacher {

    final String PATH = "admin/add/addTeacher";

    @GetMapping("/addTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(
            @RequestParam(defaultValue = "null") String login,
            @RequestParam(defaultValue = "null") String password,
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName, Model model) {

        if (incorrectInput(login, password, firstName, secondName)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        TeacherService teacherService = new TeacherService();
        if (loginIsInvalid(login, teacherService)) {
            model.addAttribute("Message", "Учитель с таким логином уже существует");
            return PATH;
        }

        teacherService.create(new Teacher(login, password, firstName, secondName));
        teacherService.closeSession();
        model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
        return PATH;
    }


    private boolean incorrectInput(String login, String password, String firstName, String secondName) {
        return login.equals("null") || password.equals("null") || firstName.equals("null") || secondName.equals("null");
    }

    private boolean loginIsInvalid(String login, TeacherService teacherService) {
        return !teacherService.readByLogin(login).getLogin().equals("null");
    }
}
