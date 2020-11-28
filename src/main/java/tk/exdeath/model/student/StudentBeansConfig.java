package tk.exdeath.model.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import tk.exdeath.model.student.account.AccountStudent;
import tk.exdeath.model.student.account.AuthStudent;
import tk.exdeath.model.student.account.LoggedStudent;
import tk.exdeath.model.student.answers.AddAnswers;

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
}
