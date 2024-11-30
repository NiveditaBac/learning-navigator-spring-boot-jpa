package learning_navigator_springboot_springdatajpa.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long studentRegistrationId;

    @Column(nullable = false)
    private String studentName;

    @ManyToMany
    @JoinTable(name = "student_subject", 
        joinColumns = {@JoinColumn(name="fk_student")}, 
        inverseJoinColumns = {@JoinColumn(name="fk_subject")})
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "student_exam", 
        joinColumns = {@JoinColumn(name="fk_student")}, 
        inverseJoinColumns = {@JoinColumn(name="fk_exam")})
    private Set<Exam> exams = new HashSet<>();  


    //Add Subject to Set
    public void addSubject(Subject subject){

        if(this.subjects == null){
            this.subjects = new HashSet<>();
        }
        this.subjects.add(subject);
        if(subject.getStudents() == null){
            subject.setStudents(new HashSet<>());
        }
        subject.getStudents().add(this);
    }

    //Remove Subject from Set
    public void removeSubject(Long subjectId){
        Subject subject = this.subjects.stream().filter(s -> Objects.equals(s.getSubjectId(), subjectId)).findFirst().orElse(null);
        if(subject != null){
            this.subjects.remove(subject);
            subject.getStudents().remove(this);
        }
    }

    //Add Exam to Set
    public void addExam(Exam exam){

        if(this.exams == null){
            this.exams = new HashSet<>();
        }
        this.exams.add(exam);
        if(exam.getStudents() == null){
            exam.setStudents(new HashSet<>());
        }
        exam.getStudents().add(this);
    }

    //Remove Exam from Set
    public void removeExam(Long examId){
        Exam exam = this.exams.stream().filter(e -> Objects.equals(e.getExamId(), examId)).findFirst().orElse(null);
        if(exam != null){
            this.exams.remove(exam);
            exam.getStudents().remove(this);
        }
    }
}
