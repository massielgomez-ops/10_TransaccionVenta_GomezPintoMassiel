package ap2.nombreapellido.repository;

import ap2.nombreapellido.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}