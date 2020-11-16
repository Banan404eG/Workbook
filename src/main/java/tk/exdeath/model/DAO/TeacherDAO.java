package tk.exdeath.model.DAO;

import tk.exdeath.model.Teacher;

public interface TeacherDAO {
    void create(Teacher teacher);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    Teacher readByName(String firstName, String secondName);

    Teacher readByLogin(String login);

    void closeSession();
}
