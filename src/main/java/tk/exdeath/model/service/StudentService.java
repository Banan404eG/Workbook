package tk.exdeath.model.service;

import tk.exdeath.model.DAO.StudentDAO;
import tk.exdeath.model.DAO.StudentDAOImpl;
import tk.exdeath.model.Student;

public class StudentService {

    private final StudentDAO DAO = new StudentDAOImpl();

    public void create(Student student) {
        DAO.create(student);
    }

    public void update(Student student) {
        DAO.update(student);
    }

    public Student readByName(String firstName, String secondName) {
        return DAO.readByName(firstName, secondName);
    }
}
