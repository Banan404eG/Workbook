package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Mark;
import tk.exdeath.model.Teacher;

@Controller
public class AddMarks {

    private String[] currentMarks;
    private Mark markToUpdate;

    @PostMapping("/marks")
    public String marks(
            @RequestParam(required = false, value = "marks[]") String[] marks,
            @RequestParam() int id, Model model) {

        Teacher teacher = LoggedTeacher.getTeacher();

        if (markIsNull(teacher, id)) {
            Mark mark = new Mark(id, marks, teacher);
            teacher.addMark(mark);
        } else {
            updateMarks(marks);
        }

        LoggedTeacher.update();
        model.addAttribute("Error", marks);
        return "errorPage";
    }

    private boolean markIsNull(Teacher teacher, int id) {
        for (Mark mark : teacher.getMarks()) {
            if (mark.getTaskID() == id) {
                currentMarks = mark.getMarks();
                markToUpdate = mark;
                return false;
            }
        }
        return true;
    }

    private void updateMarks(String[] newMarks) {
        for (int i = 0; i < currentMarks.length; i++) {
            if (!newMarks[i].equals("")) {
                currentMarks[i] = newMarks[i];
            }
        }
        markToUpdate.setMarks(currentMarks);
    }
}
