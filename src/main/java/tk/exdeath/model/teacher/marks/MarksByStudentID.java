package tk.exdeath.model.teacher.marks;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.teacher.account.LoggedTeacher;

import java.util.ArrayList;
import java.util.List;

public class MarksByStudentID {

    private final Teacher teacher;
    private final int studentID;
    private Student student;
    private final List<String> tableNames = new ArrayList<>();
    private final List<String> marks = new ArrayList<>();

    public MarksByStudentID(int studentID) {
        teacher = LoggedTeacher.getTeacher();
        if (teacher == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        this.studentID = studentID;
        setStudent();
        setMarks();
    }


    private void setStudent() {
        for (Student student : teacher.getStudents()) {
            if (student.getStudentID() == studentID) {
                this.student = student;
                return;
            }
        }
        throw new RuntimeException("Неверный ID ученика");
    }

    private void setMarks() {
        for (Mark mark : teacher.getMarks()) {
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
    }


    public String getStudentName() {
        return student.getFirstName() + " " + student.getSecondName();
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public List<String> getMarks() {
        return marks;
    }
}
