package tk.exdeath.model.logic.teacher.account;

import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.database.service.TeacherService;

public class LoggedTeacher {

    private final TeacherService teacherService = new TeacherService();
    private Teacher teacher;
    private String login;

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void update() {
        teacherService.update(teacher);
    }

    public void closeSession() {
        teacherService.closeSession();
    }
}
