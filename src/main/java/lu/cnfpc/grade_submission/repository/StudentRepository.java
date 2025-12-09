package lu.cnfpc.grade_submission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.cnfpc.grade_submission.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Student datastore
    

    
}
