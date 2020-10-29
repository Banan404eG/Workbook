package tk.exdeath.controller.student;

import tk.exdeath.controller.workbooks.Task;
import tk.exdeath.model.Student;
import tk.exdeath.model.service.StudentService;

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

    public static void setStudent(Student student) {
        LoggedStudent.student = student;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        LoggedStudent.login = login;
    }

    public static void addTask(Task task) {
        student.addTask(task);
    }

    public static void update() {
        studentService.update(student);
    }
}
