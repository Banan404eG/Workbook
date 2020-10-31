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

    public Student readByLogin(String login) {

        if (DAO.readByLogin(login) == null) {
            Student student = new Student();
            student.setLogin("null");
            return student;
        }

        return DAO.readByLogin(login);
    }

    public Student readByID(int id) {
        return DAO.readByID(id);
    }
}
