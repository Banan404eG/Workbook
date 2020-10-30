package tk.exdeath.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workbook.tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "task_id")
    private int taskID;
    @Column(name = "table_name")
    private String tableName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    public Task(Student student, int taskID, String tableName) {
        this.student = student;
        this.taskID = taskID;
        this.tableName = tableName;
    }
}
