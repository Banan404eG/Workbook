package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Mark;
import tk.exdeath.model.Student;
import tk.exdeath.model.Teacher;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarkList {

    private List<Student> students;

    @GetMapping("/markList")
    public String markList(Model model) {

        Teacher teacher = LoggedTeacher.getTeacher();

        model.addAttribute("students", getStudentNames(teacher));
        model.addAttribute("marks", getMarks(students, teacher.getMarks()));
        return "teacher/markList";
    }


    private List<String> getStudentNames(Teacher teacher) {
        List<String> studentNames = new ArrayList<>();
        students = new ArrayList<>();

        for (Student student : teacher.getStudents()) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName());
            students.add(student);
        }

        return studentNames;
    }

    private List<String> getMarks(List<Student> students, List<Mark> teacherMarks) {

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

        return marks;
    }
}
