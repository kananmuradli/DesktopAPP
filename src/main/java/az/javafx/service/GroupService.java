package az.javafx.service;

import az.javafx.model.Group;
import java.util.List;

public interface GroupService {

    boolean addGroup(Group group);

    List<Group> getAllGroups();

    Group getGroupById(Long id);

    boolean updateGroupById(Group group);

    boolean deleteGroup(Long id);
}
