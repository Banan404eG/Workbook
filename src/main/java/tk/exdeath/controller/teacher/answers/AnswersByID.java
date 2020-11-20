package tk.exdeath.controller.teacher.answers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.controller.teacher.LoggedTeacher;
import tk.exdeath.model.Mark;
import tk.exdeath.model.Page;
import tk.exdeath.model.Task;
import tk.exdeath.model.service.PageService;
import tk.exdeath.model.service.StudentService;

@Controller
public class AnswersByID {

    final String PATH = "workbook/page";
    final String INPUT = "<input name=\"marks[]\" placeholder=\"Оценка: \" type=\"text\"><br><br>";
    final int RIGHT_ANSWERS_STUDENT_ID = 4;

    Model model;
    StudentService studentService;
    String lesson;
    int grade, page;

    @GetMapping("/answersByID")
    public String taskByID(@RequestParam(defaultValue = "0") int studentID,
                           @RequestParam(defaultValue = "0") int id, Model model) {

        if (incorrectInput(studentID, id)) {
            model.addAttribute("Error", "Некорректный ID ученика или ID задания");
            return "errorPage";
        }

        studentService = new StudentService();
        this.model = model;

        Task task = getTask(studentID, id);
        if (task == null) {
            model.addAttribute("Error", "Неверный ID");
            return "errorPage";
        }

        setModelAttributes(task, id);
        studentService.closeSession();
        return PATH;
    }


    private boolean incorrectInput(int studentID, int id) {
        return studentID < 1 || id < 1;
    }

    private Task getTask(int studentID, int id) {
        for (Task task : studentService.readByID(studentID).getTasks()) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private void setModelAttributes(Task task, int id) {
        setTaskInfo(task);
        setInputsAndPicture(lesson, grade, page);
        setRightAnswers();
        setMarks(id);
        setRole();
        model.addAttribute("id", id);
    }

    private void setTaskInfo(Task task) {
        lesson = task.getLesson();
        grade = task.getGrade();
        page = task.getPage();
        model.addAttribute("studentAnswers", task.getAnswers());
        model.addAttribute("studentID", task.getStudentID());
    }

    private void setInputsAndPicture(String lesson, int grade, int page) {
        PageService pageService = new PageService();
        Page pageEntity = pageService.read(lesson, grade, page);

        StringBuilder inputs = new StringBuilder();
        for (int i = 0; i < pageEntity.getNumberOfInputs(); i++) {
            inputs.append(INPUT);
        }

        model.addAttribute("picture", pageEntity.getPicture());
        model.addAttribute("inputs", inputs.toString());
        pageService.closeSession();
    }

    private void setRightAnswers() {
        for (Task task : studentService.readByID(RIGHT_ANSWERS_STUDENT_ID).getTasks()) {
            if (task.getLesson().equals(lesson) && task.getGrade() == grade && task.getPage() == page) {
                model.addAttribute("rightAnswers", task.getAnswers());
                return;
            }
        }
        model.addAttribute("rightAnswers", "Правильные ответы не обнаружены");
    }

    private void setMarks(int id) {
        if (LoggedTeacher.getTeacher() == null) {
            return;
        }

        for (Mark mark : LoggedTeacher.getTeacher().getMarks()) {
            if (mark.getTaskID() == id) {
                model.addAttribute("marks", mark.getMarks());
                return;
            }
        }
    }

    private void setRole() {
        if (LoggedTeacher.getTeacher() != null) {
            model.addAttribute("role", "teacher");
        } else {
            model.addAttribute("role", "watcher");
        }
    }
}
