package tk.exdeath.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountStudent {

    final String PATH = "student/accountStudent";

    @GetMapping("/accountStudent")
    public String returnPage(Model model) {
        model.addAttribute("Name", LoggedStudent.getStudent().getFirstName());
        return PATH;
    }
}
