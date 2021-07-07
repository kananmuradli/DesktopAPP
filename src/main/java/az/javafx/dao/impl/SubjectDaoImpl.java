package az.javafx.dao.impl;

import az.javafx.config.DBConfig;
import az.javafx.dao.SubjectDao;
import az.javafx.model.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    @Override
    public boolean saveSubject(Subject subject) {
        boolean isAdded = false;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String insertIntoSubjectContactInfo = "INSERT INTO subject_contact_info VALUES(?,?)";

        String getLastSubjectInfoId = "SELECT MAX(id) id FROM subject_contact_info";

        String insertIntoSubject = "INSERT INTO subject(subject_name,contact_info_id) VALUES (?,?)";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(insertIntoSubjectContactInfo);
                ps.execute();

                ps = c.prepareStatement(getLastSubjectInfoId);
                rs = ps.executeQuery();
                long lastSubjectInfoId = 0;
                if (rs.next()){
                    lastSubjectInfoId = rs.getLong("id");
                }

                ps = c.prepareStatement(insertIntoSubject);
                ps.setString(1, subject.getSubjectName());
                ps.setLong(2, lastSubjectInfoId);
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
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT \n" +
                "    sj.id id,\n" +
                "    sj.subject_name name,\n" +
                "FROM\n" +
                "    studentmanagmentapp03.subject s\n" +
                "        LEFT JOIN\n" +
                "    subject_contact_info sjci ON sj.contact_info_id = sjci.id WHERE sj.active = 1";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getString("name"));
                    subjects.add(subject);
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
        return subjects;
    }

    @Override
    public boolean softDeleteSubject(Long id) {
        return false;
    }

    @Override
    public boolean hardDeleteSubject(Long id) {
        boolean isDeleted = false;

        Connection c = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM subject WHERE id = ?";
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
    public Subject getSubjectById(Long id) {
        Subject subject = new Subject();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT \n" +
                "    sj.id id,\n" +
                "    sj.subject_name name,\n"+
                "FROM\n" +
                "    studentmanagmentapp03.subject s\n" +
                "        LEFT JOIN\n" +
                "    subject_contact_info sjci ON sj.contact_info_id = sjci.id WHERE sj.active = 1 and sj.id = " + id;
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    subject.setId(rs.getLong("id"));
                    subject.setSubjectName(rs.getString("name"));
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
        return subject;
    }

    @Override
    public boolean updateSubjectById(Subject subject) {
        boolean isUpdated = false;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String updateSubject = "UPDATE subject SET subject_name = ? WHERE id = ?";

        String selectContactInfoIdById = "SELECT contact_info_id from subject WHERE id ="+subject.getId();

        String updateSubjectContactInfo = "UPDATE subject_contact_info SET  WHERE id = ?";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(updateSubject);
                ps.setString(1, subject.getSubjectName());
                ps.setLong(2, subject.getId());
                ps.execute();

                ps = c.prepareStatement(selectContactInfoIdById);
                rs = ps.executeQuery();
                long contactInfoId = 0;
                if (rs.next()){
                    contactInfoId = rs.getLong("contact_info_id");
                }

                ps = c.prepareStatement(updateSubjectContactInfo);
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
