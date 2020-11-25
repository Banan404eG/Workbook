package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

import java.util.List;

public class FindStudentByName {

    private List<Student> students;
    private final String firstName, secondName;
    StudentList studentList;

    public FindStudentByName(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public boolean incorrectInput() {
        return firstName.equals("null") || secondName.equals("null");
    }

    public boolean studentDoesNotExist() {
        StudentService studentService = new StudentService();
        students = studentService.readByName(firstName, secondName);
        studentService.closeSession();
        return students.isEmpty();
    }

    public boolean studentIsUnique() {
        return students.size() == 1;
    }

    public int getStudentID() {
        return students.get(0).getStudentID();
    }

    public void setStudents() {
        studentList = new StudentList();
        studentList.setStudents(students);
    }

    public List<String> getStudentNames() {
        return studentList.getStudentNames();
    }

    public List<String> getStudentIDs() {
        return studentList.getStudentIDs();
    }
}
