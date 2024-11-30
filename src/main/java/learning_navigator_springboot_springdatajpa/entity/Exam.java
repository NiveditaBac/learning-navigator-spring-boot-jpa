package learning_navigator_springboot_springdatajpa.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "exams")
@Entity
public class Exam {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long examId;

    @Column(nullable = false, unique = true)
    private String subjectName;

    @ManyToMany(mappedBy="exams")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
}
    