package az.javafx;

import az.javafx.dao.GroupDao;
import az.javafx.dao.impl.GroupDaoImpl;
import az.javafx.exceptions.*;
import az.javafx.model.Group;
import az.javafx.service.GroupService;
import az.javafx.service.impl.GroupServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GroupFormController extends GeneralController {

    GroupDao groupDao = new GroupDaoImpl();
    GroupService groupService = new GroupServiceImpl(groupDao);

    @FXML
    private TextField groupNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button ignoreBtn;

    @FXML
    private Label successMessage;

    @FXML
    void initialize() {
        saveBtn.setOnAction(event -> {
            if (operation2.equals("add")) {
                setGroup(operation2);
            } else if (operation2.equals("update")) {
                setGroup(operation2);
            }

        });
        printSelectedGroup();

        ignoreBtn.setOnAction(event -> {
            ignoreBtn.getScene().getWindow().hide();
        });
    }

    private void printSelectedGroup() {
        if (selectedGroup != null) {
            groupNameField.setText(selectedGroup.getGroupName());
        }
    }

    private void setGroup(String operation2) {
        Group group = new Group();
        boolean errorNotFound = false;
        try {
            group.setGroupName(groupNameField.getText());

        } catch (NameException ex) {
            errorNotFound = false;
            groupNameField.clear();
            groupNameField.setPromptText(ex.getMessage());
        }

            if (errorNotFound) {
                if (operation2.equals("add")) {
                    groupService.addGroup(group);
                    successMessage.setText("Group uğurla əlavə edildi");
                } else if (operation2.equals("update")) {
                    group.setId(selectedGroup.getId());
                    groupService.updateGroupById(group);
                    successMessage.setText("Dəyişiklik uğurla yerinə yetirildi");
                }

                groupNameField.clear();

            }
        }
    }
