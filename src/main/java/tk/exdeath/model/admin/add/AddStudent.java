package tk.exdeath.model.admin.add;

import tk.exdeath.model.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public abstract class AddStudent {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void addStudent(String login, String password, String firstName, String secondName, String studentClass) {
        if (incorrectInput(login, password, firstName, secondName, studentClass)) {
            throw new RuntimeException("Некорректный ввод");
        }
        StudentService studentService = new StudentService();
        if (loginIsInvalid(login, studentService)) {
            studentService.closeSession();
            throw new RuntimeException("Ученик с таким логином уже существует");
        }
        studentService.create(new Student(login, password, firstName, secondName, studentClass));
        studentService.closeSession();
    }


    private static boolean incorrectInput(String login, String password, String firstName, String secondName, String studentClass) {
        return login.equals("null") || password.equals("null") || firstName.equals("null") || secondName.equals("null") || studentClass.equals("null");
    }

    private static boolean loginIsInvalid(String login, StudentService studentService) {
        return !studentService.readByLogin(login).getLogin().equals("null");
    }
}
