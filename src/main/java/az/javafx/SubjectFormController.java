package az.javafx;

import az.javafx.dao.SubjectDao;
import az.javafx.dao.impl.SubjectDaoImpl;
import az.javafx.exceptions.NameException;
import az.javafx.model.Subject;
import az.javafx.service.SubjectService;
import az.javafx.service.impl.SubjectServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SubjectFormController extends GeneralController{

    SubjectDao subjectDao = new SubjectDaoImpl();
    SubjectService subjectService = new SubjectServiceImpl(subjectDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField subjectNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button ignoreBtn;

    @FXML
    private Label successMessage;

    @FXML
    void initialize() {
        saveBtn.setOnAction(event -> {
            if (operation3.equals("add")) {
                setSubject(operation3);
            } else if (operation3.equals("update")) {
                setSubject(operation3);
            }

        });
        printSelectedSubject();

        ignoreBtn.setOnAction(event -> {
            ignoreBtn.getScene().getWindow().hide();
        });
    }

    private void printSelectedSubject() {
        if (selectedSubject != null) {
            subjectNameField.setText(selectedSubject.getSubjectName());
        }
    }

    private void setSubject(String operation3) {
        Subject subject = new Subject();
        boolean errorNotFound = false;
        try {
            subject.setSubjectName(subjectNameField.getText());

        } catch (NameException ex) {
            errorNotFound = false;
            subjectNameField.clear();
            subjectNameField.setPromptText(ex.getMessage());
        }

        if (errorNotFound) {
            if (operation3.equals("add")) {
                subjectService.addSubject(subject);
                successMessage.setText("Subject uğurla əlavə edildi");
            } else if (operation3.equals("update")) {
                subject.setId(selectedSubject.getId());
                subjectService.updateSubjectById(subject);
                successMessage.setText("Dəyişiklik uğurla yerinə yetirildi");
            }

            subjectNameField.clear();

        }
    }
}