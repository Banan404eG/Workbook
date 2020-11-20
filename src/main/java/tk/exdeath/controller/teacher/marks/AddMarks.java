package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Mark;
import tk.exdeath.model.Student;
import tk.exdeath.model.Task;
import tk.exdeath.model.Teacher;

@Controller
public class AddMarks {

    private String[] currentMarks;
    private Mark markToUpdate;

    @PostMapping("/marks")
    public String marks(
            @RequestParam(required = false, value = "marks[]") String[] marks,
            @RequestParam int id,
            @RequestParam int studentID) {

        Teacher teacher = LoggedTeacher.getTeacher();

        if (markIsNull(teacher, id)) {
            Mark mark = new Mark(getTask(studentID, id), marks, teacher);
            teacher.addMark(mark);
        } else {
            updateMarks(marks);
        }

        LoggedTeacher.update();
        return "redirect:/answersByID?studentID=" + studentID + "&id=" + id;
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

    private Task getTask(int studentID, int id) {
        for (Student student : LoggedTeacher.getTeacher().getStudents()) {
            if (student.getStudentID() == studentID) {
                for (Task task : student.getTasks()) {
                    if (task.getId() == id) {
                        return task;
                    }
                }
            }
        }
        return null;
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
