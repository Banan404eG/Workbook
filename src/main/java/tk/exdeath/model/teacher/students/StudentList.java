package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.teacher.account.LoggedTeacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentList {

    private final List<String> studentNames = new ArrayList<>();
    private final List<String> studentIDs = new ArrayList<>();

    public List<String> getStudentNames() {
        return studentNames;
    }

    public List<String> getStudentIDs() {
        return studentIDs;
    }

    public void setStudents() {
        setNamesAndIDs(LoggedTeacher.getTeacher().getStudents());
    }

    public void setStudents(List<Student> students) {
        setNamesAndIDs(students);
    }

    private void setNamesAndIDs(Collection<Student> students) {
        for (Student student : students) {
            studentNames.add(student.getFirstName() + " " + student.getSecondName() + " " + student.getStudentClass());
            studentIDs.add(String.valueOf(student.getStudentID()));
        }
    }
}
