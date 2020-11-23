package tk.exdeath.model.database.DAO;

import tk.exdeath.model.database.entities.Teacher;

public interface TeacherDAO {
    void create(Teacher teacher);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    Teacher readByName(String firstName, String secondName);

    Teacher readByLogin(String login);

    void closeSession();
}
