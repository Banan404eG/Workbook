package tk.exdeath.model.logic.teacher.answers;

import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class TasksByStudentID {

    private List<String> tableNames;
    private List<Integer> taskIDs;
    private Student student;

    public void tasksByStudentID(int id) {
        if (incorrectInput(id)) {
            throw new RuntimeException("Некорректный ввод");
        }
        StudentService service = new StudentService();
        student = service.readByID(id);
        if (studentDoesNotExist()) {
            service.closeSession();
            throw new RuntimeException("Ученика с такими ID не существует");
        }
        setTasks();
        service.closeSession();
    }


    private boolean incorrectInput(int id) {
        return id < 1;
    }

    private boolean studentDoesNotExist() {
        return student.getLogin().equals("null");
    }

    private void setTasks() {
        tableNames = new ArrayList<>();
        taskIDs = new ArrayList<>();
        for (Task task : student.getTasks()) {
            tableNames.add(task.getTableName());
            taskIDs.add(task.getId());
        }
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
