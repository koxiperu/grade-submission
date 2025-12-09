package lu.cnfpc.grade_submission.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {
   

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id",nullable=false)

    private Course course;
    private String score;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id",nullable=false)

    private Student student;

    // public Grade(String name, String subject, String score) {
    //     this.id = UUID.randomUUID().toString();
    //     this.name = name;
    //     this.subject = subject;
    //     this.score = score;
    // }


    // New grade constructor using the html form
   
    

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    

    
}
