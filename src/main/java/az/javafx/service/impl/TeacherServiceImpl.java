package az.javafx.service.impl;

import az.javafx.dao.TeacherDao;
import az.javafx.model.Teacher;
import az.javafx.service.TeacherService;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {


    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherDao.saveTeacher(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDao.getTeacherById(id);
    }

    @Override
    public boolean updateTeacherById(Teacher teacher) {
        return teacherDao.updateTeacherById(teacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        return teacherDao.hardDeleteTeacher(id);
    }
}
