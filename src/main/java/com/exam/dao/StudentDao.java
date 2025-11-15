package main.java.com.exam.dao;

import main.java.com.exam.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private final String url = "jdbc:mysql://localhost:3306/examdb";
    private final String user = "root";
    private final String pass = "tdung@2209";

    public StudentDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // đảm bảo mysql-connector-j ở WEB-INF/lib
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(Student s) {
        String sql = "INSERT INTO students(id, name, email, score) VALUES(?,?,?,?)";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setDouble(4, s.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, email, score FROM students";
        try (Connection c = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setScore(rs.getDouble("score"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
