package az.javafx.dao;

import az.javafx.model.Subject;
import java.util.List;

public interface SubjectDao {
    boolean saveSubject(Subject subject);

    List<Subject> getAllSubjects();

    boolean softDeleteSubject(Long id);

    boolean hardDeleteSubject(Long id);

    Subject getSubjectById(Long id);

    boolean updateSubjectById(Subject subject);
}
