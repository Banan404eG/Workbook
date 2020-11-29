package tk.exdeath.model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import tk.exdeath.model.logic.teacher.account.AccountTeacher;
import tk.exdeath.model.logic.teacher.account.AuthTeacher;
import tk.exdeath.model.logic.teacher.account.LoggedTeacher;
import tk.exdeath.model.logic.teacher.answers.AnswersByID;
import tk.exdeath.model.logic.teacher.answers.TasksByStudentID;
import tk.exdeath.model.logic.teacher.marks.AddMarks;
import tk.exdeath.model.logic.teacher.marks.MarkList;
import tk.exdeath.model.logic.teacher.marks.MarksByStudentID;
import tk.exdeath.model.logic.teacher.students.FindStudentByName;
import tk.exdeath.model.logic.teacher.students.StudentList;

@Configuration
public class TeacherBeansConfig {

    @Bean
    @SessionScope
    public LoggedTeacher getLoggedTeacher() {
        return new LoggedTeacher();
    }

    @Bean
    @SessionScope
    public AuthTeacher getAuthTeacher() {
        return new AuthTeacher();
    }

    @Bean
    @SessionScope
    public AccountTeacher getAccountTeacher() {
        return new AccountTeacher();
    }

    @Bean
    @SessionScope
    public AnswersByID getAnswersByID() {
        return new AnswersByID();
    }

    @Bean
    @SessionScope
    public TasksByStudentID getTasksByStudentID() {
        return new TasksByStudentID();
    }

    @Bean
    @SessionScope
    public AddMarks getAddMarks() {
        return new AddMarks();
    }

    @Bean
    @SessionScope
    public MarkList getMarkList() {
        return new MarkList();
    }

    @Bean
    @SessionScope
    public MarksByStudentID getMarksByStudentID() {
        return new MarksByStudentID();
    }

    @Bean
    @SessionScope
    public FindStudentByName getFindStudentByName() {
        return new FindStudentByName();
    }

    @Bean
    @SessionScope
    public StudentList getStudentList() {
        return new StudentList();
    }
}
