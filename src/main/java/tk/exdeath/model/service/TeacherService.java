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

    public Teacher readByName(String firstName, String secondName) {
        return DAO.readByName(firstName, secondName);
    }
}
