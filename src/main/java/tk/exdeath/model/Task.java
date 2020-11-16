package tk.exdeath.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workbook.tasks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lesson")
    private String lesson;
    @Column(name = "grade")
    private int grade;
    @Column(name = "page")
    private int page;
    @Column(columnDefinition = "varchar[]")
    @Type(type = "tk.exdeath.model.hibernate.SqlStringArray")
    private String[] answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


    public Task() {
    }

    public Task(Student student, String lesson, int grade, int page, String[] answers) {
        this.student = student;
        this.lesson = lesson;
        this.grade = grade;
        this.page = page;
        this.answers = answers;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getStudentID() {
        return student.getStudentID();
    }

    public int getId() {
        return id;
    }

    public String getLesson() {
        return lesson;
    }

    public int getGrade() {
        return grade;
    }

    public int getPage() {
        return page;
    }

    public String getTableName() {
        return lesson + ", класс: " + grade + ", страница: " + page;
    }
}
