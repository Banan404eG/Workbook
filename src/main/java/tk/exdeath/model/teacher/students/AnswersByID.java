package tk.exdeath.model.teacher.students;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.service.PageService;
import tk.exdeath.model.database.service.StudentService;
import tk.exdeath.model.teacher.account.LoggedTeacher;

public class AnswersByID {

    private final int RIGHT_ANSWERS_STUDENT_ID = 4;

    private final int studentID, id;
    private final StudentService studentService;
    private final Task task;
    private Page page;

    public AnswersByID(int studentID, int id) {
        studentService = new StudentService();
        this.studentID = studentID;
        this.id = id;
        task = getTask();
        if (task != null) {
            page = getPage();
        }
    }

    private Task getTask() {
        for (Task task : studentService.readByID(studentID).getTasks()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private Page getPage() {
        PageService pageService = new PageService();
        Page page = pageService.read(task.getLesson(), task.getGrade(), task.getPage());
        pageService.closeSession();
        return page;
    }

    public boolean incorrectInput() {
        return studentID < 1 || id < 1;
    }

    public boolean taskDoesNotExist() {
        return task == null;
    }

    public boolean teacherIsLogged() {
        return LoggedTeacher.getTeacher() != null;
    }

    public String getPicture() {
        return page.getPicture();
    }

    public String getInputs(String INPUT) {
        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < page.getNumberOfInputs(); i++) {
            inputs.append(INPUT);
        }
        return inputs.toString();
    }

    public String[] getStudentAnswers() {
        return task.getAnswers();
    }

    public String[] getRightAnswers() {
        for (Task task : studentService.readByID(RIGHT_ANSWERS_STUDENT_ID).getTasks()) {
            if (task.getLesson().equals(this.task.getLesson()) &&
                    task.getGrade() == this.task.getGrade() &&
                    task.getPage() == this.task.getPage()) {
                return task.getAnswers();
            }
        }
        return new String[]{"Правильные ответы не обнаружены"};
    }

    public String[] getMarks() {
        for (Mark mark : LoggedTeacher.getTeacher().getMarks()) {
            if (mark.getTaskID() == id) {
                return mark.getMarks();
            }
        }
        return new String[]{};
    }
}
