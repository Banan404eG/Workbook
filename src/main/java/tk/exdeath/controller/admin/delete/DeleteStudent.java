package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

@Controller
public class DeleteStudent {

    final String PATH = "admin/delete/deleteStudent";

    @GetMapping("/deleteStudent")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }
        return "errorPage";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(
            @RequestParam(defaultValue = "null") String login, Model model) {

        if (incorrectInput(login)) {
            model.addAttribute("Message", "Некорректный ввод");
            return PATH;
        }

        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(login);

        if (loginIsInvalid(student)) {
            model.addAttribute("Message", "Ученика с таким логином не существует");
            return PATH;
        }

        studentService.delete(student);
        studentService.closeSession();
        model.addAttribute("Message", login + " успешно удален");
        return PATH;
    }


    private boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private boolean loginIsInvalid(Student student) {
        return student.getLogin().equals("null");
    }
}
