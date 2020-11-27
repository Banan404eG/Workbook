package tk.exdeath.model.teacher.marks;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Student;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.entities.Teacher;
import tk.exdeath.model.teacher.account.LoggedTeacher;

public abstract class AddMarks {

    private static String[] currentMarks;
    private static Mark markToUpdate;

    public static void addMarks(int id, int studentID, String[] marks) {
        Teacher teacher = LoggedTeacher.getTeacher();
        if (markIsNull(teacher, id)) {
            Mark mark = new Mark(getTask(studentID, id), marks, teacher);
            teacher.addMark(mark);
        } else {
            updateMarks(marks);
        }
        LoggedTeacher.update();
    }


    private static boolean markIsNull(Teacher teacher, int id) {
        for (Mark mark : teacher.getMarks()) {
            if (mark.getTaskID() == id) {
                currentMarks = mark.getMarks();
                markToUpdate = mark;
                return false;
            }
        }
        return true;
    }

    private static Task getTask(int studentID, int id) {
        for (Student student : LoggedTeacher.getTeacher().getStudents()) {
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

    private static void updateMarks(String[] newMarks) {
        for (int i = 0; i < currentMarks.length; i++) {
            if (!newMarks[i].equals("")) {
                currentMarks[i] = newMarks[i];
            }
        }
        markToUpdate.setMarks(currentMarks);
    }
}
