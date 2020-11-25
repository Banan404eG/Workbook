package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class TasksByStudentID {

    private final List<String> tableNames = new ArrayList<>();
    private final List<Integer> taskIDs = new ArrayList<>();
    Student student;
    int id;

    public TasksByStudentID(int id) {
        StudentService service = new StudentService();
        student = service.readByID(id);
        setTasks();
        service.closeSession();
        this.id = id;
    }

    private void setTasks() {
        for (Task task : student.getTasks()) {
            tableNames.add(task.getTableName());
            taskIDs.add(task.getId());
        }
    }

    public boolean incorrectInput() {
        return id < 1;
    }

    public boolean studentDoesNotExist() {
        return student.getLogin().equals("null");
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public List<Integer> getTaskIDs() {
        return taskIDs;
    }

    public String getStudentName() {
        return student.getFirstName() + " " + student.getSecondName();
    }
}
