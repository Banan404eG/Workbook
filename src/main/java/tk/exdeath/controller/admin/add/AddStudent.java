package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class AddStudent {

    final String PATH = "admin/add/addStudent";

    @GetMapping("/addStudent")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/addStudent")
    public String addStudent(
            @RequestParam(defaultValue = "null") String login,
            @RequestParam(defaultValue = "null") String password,
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName,
            @RequestParam(defaultValue = "null") String studentClass, Model model) {

        if (incorrectInput(login, password, firstName, secondName, studentClass)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        StudentService studentService = new StudentService();
        if (loginIsInvalid(login, studentService)) {
            model.addAttribute("Message", "Ученик с таким логином уже существует");
            return PATH;
        }

        studentService.create(new Student(login, password, firstName, secondName, studentClass));
        studentService.closeSession();
        model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
        return PATH;
    }


    private boolean incorrectInput(String login, String password, String firstName, String secondName, String studentClass) {
        return login.equals("null") || password.equals("null") || firstName.equals("null") || secondName.equals("null") || studentClass.equals("null");
    }

    private boolean loginIsInvalid(String login, StudentService studentService) {
        return !studentService.readByLogin(login).getLogin().equals("null");
    }
}
