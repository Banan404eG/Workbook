package tk.exdeath.controller.workbook.task;

import tk.exdeath.controller.workbook.WorkbookLibrary;
import tk.exdeath.model.Task;

import java.util.*;

public abstract class TaskLibrary {

    private static Map<Integer, TaskInfo> IDsTasks;
    private static List<String> taskNames;

    public static void setTasks(List<Task> tasks) {
        IDsTasks = new HashMap<>();
        taskNames = new ArrayList<>();

        for (Task task : tasks) {
            String tableName = task.getTableName();
            taskNames.add(tableName);

            WorkbookLibrary.setWorkbook(task.getTaskID(), tableName);

            IDsTasks.put(task.getId(), new TaskInfo(
                    WorkbookLibrary.getGrade(),
                    WorkbookLibrary.getPage(),
                    WorkbookLibrary.getLesson(),
                    tableName,
                    WorkbookLibrary.getStudentAnswers(),
                    WorkbookLibrary.getRightAnswers()));
        }
    }

    public static class TaskInfo {
        int grade, page;
        String tableName, lesson;
        Object studentAnswers, rightAnswers;

        public TaskInfo(int grade, int page, String lesson, String tableName, Object studentAnswers, Object rightAnswers) {
            this.grade = grade;
            this.page = page;
            this.tableName = tableName;
            this.lesson = lesson;
            this.studentAnswers = studentAnswers;
            this.rightAnswers = rightAnswers;
        }
    }

    public static Map<Integer, TaskInfo> getIDsTasks() {
        return IDsTasks;
    }

    public static Set<Integer> getIDs() {
        return IDsTasks.keySet();
    }

    public static List<String> getTaskNames() {
        return taskNames;
    }

    public static TaskInfo getTaskInfo(int id) {
        return IDsTasks.get(id);
    }
}
