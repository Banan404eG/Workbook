package tk.exdeath.controller.student.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookLibraryController {

    @GetMapping("/bookLibrary")
    public String getLibrary() {
        return "student/workbook/bookLibrary";
    }
}
