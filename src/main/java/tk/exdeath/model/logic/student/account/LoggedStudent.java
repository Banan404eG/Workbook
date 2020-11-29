package tk.exdeath.model.logic.student.account;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public class LoggedStudent {

    private final StudentService studentService = new StudentService();
    private Student student;
    private String login;

    public StudentService getStudentService() {
        return studentService;
    }

    public Student getStudent() {
        return student;
    }

    public String getLogin() {
        return login;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void update() {
        studentService.update(student);
    }

    public void closeSession() {
        studentService.closeSession();
    }
}
