package tk.exdeath.model.student.account;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public abstract class AuthStudent {

    public static void authStudent(String login, String password) {
        StudentService service = LoggedStudent.getStudentService();
        Student student = service.readByLogin(login);
        if (invalidLogin(student)) {
            throw new RuntimeException("Аккаунта с таким логином не существует");
        }
        if (invalidPassword(student, password)) {
            throw new RuntimeException("Неверный пароль, попробуйте ещё раз");
        }
        LoggedStudent.setLogin(login);
        LoggedStudent.setStudent(student);
    }


    private static boolean invalidLogin(Student student) {
        return student.getLogin().equals("null");
    }

    private static boolean invalidPassword(Student student, String password) {
        return !student.getPassword().equals(password);
    }
}
