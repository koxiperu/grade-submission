package lu.cnfpc.grade_submission.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lu.cnfpc.grade_submission.model.Course;
import lu.cnfpc.grade_submission.model.Student;
import lu.cnfpc.grade_submission.service.CourseService;
import lu.cnfpc.grade_submission.service.StudentService;

@Controller
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService){
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // A handler method that handles the form submit of a Student
    @PostMapping("/studentSubmit")
    public String submitStudent(@Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form";
        }
        studentService.submitStudent(student);
        return "redirect:/students";
    }

    // A handler method to get the student form
    @GetMapping("/addstudent")
    public String getStudentForm(Model model, @RequestParam(required = false) Long id) {
        Student student = (id!=null) ? studentService.getStudentbyId(id) : new Student();
        model.addAttribute("student", student);
        return "add_student";
    }

    // A handle  method to get the student list
    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    // A handler method that handles a delete student request
    @GetMapping("/delete-student")
    public String getMethodName(@RequestParam Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    } 

    @GetMapping("/students/{student_id}/enrollments")
    public String listCoursesForStudent(@PathVariable("student_id") Long studentId, Model model) {
        Student student = studentService.getStudentbyId(studentId);
        if(student == null) return "redirect:/students";

        List<Course> courses = courseService.getCoursesForStudent(studentId);

        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "student_courses";
    }
    
}
