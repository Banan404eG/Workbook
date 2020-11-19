package tk.exdeath.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workbook.marks")
@IdClass(Mark.PrimaryKey.class)
public class Mark implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(columnDefinition = "varchar[]")
    @Type(type = "tk.exdeath.model.hibernate.SqlStringArray")
    private String[] marks;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Mark() {
    }

    public Mark(Task task, String[] marks, Teacher teacher) {
        this.task = task;
        this.marks = marks;
        this.teacher = teacher;
    }

    public int getTaskID() {
        return task.getId();
    }

    public Task getTask() {
        return task;
    }

    public String[] getMarks() {
        return marks;
    }

    public void setMarks(String[] marks) {
        this.marks = marks;
    }

    public static class PrimaryKey implements Serializable {
        protected Task task;
        protected Teacher teacher;

        public PrimaryKey() {
        }

        public PrimaryKey(Task task, Teacher teacher) {
            this.task = task;
            this.teacher = teacher;
        }
    }
}
