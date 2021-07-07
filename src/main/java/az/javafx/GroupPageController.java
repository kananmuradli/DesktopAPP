package az.javafx;

import az.javafx.dao.GroupDao;
import az.javafx.dao.impl.GroupDaoImpl;
import az.javafx.model.Group;
import az.javafx.service.GroupService;
import az.javafx.service.impl.GroupServiceImpl;
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

public class GroupPageController extends GeneralController{

    GroupDao groupDao = new GroupDaoImpl();
    GroupService groupService = new GroupServiceImpl(groupDao);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> groupTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> groupNameCol;

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
        printGroups();

        addBtn.setOnAction(event -> {
            operation2 = addBtn.getText().toLowerCase();
            openGroupAddForm();
        });

        refreshBtn.setOnAction(event -> {
            printGroups();
        });

        updateBtn.setOnAction(event -> {
            operation2 = updateBtn.getText().toLowerCase();
            openGroupUpdateForm();
        });

        deleteBtn.setOnAction(event -> {
            deleteGroupById();
            printGroups();

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
    private void deleteGroupById() {
        Long groupId = groupTable.getSelectionModel().getSelectedItem().getId();
        groupService.deleteGroup(groupId);
    }

    private void openGroupAddForm() {
        selectedGroup = null;
        ControllerUtil.openNewScene(getClass().getResource("groupForm.fxml"));
    }

    private void openGroupUpdateForm() {
        try {
            Long groupId = groupTable.getSelectionModel().getSelectedItem().getId();
            Group group = groupService.getGroupById(groupId);
            selectedGroup = group;
            errrorMessage.setText("");
            ControllerUtil.openNewScene(getClass().getResource("groupForm.fxml"));
        }catch (NullPointerException ex){
            errrorMessage.setText("Dəyişiklik etmək üçün istifadəçi seçilməyib !");
        }
    }

    private void printGroups() {
        List<Group> groupsFromDb = groupService.getAllGroups();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        groupTable.setItems(FXCollections.observableArrayList(groupsFromDb));
    }
    }
