package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.add.AddStudent;

import javax.annotation.Resource;

@Controller
public class AddStudentController {

    final String PATH = "admin/add/addStudent";

    @Resource(name = "getAddStudent")
    private AddStudent addStudent;

    @GetMapping("/addStudent")
    public String returnPage(Model model) {
        try {
            addStudent.validationCheck();
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
            addStudent.addStudent(login, password, firstName, secondName, studentClass);
            model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
