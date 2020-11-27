package tk.exdeath.model.student.account;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public abstract class LoggedStudent {

    private static final StudentService studentService = new StudentService();
    private static Student student;
    private static String login;

    public static StudentService getStudentService() {
        return studentService;
    }

    public static Student getStudent() {
        return student;
    }

    public static String getLogin() {
        return login;
    }

    public static void setStudent(Student student) {
        LoggedStudent.student = student;
    }

    public static void setLogin(String login) {
        LoggedStudent.login = login;
    }

    public static void update() {
        studentService.update(student);
    }
}