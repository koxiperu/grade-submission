package lu.cnfpc.grade_submission.repository;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.cnfpc.grade_submission.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
