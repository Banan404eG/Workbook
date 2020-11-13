package tk.exdeath.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class AddStudent {

    final String PATH = "admin/addStudent";

    StudentService studentService;

    @GetMapping("/addStudent")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/addStudent")
    public String addStudent(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam String studentClass, Model model) {

        studentService = new StudentService();

        if (loginIsInvalid(login)) {
            model.addAttribute("Message", "Ученик с таким логином уже существует");
            return PATH;
        }

        studentService.create(new Student(login, password, firstName, secondName, studentClass));

        model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
        return PATH;
    }

    private boolean loginIsInvalid(String login) {
        return !studentService.readByLogin(login).getLogin().equals("null");
    }
}
