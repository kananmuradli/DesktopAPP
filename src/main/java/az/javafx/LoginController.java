package az.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import az.javafx.dao.UserDao;
import az.javafx.dao.impl.UserDaoImpl;
import az.javafx.model.Credential;
import az.javafx.service.LoginService;
import az.javafx.service.impl.LoginServiceImpl;
import az.javafx.util.ControllerUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginController {

    UserDao userDAO = new UserDaoImpl();
    LoginService loginService = new LoginServiceImpl(userDAO);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registrationBtn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    void initialize() {
        registrationBtn.setOnAction(event -> {
            registrationBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("registration.fxml"));
        });
        loginBtn.setOnAction(event -> {

        });

        loginBtn.setOnAction(event -> {
            if (usernameField.getText() != null && passwordField.getText() != null) {
            boolean login = loginService.login(new Credential(usernameField.getText(),passwordField.getText()));
            if (login==true){
                loginBtn.getScene().getWindow().hide();
                ControllerUtil.openNewScene(getClass().getResource("main.fxml"));
            }else{
                System.out.println("username or password is wrong");}
            }else{
                System.out.println("username or password is empty");}
        });
    }

}

