package tk.exdeath.model.service;

import tk.exdeath.model.DAO.TeacherDAO;
import tk.exdeath.model.DAO.TeacherDAOImpl;
import tk.exdeath.model.Teacher;

public class TeacherService {

    private final TeacherDAO DAO = new TeacherDAOImpl();

    public void create(Teacher teacher) {
        DAO.create(teacher);
    }

    public void update(Teacher teacher) {
        DAO.update(teacher);
    }

    public void delete(Teacher teacher) {
        DAO.delete(teacher);
    }

    public Teacher readByName(String firstName, String secondName) {
        Teacher teacher = DAO.readByName(firstName, secondName);
        return nullCheck(teacher);
    }

    public Teacher readByLogin(String login) {
        Teacher teacher = DAO.readByLogin(login);
        return nullCheck(teacher);
    }

    private Teacher nullCheck(Teacher teacher) {
        if (teacher == null) {
            teacher = new Teacher();
            teacher.setLogin("null");
        }
        return teacher;
    }
}
