package az.javafx.service.impl;

import az.javafx.dao.StudentDao;
import az.javafx.model.Student;
import az.javafx.service.StudentService;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDao.saveStudent(student);
    }

    @Override
    public List<Student> getAllStudents() {

        return studentDao.getAllStudents();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentDao.getStudentById(id);
    }

    @Override
    public boolean updateStudentById(Student student) {

        return studentDao.updateStudentById(student);
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentDao.hardDeleteStudent(id);
    }

}
