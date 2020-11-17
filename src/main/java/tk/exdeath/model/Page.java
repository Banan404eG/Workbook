package tk.exdeath.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workbook.pages")
public class Page implements Serializable {

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
    @Column(name = "number_of_inputs")
    private int numberOfInputs;
    @Column(name = "picture")
    private String picture;

    public Page() {
    }

    public Page(String lesson, int grade, int page, int numberOfInputs, String picture) {
        this.lesson = lesson;
        this.grade = grade;
        this.page = page;
        this.numberOfInputs = numberOfInputs;
        this.picture = picture;
    }

    public int getPage() {
        return page;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public String getPicture() {
        return picture;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
