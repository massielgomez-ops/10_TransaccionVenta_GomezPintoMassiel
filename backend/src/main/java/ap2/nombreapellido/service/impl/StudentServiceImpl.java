package ap2.nombreapellido.service.impl;

import ap2.nombreapellido.model.Student;
import ap2.nombreapellido.repository.StudentRepository;
import ap2.nombreapellido.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Student findById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado"));
  }

  @Override
  public Student save(Student student) {
    student.setId(null);
    return studentRepository.save(student);
  }

  @Override
  @Transactional
  public Student update(Long id, Student student) {
    Student currentStudent = findById(id);

    currentStudent.setNombre(student.getNombre());
    currentStudent.setApellido(student.getApellido());
    currentStudent.setCorreo(student.getCorreo());
    currentStudent.setTelefono(student.getTelefono());
    currentStudent.setEdad(student.getEdad());
    currentStudent.setCarrera(student.getCarrera());
    currentStudent.setCodigo(student.getCodigo());
    currentStudent.setDireccion(student.getDireccion());

    return studentRepository.save(currentStudent);
  }

  @Override
  public void delete(Long id) {
    Student student = findById(id);
    studentRepository.delete(student);
  }
}