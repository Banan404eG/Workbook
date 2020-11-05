package tk.exdeath.controller.workbook.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskByIDController {

    @GetMapping("/taskByID")
    public String taskByID(
            @RequestParam int id, Model model) {

        TaskLibrary.TaskInfo taskInfo = TaskLibrary.getTaskInfo(id);

        model.addAttribute("studentAnswers", taskInfo.studentAnswers);
        model.addAttribute("role", "teacher");
        model.addAttribute("rightAnswers", taskInfo.rightAnswers);
        return "workbook/" + taskInfo.lesson + "/" + taskInfo.grade + "/" + taskInfo.page;
    }
}
