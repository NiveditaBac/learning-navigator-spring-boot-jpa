package learning_navigator_springboot_springdatajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning_navigator_springboot_springdatajpa.entity.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long>{

    boolean existsBySubjectName(String subjectName);
    Optional<Subject> findBySubjectName(String subjectName);

}
    