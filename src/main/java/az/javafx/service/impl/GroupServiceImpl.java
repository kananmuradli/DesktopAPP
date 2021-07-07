package az.javafx.service.impl;

import az.javafx.dao.GroupDao;
import az.javafx.model.Group;
import az.javafx.service.GroupService;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public boolean addGroup(Group group) {
        return groupDao.saveGroup(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public boolean updateGroupById(Group group) {
        return groupDao.updateGroupById(group);
    }

    @Override
    public boolean deleteGroup(Long id) {
        return groupDao.hardDeleteGroup(id);
    }
}
