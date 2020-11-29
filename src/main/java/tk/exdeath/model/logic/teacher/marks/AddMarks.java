package tk.exdeath.model.logic.teacher.marks;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.logic.teacher.account.LoggedTeacher;

import javax.annotation.Resource;

public class AddMarks {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;

    private String[] currentMarks;
    private Mark markToUpdate;

    public void addMarks(int id, int studentID, String[] marks) {
        Teacher teacher = loggedTeacher.getTeacher();
        if (markIsNull(teacher, id)) {
            Mark mark = new Mark(getTask(studentID, id), marks, teacher);
            teacher.addMark(mark);
        } else {
            updateMarks(marks);
        }
        loggedTeacher.update();
    }


    private boolean markIsNull(Teacher teacher, int id) {
        for (Mark mark : teacher.getMarks()) {
            if (mark.getTaskID() == id) {
                currentMarks = mark.getMarks();
                markToUpdate = mark;
                return false;
            }
        }
        return true;
    }

    private Task getTask(int studentID, int id) {
        for (Student student : loggedTeacher.getTeacher().getStudents()) {
            if (student.getStudentID() == studentID) {
                for (Task task : student.getTasks()) {
                    if (task.getId() == id) {
                        return task;
                    }
                }
            }
        }
        throw new RuntimeException("Неверный ID задания или ученика");
    }

    private void updateMarks(String[] newMarks) {
        for (int i = 0; i < currentMarks.length; i++) {
            if (!newMarks[i].equals("")) {
                currentMarks[i] = newMarks[i];
            }
        }
        markToUpdate.setMarks(currentMarks);
    }
}
