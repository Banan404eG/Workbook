package tk.exdeath.model.logic.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.logic.teacher.account.LoggedTeacher;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentList {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;
    private List<String> studentNames;
    private List<String> studentIDs;

    public void studentList() {
        if (loggedTeacher.getTeacher() == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        setNamesAndIDs(loggedTeacher.getTeacher().getStudents());
    }

    public void studentList(List<Student> students) {
        setNamesAndIDs(students);
    }


    private void setNamesAndIDs(Collection<Student> students) {
        studentNames = new ArrayList<>();
        studentIDs = new ArrayList<>();
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
