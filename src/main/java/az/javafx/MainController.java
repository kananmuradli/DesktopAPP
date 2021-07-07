package az.javafx;

import java.net.URL;
import java.util.ResourceBundle;

import az.javafx.util.ControllerUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    protected Button homeBtn;

    @FXML
    protected Button teacherOppBtn;

    @FXML
    protected Button groupOppBtn;

    @FXML
    protected Button logOutBtn;

    @FXML
    protected Button studentOppBtn;

    @FXML
    protected Button subjectOppBtn;


    @FXML
    void initialize() {

        studentOppBtn.setOnAction(event -> {
            studentOppBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("studentPage.fxml"));

        });

        logOutBtn.setOnAction(event -> {
            logOutBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("login.fxml"));

        });

        teacherOppBtn.setOnAction(event -> {
            teacherOppBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("teacherPage.fxml"));

        });

        groupOppBtn.setOnAction(event -> {
            groupOppBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("groupPage.fxml"));
        });

        subjectOppBtn.setOnAction(event -> {
            subjectOppBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("subjectPage.fxml"));
        });
    }
}


