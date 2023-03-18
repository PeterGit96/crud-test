package co.develhope.crud.controllers;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        if(student.getId() == null) {
            throw new IllegalArgumentException("Missed student's id");
        }
        if(studentRepository.existsById(student.getId())) {
            throw new UnsupportedOperationException("Cannot create student with already exists id");
        }
        return studentRepository.saveAndFlush(student);
    }

    @GetMapping({"", "/"})
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    @PutMapping("/edit/{id}")
    public Student updateStudentId(@PathVariable Long id, @RequestBody Student student) {
        if(id == null || student.getId() == null) {
            throw new IllegalArgumentException("Missed student's id");
        }
        if(studentRepository.existsById(student.getId())) {
            throw new UnsupportedOperationException("Cannot update same already exists id");
        }

        Student studentFromDB = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Student studentEdit = new Student(student.getId(), studentFromDB.getName(),
                studentFromDB.getSurname(), studentFromDB.isWorking());
        studentRepository.deleteById(id);
        return studentRepository.saveAndFlush(studentEdit);
    }

    @PutMapping("/edit/{id}/work")
    public Student updateStudentWorkingStatus(@PathVariable Long id, @RequestParam("working") boolean working) {
        return studentService.setStudentIsWorkingStatus(id, working);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        if(!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }

}
