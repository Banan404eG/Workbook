package tk.exdeath.model.logic.teacher.marks;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.logic.teacher.account.LoggedTeacher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MarkList {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;

    private Teacher teacher;
    private List<String> marks;
    private List<String> studentNames;
    private List<Integer> studentIDs;

    public void markList() {
        teacher = loggedTeacher.getTeacher();
        if (teacher == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        setMarks(setStudents());
    }


    private List<Student> setStudents() {
        studentNames = new ArrayList<>();
        studentIDs = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        for (Student student : teacher.getStudents()) {
            students.add(student);
            studentNames.add(student.getFirstName() + " " + student.getSecondName());
            studentIDs.add(student.getStudentID());
        }
        return students;
    }

    private void setMarks(List<Student> students) {
        marks = new ArrayList<>();
        for (Student student : students) {
            StringBuilder studentMarks = new StringBuilder();
            for (Mark mark : teacher.getMarks()) {
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
    }


    public List<String> getMarks() {
        return marks;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public List<Integer> getStudentIDs() {
        return studentIDs;
    }
}
