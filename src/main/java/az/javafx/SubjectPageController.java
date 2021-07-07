package az.javafx;

import az.javafx.dao.SubjectDao;
import az.javafx.dao.impl.SubjectDaoImpl;
import az.javafx.model.Subject;
import az.javafx.service.SubjectService;
import az.javafx.service.impl.SubjectServiceImpl;
import az.javafx.util.ControllerUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SubjectPageController extends GeneralController{

    SubjectDao subjectDao = new SubjectDaoImpl();
    SubjectService subjectService = new SubjectServiceImpl(subjectDao);

    @FXML
    private TableView<?> subjectTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> subjectNameCol;

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
        printSubjects();

        addBtn.setOnAction(event -> {
            operation3 = addBtn.getText().toLowerCase();
            openSubjectAddForm();
        });

        refreshBtn.setOnAction(event -> {
            printSubjects();
        });

        updateBtn.setOnAction(event -> {
            operation3 = updateBtn.getText().toLowerCase();
            openSubjectUpdateForm();
        });

        deleteBtn.setOnAction(event -> {
            deleteSubjectById();
            printSubjects();

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
    private void deleteSubjectById() {
        Long subjectId = subjectTable.getSelectionModel().getSelectedItem().getId();
        subjectService.deleteSubject(subjectId);
    }

    private void openSubjectAddForm() {
        selectedSubject = null;
        ControllerUtil.openNewScene(getClass().getResource("subjectForm.fxml"));
    }

    private void openSubjectUpdateForm() {
        try {
            Long subjectId = subjectTable.getSelectionModel().getSelectedItem().getId();
            Subject subject = subjectService.getSubjectById(subjectId);
            selectedSubject = subject;
            errrorMessage.setText("");
            ControllerUtil.openNewScene(getClass().getResource("subjectForm.fxml"));
        }catch (NullPointerException ex){
            errrorMessage.setText("Dəyişiklik etmək üçün istifadəçi seçilməyib !");
        }
    }

    private void printSubjects() {
        List<Subject> subjectsFromDb = subjectService.getAllSubjects();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        subjectNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        subjectTable.setItems(FXCollections.observableArrayList(subjectsFromDb));
    }
    }
