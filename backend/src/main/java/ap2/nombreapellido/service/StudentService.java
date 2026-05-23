package ap2.nombreapellido.service;

import ap2.nombreapellido.model.Student;

import java.util.List;

public interface StudentService {

  List<Student> findAll();

  Student findById(Long id);

  Student save(Student student);

  Student update(Long id, Student student);

  void delete(Long id);
}