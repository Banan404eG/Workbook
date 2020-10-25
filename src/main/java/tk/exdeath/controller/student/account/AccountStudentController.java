package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.controller.student.LoggedStudent;

@Controller
public class AccountStudentController {

    final String PATH = "student/account/accountStudent";

    @GetMapping("/accountStudent")
    public String accountTeacher(Model model) {
        model.addAttribute("Name", LoggedStudent.getStudent().getFirstName());
        return PATH;
    }
}
