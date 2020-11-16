package tk.exdeath.controller.admin.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.admin.LoggedAdmin;
import tk.exdeath.model.service.StudentService;

@Controller
public class DeleteStudent {

    final String PATH = "admin/delete/deleteStudent";

    StudentService studentService;

    @GetMapping("/deleteStudent")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {

        if (LoggedAdmin.isKeyValid(key)) {
            return PATH;
        }

        return "errorPage";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(
            @RequestParam String login, Model model) {

        studentService = new StudentService();

        if (loginIsInvalid(login)) {
            model.addAttribute("Message", "Ученика с таким логином не существует");
            return PATH;
        }

        studentService.delete(studentService.readByLogin(login));
        studentService.closeSession();

        model.addAttribute("Message", login + " успешно удален");
        return PATH;
    }

    private boolean loginIsInvalid(String login) {
        return studentService.readByLogin(login).getLogin().equals("null");
    }
}
