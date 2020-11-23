package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarksByStudentID {

    final String PATH = "teacher/marks/marksByStudentID";

    @GetMapping("/marksByStudentID")
    public String marksByStudentID(
            @RequestParam int studentID, Model model) {

        Teacher teacher = LoggedTeacher.getTeacher();
        Student student = getStudent(teacher, studentID);

        if (student == null) {
            model.addAttribute("Error", "Неверный ID ученика");
            return "errorPage";
        }

        setMarks(student, teacher.getMarks(), model);
        return PATH;
    }


    private Student getStudent(Teacher teacher, int studentID) {
        for (Student student : teacher.getStudents()) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private void setMarks(Student student, List<Mark> teacherMarks, Model model) {
        int studentID = student.getStudentID();
        List<String> tableNames = new ArrayList<>();
        List<String> marks = new ArrayList<>();

        for (Mark mark : teacherMarks) {
            StringBuilder studentMarks = new StringBuilder();

            if (mark.getTask().getStudentID() == studentID) {
                for (String string : mark.getMarks()) {
                    studentMarks.append(string);
                    if (!string.equals("")) {
                        studentMarks.append(" ");
                    }
                }
            }
            tableNames.add(mark.getTask().getTableName());
            marks.add(studentMarks.toString());
        }

        model.addAttribute("studentName", student.getFirstName() + " " + student.getSecondName());
        model.addAttribute("tableNames", tableNames);
        model.addAttribute("marks", marks);
    }
}
