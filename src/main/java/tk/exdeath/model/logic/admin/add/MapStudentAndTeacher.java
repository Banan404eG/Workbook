package tk.exdeath.model.logic.admin.add;

import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.StudentService;
import tk.exdeath.model.database.service.TeacherService;

public abstract class MapStudentAndTeacher {

    public static void keyCheck(String key) {
        LoggedAdmin.keyCheck(key);
    }

    public static void mapStudentAndTeacher(String studentLogin, String teacherLogin) {
        if (incorrectInput(studentLogin, teacherLogin)) {
            throw new RuntimeException("Некорректный ввод");
        }
        Student student = setStudent(studentLogin);
        updateTeacher(teacherLogin, student);
    }


    private static Student setStudent(String studentLogin) {
        StudentService studentService = new StudentService();
        Student student = studentService.readByLogin(studentLogin);
        if (loginIsInvalid(student.getLogin())) {
            studentService.closeSession();
            throw new RuntimeException("Ученика с таким логином не существует");
        }
        studentService.closeSession();
        return student;
    }

    private static void updateTeacher(String teacherLogin, Student student) {
        TeacherService teacherService = new TeacherService();
        Teacher teacher = teacherService.readByLogin(teacherLogin);
        if (loginIsInvalid(teacher.getLogin())) {
            teacherService.closeSession();
            throw new RuntimeException("Учителя с таким логином не существует");
        }
        teacher.addStudent(student);
        teacherService.update(teacher);
        teacherService.closeSession();
    }

    private static boolean incorrectInput(String studentLogin, String teacherLogin) {
        return studentLogin.equals("null") || teacherLogin.equals("null");
    }

    private static boolean loginIsInvalid(String login) {
        return login.equals("null");
    }
}
