package co.develhope.crud;

import co.develhope.crud.entities.Student;
import co.develhope.crud.repositories.StudentRepository;
import co.develhope.crud.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentActivationTest() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Pietro");
        student.setSurname("Gallina");
        student.setWorking(true);

        Student studentFromDB = studentRepository.saveAndFlush(student);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.setStudentIsWorkingStatus(student.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

        Student studentFromFind = studentRepository.findById(studentFromDB.getId()).get();
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull();
        assertThat(studentFromFind.getId()).isEqualTo(studentFromDB.getId());
        assertThat(studentFromFind.isWorking()).isTrue();
    }
}
