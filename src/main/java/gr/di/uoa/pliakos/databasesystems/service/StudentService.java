package gr.di.uoa.pliakos.databasesystems.service;

import gr.di.uoa.pliakos.databasesystems.domain.Student;
import gr.di.uoa.pliakos.databasesystems.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Integer studentId){
      return studentRepository.findOne(studentId);
  }
}
