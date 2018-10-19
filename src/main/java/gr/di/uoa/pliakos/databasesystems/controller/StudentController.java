package gr.di.uoa.pliakos.databasesystems.controller;

import gr.di.uoa.pliakos.databasesystems.domain.Course;
import gr.di.uoa.pliakos.databasesystems.domain.Student;
import gr.di.uoa.pliakos.databasesystems.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/")
class StudentController {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    StudentService studentService;

    @GetMapping
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("student");
        modelAndView.addObject("students", studentService.getStudents());
        return modelAndView;
    }


    @PostMapping(value = "student", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView addStudent(@RequestParam Integer rollNo,
                            @RequestParam String name,
                            @RequestParam String dateOfBirth ) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student");
        try {
            Student student = new Student();
            student.setRollNo(rollNo);
            student.setName(name);
            student.setDateOfBirth(df.parse(dateOfBirth));
            student = studentService.addStudent(student);
            modelAndView.addObject("message", "Student added with name: " + student.getName());
        }
        catch (Exception ex){
            modelAndView.addObject("message", "Failed to add student: " + ex.getMessage());
        }
        modelAndView.addObject("students", studentService.getStudents());
        return modelAndView;
    }

    @GetMapping("/students/{studentId}")
    public ModelAndView show(@PathVariable Integer studentId) {
      ModelAndView modelAndView = new ModelAndView("course");
      modelAndView.addObject("student", studentService.getStudent(studentId));
      return modelAndView;
    }

    @PostMapping("/students/{studentId}/courses")
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView addCourse(@PathVariable Integer studentId,
                            @RequestParam String name,
                            @RequestParam String grade ) throws Exception {

        ModelAndView modelAndView = new ModelAndView("course");
        Student student = studentService.getStudent(studentId);
        try {
          Course course = new Course();
          course.setStudent(student);
          course.setName(name);
          course.setGrade(Integer.parseInt(grade));
          student.getCourses().add(course);
          student = studentService.addStudent(student);
          modelAndView.addObject("message", "Course added with name: " + course.getName());
        }
        catch (Exception ex){
          modelAndView.addObject("message", "Failed to add course: " + ex.getMessage());
        }
        modelAndView.addObject("student", student);
        return modelAndView;
    }

}
