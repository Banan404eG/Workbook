package tk.exdeath.model.student.answers;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.student.account.LoggedStudent;

public abstract class AddAnswers {

    public static void addAnswers(String lesson, int grade, int page, String[] answers) {
        Student student = LoggedStudent.getStudent();
        student.addTask(new Task(student, lesson, grade, page, answers));
        LoggedStudent.update();
    }
}
