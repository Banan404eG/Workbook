package tk.exdeath.controller.admin.add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.admin.add.MapStudentAndTeacher;

@Controller
public class MapStudentAndTeacherController {

    final String PATH = "admin/add/mapStudentAndTeacher";

    @GetMapping("/mapStudentAndTeacher")
    public String returnPage(
            @RequestParam(defaultValue = "ERROR") String key, Model model) {
        try {
            MapStudentAndTeacher.keyCheck(key);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Error", ex.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/mapStudentAndTeacher")
    public String mapStudentAndTeacher(
            @RequestParam(defaultValue = "null") String studentLogin,
            @RequestParam(defaultValue = "null") String teacherLogin, Model model) {
        try {
            MapStudentAndTeacher.mapStudentAndTeacher(studentLogin, teacherLogin);
            model.addAttribute("Message", studentLogin + " успешно добавлен к " + teacherLogin);
            return PATH;
        } catch (RuntimeException ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }
}
