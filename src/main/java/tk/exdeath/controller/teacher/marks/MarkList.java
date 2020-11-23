package tk.exdeath.controller.teacher.marks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarkList {

    final String PATH = "teacher/marks/markList";

    @GetMapping("/markList")
    public String markList(Model model) {

        Teacher teacher = LoggedTeacher.getTeacher();
        List<Student> students = new ArrayList<>();

        setStudents(teacher, students, model);
        setMarks(students, teacher.getMarks(), model);
        return PATH;
    }


    private void setStudents(Teacher teacher, List<Student> students, Model model) {
        List<String> studentNames = new ArrayList<>();
        List<Integer> studentIDs = new ArrayList<>();

        for (Student student : teacher.getStudents()) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName());
            students.add(student);
            studentIDs.add(student.getStudentID());
        }

        model.addAttribute("studentIDs", studentIDs);
        model.addAttribute("students", studentNames);
    }

    private void setMarks(List<Student> students, List<Mark> teacherMarks, Model model) {
        List<String> marks = new ArrayList<>();

        for (Student student : students) {
            StringBuilder studentMarks = new StringBuilder();

            for (Mark mark : teacherMarks) {
                if (mark.getTask().getStudentID() == student.getStudentID()) {
                    for (String string : mark.getMarks()) {
                        studentMarks.append(string);
                        if (!string.equals("")) {
                            studentMarks.append(" ");
                        }
                    }
                }
            }
            marks.add(studentMarks.toString());
        }
        model.addAttribute("marks", marks);
    }
}
