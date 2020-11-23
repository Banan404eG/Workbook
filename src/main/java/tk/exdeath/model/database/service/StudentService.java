package tk.exdeath.model.database.service;

import tk.exdeath.model.database.DAO.StudentDAO;
import tk.exdeath.model.database.DAO.StudentDAOImpl;
import tk.exdeath.model.database.entities.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO DAO = new StudentDAOImpl();

    public void create(Student student) {
        DAO.create(student);
    }

    public void update(Student student) {
        DAO.update(student);
    }

    public void delete(Student student) {
        DAO.delete(student);
    }

    public List<Student> readByName(String firstName, String secondName) {
        return DAO.readListByName(firstName, secondName);
    }

    public Student readByLogin(String login) {
        Student student = DAO.readByLogin(login);
        return nullCheck(student);
    }

    public Student readByID(int id) {
        Student student = DAO.readByID(id);
        return nullCheck(student);
    }

    private Student nullCheck(Student student) {
        if (student == null) {
            student = new Student();
            student.setLogin("null");
        }
        return student;
    }

    public void closeSession() {
        DAO.closeSession();
    }
}
