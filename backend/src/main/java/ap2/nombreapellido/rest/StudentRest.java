package ap2.nombreapellido.rest;

import ap2.nombreapellido.model.Student;
import ap2.nombreapellido.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/students")
public class StudentRest {

  private final StudentService studentService;

  public StudentRest(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<Student>> findAll() {
    return ResponseEntity.ok(studentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> findById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Student> save(@Valid @RequestBody Student student) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {
    return ResponseEntity.ok(studentService.update(id, student));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    studentService.delete(id);
    return ResponseEntity.noContent().build();
  }
}