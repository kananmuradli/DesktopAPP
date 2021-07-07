package az.javafx.dao;

import az.javafx.model.Student;
import java.util.List;

public interface StudentDao {

     boolean saveStudent(Student student);

     List<Student> getAllStudents();

     boolean softDeleteStudent(Long id);

     boolean hardDeleteStudent(Long id);

     Student getStudentById(Long id);

     boolean updateStudentById(Student student);
}
