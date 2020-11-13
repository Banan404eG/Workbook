package tk.exdeath.model.DAO;

import tk.exdeath.model.Student;

import java.util.List;

public interface StudentDAO {
    void create(Student student);

    void update(Student student);

    void delete(Student student);

    Student readByName(String firstName, String secondName);

    List<Student> readListByName(String firstName, String secondName);

    Student readByLogin(String login);

    Student readByID(int id);
}
