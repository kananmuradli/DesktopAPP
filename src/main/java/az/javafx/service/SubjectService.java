package az.javafx.service;

import az.javafx.model.Subject;

import java.util.List;

public interface SubjectService {

    boolean addSubject(Subject subject);

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);

    boolean updateSubjectById(Subject subject);

    boolean deleteSubject(Long id);
}
