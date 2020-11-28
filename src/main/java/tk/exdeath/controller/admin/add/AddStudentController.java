package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.admin.add.AddStudent;

@Controller
public class AddStudentController {

    final String PATH = "admin/add/addStudent";

    @GetMapping("/addStudent")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {
        try {
            AddStudent.keyCheck(key);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/addStudent")
    public String addStudent(
            @RequestParam(defaultValue = "null") String login,
            @RequestParam(defaultValue = "null") String password,
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName,
            @RequestParam(defaultValue = "null") String studentClass, Model model) {
        try {
            AddStudent.addStudent(login, password, firstName, secondName, studentClass);
            model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
