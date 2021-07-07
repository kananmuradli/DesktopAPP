package az.javafx;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;;
import az.javafx.dao.UserDao;
import az.javafx.dao.impl.UserDaoImpl;
import az.javafx.exceptions.*;
import az.javafx.model.Student;
import az.javafx.model.User;
import az.javafx.service.RegistrationService;
import az.javafx.service.impl.RegistrationServiceImpl;
import az.javafx.util.ControllerUtil;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RegistrationController{

    UserDao userDAO = new UserDaoImpl();
    RegistrationService registrationService = new RegistrationServiceImpl(userDAO);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button registrationBtn;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField surnameField;

    @FXML
    private Group genderButton;

    @FXML
    void initialize() {
        String gender = "";
        String finalGender = gender;
        registrationBtn.setOnAction(event -> {
            User user = new User();
            user.setFirstname(firstnameField.getText());
            user.setUsername(usernameField.getText());
            user.setSurname(surnameField.getText());
            user.setPassword(passwordField.getText());
            user.setGender(finalGender);
            registrationService.addUser(user);
            if (registrationService.addUser(user) == true){
                registrationBtn.getScene().getWindow().hide();
                ControllerUtil.openNewScene(getClass().getResource("login.fxml"));
            }else{
                System.out.println("error");
            }
        });
        }
    }


