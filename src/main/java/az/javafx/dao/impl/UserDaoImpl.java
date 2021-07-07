package az.javafx.dao.impl;

import az.javafx.config.DBConfig;
import az.javafx.dao.UserDao;
import az.javafx.model.Credential;
import az.javafx.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO user(username,surname,firstname,gender,password) VALUES (?,?,?,?,?)";

        c = DBConfig.getConnection();
        if (c != null) {

            try {
                ps = c.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getFirstname());
                ps.setString(4, "none");
                ps.setString(5, user.getPassword());
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

    public Credential getUserByUsername(String username) {
        Credential credential = new Credential();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT username,password FROM user WHERE active = 1 AND username=" + "\""+username+"\"";
        try {
            c = DBConfig.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    credential.setUsername(rs.getString("username"));
                    credential.setPassword(rs.getString("password"));
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
        return credential;

    }
}

