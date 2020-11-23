package tk.exdeath.model.student.account;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public class AuthStudent {

    Student student;
    String login, password;

    public AuthStudent(String login, String password) {
        StudentService service = LoggedStudent.getStudentService();
        student = service.readByLogin(login);
        this.login = login;
        this.password = password;
    }

    public boolean invalidLogin() {
        return student.getLogin().equals("null");
    }

    public boolean invalidPassword() {
        return !student.getPassword().equals(password);
    }

    public void validationIsSuccessful() {
        LoggedStudent.setLogin(login);
        LoggedStudent.setStudent(student);
    }
}
