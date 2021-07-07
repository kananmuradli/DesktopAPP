package az.javafx.dao;

import az.javafx.model.Group;

import java.util.List;

public interface GroupDao {

    boolean saveGroup(Group group);

    List<Group> getAllGroups();

    boolean softDeleteGroup(Long id);

    boolean hardDeleteGroup(Long id);

    Group getGroupById(Long id);

    boolean updateGroupById(Group group);
}
