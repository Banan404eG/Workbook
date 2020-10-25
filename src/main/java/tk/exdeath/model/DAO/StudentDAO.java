package tk.exdeath.model.DAO;

import tk.exdeath.model.Student;

public interface StudentDAO {
    void create(Student student);

    void update(Student student);

    Student readByName(String firstName, String secondName);
}
