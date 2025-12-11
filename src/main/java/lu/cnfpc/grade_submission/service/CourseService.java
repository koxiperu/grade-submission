package lu.cnfpc.grade_submission.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lu.cnfpc.grade_submission.model.Course;
import lu.cnfpc.grade_submission.model.Enrollment;
import lu.cnfpc.grade_submission.repository.CourseRepository;
import lu.cnfpc.grade_submission.repository.EnrollmentRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository){
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course findById(Long courseId){
        return courseRepository.findById(courseId).orElse(null);
    }

    public List<Course> getCoursesForStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        return enrollments.stream().map(Enrollment::getCourse).collect(Collectors.toList());
    }

}
