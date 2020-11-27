package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.teacher.account.LoggedTeacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentList {

    private final List<String> studentNames = new ArrayList<>();
    private final List<String> studentIDs = new ArrayList<>();

    public StudentList() {
        if (LoggedTeacher.getTeacher() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        setNamesAndIDs(LoggedTeacher.getTeacher().getStudents());
    }

    public StudentList(List<Student> students) {
        setNamesAndIDs(students);
    }


    private void setNamesAndIDs(Collection<Student> students) {
        for (Student student : students) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName() + " " + student.getStudentClass());
            studentIDs.add(String.valueOf(student.getStudentID()));
        }
    }


    public List<String> getStudentNames() {
        return studentNames;
    }

    public List<String> getStudentIDs() {
        return studentIDs;
    }
}
