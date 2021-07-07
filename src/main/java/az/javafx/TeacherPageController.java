package az.javafx;

import az.javafx.dao.TeacherDao;
import az.javafx.dao.impl.TeacherDaoImpl;
import az.javafx.model.Teacher;
import az.javafx.service.TeacherService;
import az.javafx.service.impl.TeacherServiceImpl;
import az.javafx.util.ControllerUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherPageController extends GeneralController{

    TeacherDao teacherDao = new TeacherDaoImpl();
    TeacherService teacherService = new TeacherServiceImpl(teacherDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Teacher> teacherTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> surnameCol;

    @FXML
    private TableColumn<?, ?> ageCol;

    @FXML
    private TableColumn<?, ?> seriaNumCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> genderCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private Label errrorMessage;

    @FXML
    void initialize() {

        printTeachers();

        addBtn.setOnAction(event -> {
            operation1 = addBtn.getText().toLowerCase();
            openTeacherAddForm();
        });

        refreshBtn.setOnAction(event -> {
            printTeachers();
        });

        updateBtn.setOnAction(event -> {
            operation1 = updateBtn.getText().toLowerCase();
            openTeacherUpdateForm();
        });

        deleteBtn.setOnAction(event -> {
            deleteTeacherById();
            printTeachers();

        });

        homeBtn.setOnAction(event -> {
            homeBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("main.fxml"));

        });

        logOutBtn.setOnAction(event -> {
            logOutBtn.getScene().getWindow().hide();
            ControllerUtil.openNewScene(getClass().getResource("login.fxml"));

        });

    }

    private void deleteTeacherById() {
        Long teacherId = teacherTable.getSelectionModel().getSelectedItem().getId();
        teacherService.deleteTeacher(teacherId);
    }

    private void openTeacherAddForm() {
        selectedTeacher = null;
        ControllerUtil.openNewScene(getClass().getResource("teacherForm.fxml"));
    }

    private void openTeacherUpdateForm() {
        try {
            Long teacherId = teacherTable.getSelectionModel().getSelectedItem().getId();
            Teacher teacher = teacherService.getTeacherById(teacherId);
            selectedTeacher = teacher;
            errrorMessage.setText("");
            ControllerUtil.openNewScene(getClass().getResource("teacherForm.fxml"));
        }catch (NullPointerException ex){
            errrorMessage.setText("Dəyişiklik etmək üçün istifadəçi seçilməyib !");
        }
    }

    private void printTeachers() {
        List<Teacher> teachersFromDb = teacherService.getAllTeachers();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        seriaNumCol.setCellValueFactory(new PropertyValueFactory<>("seriaNum"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        teacherTable.setItems(FXCollections.observableArrayList(teachersFromDb));
    }
}
