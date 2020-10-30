package tk.exdeath.controller.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkbookListController {

    @GetMapping("/workbookList")
    public String getLibrary() {
        return "workbook/workbookList";
    }
}
