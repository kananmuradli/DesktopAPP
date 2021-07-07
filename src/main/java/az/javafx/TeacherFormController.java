package az.javafx;

import az.javafx.dao.TeacherDao;
import az.javafx.dao.impl.TeacherDaoImpl;
import az.javafx.exceptions.*;
import az.javafx.model.Teacher;
import az.javafx.service.TeacherService;
import az.javafx.service.impl.TeacherServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TeacherFormController extends GeneralController{

    TeacherDao teacherDao = new TeacherDaoImpl();
    TeacherService teacherService = new TeacherServiceImpl(teacherDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField seriaNumField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dobField;

    @FXML
    private Button ignoreBtn;

    @FXML
    private Label successMessage;

    @FXML
    private Label successMessage1;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton maleRadio;

    @FXML
    void initialize() {
        saveBtn.setOnAction(event -> {
            if (operation1.equals("add")) {
                setTeacher(operation1);
            } else if (operation1.equals("update")) {
                setTeacher(operation1);
            }

        });
        printSelectedTeacher();

        ignoreBtn.setOnAction(event -> {
            ignoreBtn.getScene().getWindow().hide();
        });
    }


    private void printSelectedTeacher() {
        if (selectedTeacher != null) {
            firstNameField.setText(selectedTeacher.getName());
            surnameField.setText(selectedTeacher.getSurname());
            seriaNumField.setText(selectedTeacher.getSeriaNum());
            phoneField.setText(selectedTeacher.getPhone());
            emailField.setText(selectedTeacher.getEmail());
            dobField.setValue(LocalDate.parse(selectedTeacher.getDOB()));
            femaleRadio.setText(selectedTeacher.getGender());
            maleRadio.setText(selectedTeacher.getGender());

        }
    }

    private void setTeacher(String operation1) {
        Teacher teacher = new Teacher();
        boolean errorNotFound = true;
        String gender = "";
        try {
            if (femaleRadio.selectedProperty().getValue() && maleRadio.selectedProperty().getValue()) {

                throw new GenderNotValidException("GenderNotValidException");

            } else if (femaleRadio.selectedProperty().getValue()) {
                gender = "Q";

            } else if (maleRadio.selectedProperty().getValue()) {
                gender = "K";

            } else {
                throw new GenderNotValidException("GenderNotValidException");
            }
        } catch (Exception ex) {
            errorNotFound = false;
            successMessage1.setText("GenderNotValidException");
        }

        try {
            teacher.setName(firstNameField.getText());
            teacher.setSurname(surnameField.getText());
            teacher.setSeriaNum(seriaNumField.getText());
            teacher.setPhone(phoneField.getText());
            teacher.setEmail(emailField.getText());
            teacher.setDOB(String.valueOf(dobField.getValue()));
            teacher.setGender(gender);

        } catch (SeriaNumException ex) {
            errorNotFound = false;
            seriaNumField.clear();
            seriaNumField.setPromptText(ex.getMessage());
        } catch (NameException ex) {
            errorNotFound = false;
            firstNameField.clear();
            firstNameField.setPromptText(ex.getMessage());
        } catch (SurnameException ex) {
            errorNotFound = false;
            surnameField.clear();
            surnameField.setPromptText(ex.getMessage());
        } catch (DOBException ex) {
            errorNotFound = false;
            dobField.setPromptText(ex.getMessage());
        } catch (EmailException ex) {
            errorNotFound = false;
            emailField.clear();
            emailField.setPromptText(ex.getMessage());
        } catch (PhoneException ex) {
            errorNotFound = false;
            phoneField.clear();
            phoneField.setPromptText(ex.getMessage());
        }

        if (errorNotFound) {
            if (operation1.equals("add")) {
                teacherService.addTeacher(teacher);
                successMessage.setText("Müəllim uğurla əlavə edildi");
            } else if (operation1.equals("update")) {
                teacher.setId(selectedTeacher.getId());
                teacherService.updateTeacherById(teacher);
                successMessage.setText("Dəyişiklik uğurla yerinə yetirildi");
            }

            firstNameField.clear();
            surnameField.clear();
            seriaNumField.clear();
            phoneField.clear();
            emailField.clear();
            dobField.getEditor().clear();
        }
    }
}
