package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.logic.admin.add.AddTeacher;

import javax.annotation.Resource;

@Controller
public class AddTeacherController {

    final String PATH = "admin/add/addTeacher";

    @Resource(name = "getAddTeacher")
    private AddTeacher addTeacher;

    @GetMapping("/addTeacher")
    public String returnPage(Model model) {
        try {
            addTeacher.validationCheck();
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/addTeacher")
    public String addTeacher(
            @RequestParam(defaultValue = "null") String login,
            @RequestParam(defaultValue = "null") String password,
            @RequestParam(defaultValue = "null") String firstName,
            @RequestParam(defaultValue = "null") String secondName, Model model) {
        try {
            addTeacher.addTeacher(login, password, firstName, secondName);
            model.addAttribute("Message", firstName + " " + secondName + " успешно создан");
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
