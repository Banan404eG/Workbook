package tk.exdeath.model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import tk.exdeath.model.logic.admin.account.AccountAdmin;
import tk.exdeath.model.logic.admin.account.AuthAdmin;
import tk.exdeath.model.logic.admin.account.LoggedAdmin;
import tk.exdeath.model.logic.admin.add.AddPage;
import tk.exdeath.model.logic.admin.add.AddStudent;
import tk.exdeath.model.logic.admin.add.AddTeacher;
import tk.exdeath.model.logic.admin.add.MapStudentAndTeacher;
import tk.exdeath.model.logic.admin.delete.DeletePage;
import tk.exdeath.model.logic.admin.delete.DeleteStudent;
import tk.exdeath.model.logic.admin.delete.DeleteTeacher;

@Configuration
public class AdminBeansConfig {

    @Bean
    @SessionScope
    public LoggedAdmin getLoggedAdmin() {
        return new LoggedAdmin();
    }

    @Bean
    @SessionScope
    public AuthAdmin getAuthAdmin() {
        return new AuthAdmin();
    }

    @Bean
    @SessionScope
    public AccountAdmin getAccountAdmin() {
        return new AccountAdmin();
    }

    @Bean
    @SessionScope
    public AddPage getAddPage() {
        return new AddPage();
    }

    @Bean
    @SessionScope
    public AddStudent getAddStudent() {
        return new AddStudent();
    }

    @Bean
    @SessionScope
    public AddTeacher getAddTeacher() {
        return new AddTeacher();
    }

    @Bean
    @SessionScope
    public MapStudentAndTeacher getMapStudentAndTeacher() {
        return new MapStudentAndTeacher();
    }

    @Bean
    @SessionScope
    public DeletePage getDeletePage() {
        return new DeletePage();
    }

    @Bean
    @SessionScope
    public DeleteStudent getDeleteStudent() {
        return new DeleteStudent();
    }

    @Bean
    @SessionScope
    public DeleteTeacher getDeleteTeacher() {
        return new DeleteTeacher();
    }
}
