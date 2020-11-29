package tk.exdeath.model.logic.teacher.answers;

import tk.exdeath.model.database.entities.Mark;
import tk.exdeath.model.database.entities.Page;
import tk.exdeath.model.database.entities.Task;
import tk.exdeath.model.database.service.PageService;
import tk.exdeath.model.database.service.StudentService;
import tk.exdeath.model.logic.teacher.account.LoggedTeacher;

import javax.annotation.Resource;

public class AnswersByID {

    @Resource(name = "getLoggedTeacher")
    private LoggedTeacher loggedTeacher;

    final int RIGHT_ANSWERS_STUDENT_ID = 4;

    private int studentID, id;
    private String[] rightAnswers;
    private Task task;
    private Page page;

    public void answersByID(int studentID, int id) {
        this.studentID = studentID;
        this.id = id;
        if (incorrectInput()) {
            throw new RuntimeException("Некорректный ID ученика или ID задания");
        }
        StudentService studentService = new StudentService();
        task = setTask(studentService);
        page = setPage();
        rightAnswers = setRightAnswers(studentService);
        studentService.closeSession();
    }


    private boolean incorrectInput() {
        return studentID < 1 || id < 1;
    }

    private Task setTask(StudentService studentService) {
        for (Task task : studentService.readByID(studentID).getTasks()) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new RuntimeException("Задания с таким ID не существует");
    }

    private Page setPage() {
        PageService pageService = new PageService();
        Page page = pageService.read(task.getLesson(), task.getGrade(), task.getPage());
        pageService.closeSession();
        return page;
    }

    private String[] setRightAnswers(StudentService studentService) {
        for (Task task : studentService.readByID(RIGHT_ANSWERS_STUDENT_ID).getTasks()) {
            if (task.getLesson().equals(this.task.getLesson()) &&
                    task.getGrade() == this.task.getGrade() &&
                    task.getPage() == this.task.getPage()) {
                return task.getAnswers();
            }
        }
        return new String[]{"Правильные ответы не обнаружены"};
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
        return rightAnswers;
    }

    public String getRole() {
        if (loggedTeacher.getTeacher() != null) {
            return "teacher";
        }
        return "watcher";
    }

    public String[] getMarks() {
        if (loggedTeacher.getTeacher() == null) {
            return new String[]{};
        }
        for (Mark mark : loggedTeacher.getTeacher().getMarks()) {
            if (mark.getTaskID() == id) {
                return mark.getMarks();
            }
        }
        return new String[]{};
    }
}
