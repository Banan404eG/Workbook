package tk.exdeath.model.logic.admin.delete;

import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.service.StudentService;

public abstract class DeleteStudent {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void deleteStudent(String login) {
        if (incorrectInput(login)) {
            throw new RuntimeException("Некорректный ввод");
        }
        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(login);
        if (loginIsInvalid(student)) {
            studentService.closeSession();
            throw new RuntimeException("Ученика с таким логином не существует");
        }
        studentService.delete(student);
        studentService.closeSession();
    }


    private static boolean incorrectInput(String login) {
        return login.equals("null");
    }

    private static boolean loginIsInvalid(Student student) {
        return student.getLogin().equals("null");
    }
}
