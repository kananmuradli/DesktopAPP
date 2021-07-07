package az.javafx.dao;

import az.javafx.model.Teacher;
import java.util.List;

public interface TeacherDao {

    boolean saveTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    boolean softDeleteTeacher(Long id);

    boolean hardDeleteTeacher(Long id);

    Teacher getTeacherById(Long id);

    boolean updateTeacherById(Teacher teacher);
}
