package az.javafx;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import az.javafx.dao.StudentDao;
import az.javafx.dao.impl.StudentDaoImpl;
import az.javafx.exceptions.*;
import az.javafx.model.Student;
import az.javafx.service.StudentService;
import az.javafx.service.impl.StudentServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentFormController extends GeneralController {

    StudentDao studentDAO = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl(studentDAO);

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
            if (operation.equals("add")) {
                setStudent(operation);
            } else if (operation.equals("update")) {
                setStudent(operation);
            }

        });
        printSelectedStudent();

        ignoreBtn.setOnAction(event -> {
            ignoreBtn.getScene().getWindow().hide();
        });
    }


    private void printSelectedStudent() {
        if (selectedStudent != null) {
            firstNameField.setText(selectedStudent.getName());
            surnameField.setText(selectedStudent.getSurname());
            seriaNumField.setText(selectedStudent.getSeriaNum());
            phoneField.setText(selectedStudent.getPhone());
            emailField.setText(selectedStudent.getEmail());
            dobField.setValue(LocalDate.parse(selectedStudent.getDOB()));
            femaleRadio.setText(selectedStudent.getGender());
            maleRadio.setText(selectedStudent.getGender());

        }
    }

    private void setStudent(String operation) {
        Student student = new Student();
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
            student.setName(firstNameField.getText());
            student.setSurname(surnameField.getText());
            student.setSeriaNum(seriaNumField.getText());
            student.setPhone(phoneField.getText());
            student.setEmail(emailField.getText());
            student.setDOB(String.valueOf(dobField.getValue()));
            student.setGender(gender);

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
                if (operation.equals("add")) {
                    studentService.addStudent(student);
                    successMessage.setText("Tələbə uğurla əlavə edildi");
                } else if (operation.equals("update")) {
                    student.setId(selectedStudent.getId());
                    studentService.updateStudentById(student);
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

