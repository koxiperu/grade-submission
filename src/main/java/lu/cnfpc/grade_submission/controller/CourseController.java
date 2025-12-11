package lu.cnfpc.grade_submission.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lu.cnfpc.grade_submission.model.Course;
import lu.cnfpc.grade_submission.model.Student;
import lu.cnfpc.grade_submission.repository.CourseRepository;
import lu.cnfpc.grade_submission.repository.StudentRepository;
import lu.cnfpc.grade_submission.service.CourseService;
import lu.cnfpc.grade_submission.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.transaction.Transactional;


@Controller
public class CourseController {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, StudentService studentService, StudentRepository studentRepository, CourseRepository courseRepository){
        this.courseService  = courseService;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/courses/{courseId}/enroll/{studentId}")
    public String enrollStudent(@PathVariable Long courseId,
                                @PathVariable Long studentId) {
        studentService.enrollStudentInCourse(studentId, courseId);
        return "redirect:/students/" + studentId + "/enrollments";
    }

    

    
}
