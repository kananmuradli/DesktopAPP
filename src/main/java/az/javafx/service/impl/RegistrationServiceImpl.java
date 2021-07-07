package az.javafx.service.impl;

import az.javafx.dao.UserDao;
import az.javafx.model.Credential;
import az.javafx.model.User;
import az.javafx.service.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    private UserDao userDao;

    public RegistrationServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

}
