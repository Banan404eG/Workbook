package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

import java.util.List;

public class FindStudentByName {

    private List<Student> students;
    private StudentList studentList;

    public FindStudentByName(String firstName, String secondName) {
        if (incorrectInput(firstName, secondName)) {
            throw new RuntimeException("Некорректный ввод");
        }
        setStudents(firstName, secondName);
        if (studentDoesNotExist()) {
            throw new RuntimeException("Ученика с такими именем и фамилией не существует");
        }
        if (!studentIsUnique()) {
            studentList = new StudentList(students);
        }
    }


    private boolean incorrectInput(String firstName, String secondName) {
        return firstName.equals("null") || secondName.equals("null");
    }

    private void setStudents(String firstName, String secondName) {
        StudentService studentService = new StudentService();
        students = studentService.readByName(firstName, secondName);
        studentService.closeSession();
    }

    private boolean studentDoesNotExist() {
        return students.isEmpty();
    }

    public boolean studentIsUnique() {
        return students.size() == 1;
    }


    public int getStudentID() {
        return students.get(0).getStudentID();
    }

    public List<String> getStudentNames() {
        return studentList.getStudentNames();
    }

    public List<String> getStudentIDs() {
        return studentList.getStudentIDs();
    }
}
