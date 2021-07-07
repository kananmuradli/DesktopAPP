package az.javafx.service.impl;

import az.javafx.dao.SubjectDao;
import az.javafx.model.Subject;
import az.javafx.service.SubjectService;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public boolean addSubject(Subject subject) {
        return subjectDao.saveSubject(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.getAllSubjects();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectDao.getSubjectById(id);
    }

    @Override
    public boolean updateSubjectById(Subject subject) {
        return subjectDao.updateSubjectById(subject);
    }

    @Override
    public boolean deleteSubject(Long id) {
        return subjectDao.hardDeleteSubject(id);
    }
}
