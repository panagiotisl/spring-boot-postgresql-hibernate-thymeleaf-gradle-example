package gr.di.uoa.pliakos.databasesystems.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_mst", schema = "public")
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    String name;
    int grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rollNo", nullable = false)
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
      this.student = student;
    }

}
