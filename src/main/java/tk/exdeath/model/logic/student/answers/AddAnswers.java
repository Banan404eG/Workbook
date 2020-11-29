package tk.exdeath.model.logic.student.answers;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.logic.student.account.LoggedStudent;

import javax.annotation.Resource;

public class AddAnswers {

    @Resource(name = "getLoggedStudent")
    private LoggedStudent loggedStudent;

    public void addAnswers(String lesson, int grade, int page, String[] answers) {
        Student student = loggedStudent.getStudent();
        if (student == null) {
            throw new RuntimeException("Для использования аккаунта, вы должны пройти аутентификацию");
        }
        student.addTask(new Task(student, lesson, grade, page, answers));
        loggedStudent.update();
    }
}
