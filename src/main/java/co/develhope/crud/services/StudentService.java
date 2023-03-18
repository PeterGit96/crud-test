package co.develhope.crud.services;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student setStudentIsWorkingStatus(Long id, boolean isWorking) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Student not found");
        }
        Student student = studentOptional.get();
        student.setWorking(isWorking);
        return studentRepository.saveAndFlush(student);
    }

}
