package com.regsys.dao;

import com.regsys.model.User;
import com.regsys.util.DBConnection;
import com.regsys.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    /** Returns true if the insert succeeded, false if the user_id is already taken. */
    public boolean registerUser(User user, String rawPassword) throws SQLException {
        if (userIdExists(user.getUserId())) {
            return false;
        }
        String sql = "INSERT INTO users (name, enroll_id, batch, dob, official_email, address, mobile_no, user_id, password_hash) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEnrollId());
            ps.setString(3, user.getBatch());
            ps.setString(4, user.getDob());
            ps.setString(5, user.getOfficialEmail());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getMobileNo());
            ps.setString(8, user.getUserId());
            ps.setString(9, PasswordUtil.hash(rawPassword));
            ps.executeUpdate();
        }
        return true;
    }

    private boolean userIdExists(String userId) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /** Returns the matching User if the userId/password combination is correct, otherwise null. */
    public User authenticate(String userId, String rawPassword) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    if (storedHash.equals(PasswordUtil.hash(rawPassword))) {
                        return mapRow(rs);
                    }
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEnrollId(rs.getString("enroll_id"));
        u.setBatch(rs.getString("batch"));
        u.setDob(rs.getString("dob"));
        u.setOfficialEmail(rs.getString("official_email"));
        u.setAddress(rs.getString("address"));
        u.setMobileNo(rs.getString("mobile_no"));
        u.setUserId(rs.getString("user_id"));
        // password_hash intentionally not mapped for display purposes
        return u;
    }
}
