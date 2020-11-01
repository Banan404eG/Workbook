package tk.exdeath.model.workbooks.Informatics.grade7.page8;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workbook.inf7_8")
public class Informatics78 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "three1")
    private String three1;
    @Column(name = "three2")
    private String three2;
    @Column(name = "four11")
    private String four11;
    @Column(name = "four12")
    private String four12;
    @Column(name = "four21")
    private String four21;
    @Column(name = "four22")
    private String four22;
    @Column(name = "four31")
    private String four31;
    @Column(name = "four32")
    private String four32;
    @Column(name = "four41")
    private String four41;
    @Column(name = "four42")
    private String four42;
    @Column(name = "four51")
    private String four51;
    @Column(name = "four52")
    private String four52;
    @Column(name = "four61")
    private String four61;
    @Column(name = "four62")
    private String four62;
    @Column(name = "four71")
    private String four71;
    @Column(name = "four72")
    private String four72;
    @Column(name = "four81")
    private String four81;
    @Column(name = "four82")
    private String four82;
    @Column(name = "four91")
    private String four91;
    @Column(name = "four92")
    private String four92;
    @Column(name = "student_id")
    private int studentID;

    @Override
    public String toString() {
        return "Ответы ученика: \n" +
                "№3 - " + three1 + ", " + three2 +
                "\n №4 \n" + four11 + ", " + four12 +
                "\n" + four21 + ", " + four22 +
                "\n" + four31 + ", " + four32 +
                "\n" + four41 + ", " + four42 +
                "\n" + four51 + ", " + four52 +
                "\n" + four61 + ", " + four62 +
                "\n" + four71 + ", " + four72 +
                "\n" + four81 + ", " + four82 +
                "\n" + four91 + ", " + four92;
    }

    public Informatics78() {
    }

    public Informatics78(String three1, String three2,
                         String four11, String four12,
                         String four21, String four22,
                         String four31, String four32,
                         String four41, String four42,
                         String four51, String four52,
                         String four61, String four62,
                         String four71, String four72,
                         String four81, String four82,
                         String four91, String four92,
                         int studentID) {
        this.three1 = three1;
        this.three2 = three2;
        this.four11 = four11;
        this.four12 = four12;
        this.four21 = four21;
        this.four22 = four22;
        this.four31 = four31;
        this.four32 = four32;
        this.four41 = four41;
        this.four42 = four42;
        this.four51 = four51;
        this.four52 = four52;
        this.four61 = four61;
        this.four62 = four62;
        this.four71 = four71;
        this.four72 = four72;
        this.four81 = four81;
        this.four82 = four82;
        this.four91 = four91;
        this.four92 = four92;
        this.studentID = studentID;
    }

    public int getId() {
        return id;
    }
}
