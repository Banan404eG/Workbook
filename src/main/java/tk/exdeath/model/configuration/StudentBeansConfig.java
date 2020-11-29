package tk.exdeath.model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;
import tk.exdeath.model.logic.student.account.AccountStudent;
import tk.exdeath.model.logic.student.account.AuthStudent;
import tk.exdeath.model.logic.student.account.LoggedStudent;
import tk.exdeath.model.logic.student.answers.AddAnswers;
import tk.exdeath.model.logic.student.workbooks.GradeList;
import tk.exdeath.model.logic.student.workbooks.LessonList;
import tk.exdeath.model.logic.student.workbooks.PageList;
import tk.exdeath.model.logic.student.workbooks.StudentPage;

@Configuration
public class StudentBeansConfig {

    @Bean
    @SessionScope
    public LoggedStudent getLoggedStudent() {
        return new LoggedStudent();
    }

    @Bean
    @SessionScope
    public AuthStudent getAuthStudent() {
        return new AuthStudent();
    }

    @Bean
    @SessionScope
    public AccountStudent getAccountStudent() {
        return new AccountStudent();
    }

    @Bean
    @SessionScope
    public AddAnswers getAddAnswers() {
        return new AddAnswers();
    }

    @Bean
    @Scope("singleton")
    public LessonList getLessonList() {
        return new LessonList();
    }

    @Bean
    @Scope("singleton")
    public GradeList getGradeList() {
        return new GradeList();
    }

    @Bean
    @Scope("singleton")
    public PageList getPageList() {
        return new PageList();
    }

    @Bean
    @Scope("singleton")
    public StudentPage getStudentPage() {
        return new StudentPage();
    }
}
