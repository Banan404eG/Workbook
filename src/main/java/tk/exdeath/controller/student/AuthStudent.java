package tk.exdeath.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class AuthStudent {

    final String PATH = "student/authStudent";

    @GetMapping("/authStudent")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/authStudent")
    public String passCheck(
            @RequestParam String login,
            @RequestParam String password, Model model) {

        StudentService service = LoggedStudent.getStudentService();
        Student student = service.readByLogin(login);

        if (studentDoesNotExist(student)) {
            model.addAttribute("Error", "Аккаунта с таким логином не существует");
            return PATH;
        }
        if (passwordIsNotCorrect(student, password)) {
            model.addAttribute("Error", "Неверный пароль, попробуйте ещё раз");
            return PATH;
        }
        return signIn(student, login);
    }


    private boolean studentDoesNotExist(Student student) {
        return student.getLogin().equals("null");
    }

    private boolean passwordIsNotCorrect(Student student, String password) {
        return !student.getPassword().equals(password);
    }

    private String signIn(Student student, String login) {
        LoggedStudent.setLogin(login);
        LoggedStudent.setStudent(student);
        return "redirect:/accountStudent";
    }
}
