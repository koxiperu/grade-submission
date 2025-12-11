package lu.cnfpc.grade_submission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lu.cnfpc.grade_submission.exception.StudentNotFoundException;
import lu.cnfpc.grade_submission.model.Course;
import lu.cnfpc.grade_submission.model.Enrollment;
import lu.cnfpc.grade_submission.model.Student;
import lu.cnfpc.grade_submission.repository.EnrollmentRepository;
import lu.cnfpc.grade_submission.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository,CourseService courseService, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Student getStudentbyId(Long student_id){
        return studentRepository.findById(student_id).orElseThrow(()-> new StudentNotFoundException("Student with id "+student_id+" not found") );
    }

    public void submitStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // service to delete student by id
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = getStudentbyId(studentId);
        Course course = courseService.findById(courseId);
        Enrollment existing = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (existing != null) {
            return;
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);
    }
    
        
}
