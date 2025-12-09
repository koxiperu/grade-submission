package lu.cnfpc.grade_submission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lu.cnfpc.grade_submission.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
