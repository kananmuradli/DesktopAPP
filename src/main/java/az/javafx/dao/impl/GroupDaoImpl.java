package az.javafx.dao.impl;

import az.javafx.config.DBConfig;
import az.javafx.dao.GroupDao;
import az.javafx.model.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    @Override
    public boolean saveGroup(Group group) {
            boolean isAdded = false;

            Connection c = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            String insertIntoGroupContactInfo = "INSERT INTO group_contact_info  VALUES(?,?)";

            String getLastGroupInfoId = "SELECT MAX(id) id FROM group_contact_info";

            String insertIntoGroup = "INSERT INTO group(group_name,contact_info_id) VALUES (?,?)";

            c = DBConfig.getConnection();
            if (c != null) {

                try {
                    ps = c.prepareStatement(insertIntoGroupContactInfo);
                    ps.execute();

                    ps = c.prepareStatement(getLastGroupInfoId);
                    rs = ps.executeQuery();
                    long lastGroupInfoId = 0;
                    if (rs.next()){
                        lastGroupInfoId = rs.getLong("id");
                    }

                    ps = c.prepareStatement(insertIntoGroup);
                    ps.setString(1, group.getGroupName());
                    ps.setLong(2, lastGroupInfoId);
                    ps.execute();
                    isAdded = true;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());

                } finally {
                    try {
                        ps.close();
                        c.close();

                    } catch (Exception e) {

                    }

                }

            }
            return isAdded;
        }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT \n" +
                "    g.id id,\n" +
                "    g.group_name name,\n" +
                "FROM\n" +
                "    studentmanagmentapp03.group s\n" +
                "        LEFT JOIN\n" +
                "    group_contact_info gci ON g.contact_info_id = gci.id WHERE g.active = 1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getLong("id"));
                    group.setGroupName(rs.getString("name"));
                    groups.add(group);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return groups;
    }

    @Override
    public boolean softDeleteGroup(Long id) {
        return false;
    }

    @Override
    public boolean hardDeleteGroup(Long id) {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM group WHERE id = ?";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, id);
                ps.execute();
                isDeleted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {

            }
        }
        return isDeleted;
    }

    @Override
    public Group getGroupById(Long id) {
        Group group = new Group();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT \n" +
                "    g.id id,\n" +
                "    g.group_name name,\n"+
                "FROM\n" +
                "    studentmanagmentapp03.group s\n" +
                "        LEFT JOIN\n" +
                "    group_contact_info gci ON g.contact_info_id = gci.id WHERE g.active = 1 and g.id = " + id;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    group.setId(rs.getLong("id"));
                    group.setGroupName(rs.getString("name"));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return group;
    }

    @Override
    public boolean updateGroupById(Group group) {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String updateGroup = "UPDATE group SET group_name = ? WHERE id = ?";

        String selectContactInfoIdById = "SELECT contact_info_id from group WHERE id ="+group.getId();

        String updateGroupContactInfo = "UPDATE group_contact_info SET  WHERE id = ?";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(updateGroup);
                ps.setString(1, group.getGroupName());
                ps.setLong(2, group.getId());
                ps.execute();

                ps = c.prepareStatement(selectContactInfoIdById);
                rs = ps.executeQuery();
                long contactInfoId = 0;
                if (rs.next()){
                    contactInfoId = rs.getLong("contact_info_id");
                }

                ps = c.prepareStatement(updateGroupContactInfo);
                ps.setLong(1, contactInfoId);
                ps.execute();
                isUpdated = true;

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } finally {
                try {
                    ps.close();
                    c.close();

                } catch (Exception e) {

                }

            }

        }
        return isUpdated;
    }
}
