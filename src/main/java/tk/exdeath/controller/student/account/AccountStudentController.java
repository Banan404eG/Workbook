package tk.exdeath.controller.student.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.student.account.AccountStudent;

@Controller
public class AccountStudentController {

    final String PATH = "student/account/accountStudent";

    @GetMapping("/accountStudent")
    public String returnPage(Model model) {
        model.addAttribute("Name", AccountStudent.getStudentName());
        return PATH;
    }
}
