package com.example.studentLessons.manager;

import com.example.studentLessons.db.DBConnectionProvider;
import com.example.studentLessons.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {


    private Connection connection = DBConnectionProvider.getProvider().getConnection();

    public void addStudent(Student student) {
        try {
            String query = "INSERT INTO `student` (`name`,`surname`,`email`,`password`) " +
                    "VALUES(?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, student.getName());
            pStatement.setString(2, student.getSurname());
            pStatement.setString(3, student.getEmail());
            pStatement.setString(4, student.getPassword());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                student.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE student SET name = '%s', surname = '%s', email='%s',password='%s' WHERE id=" + student.getId(),
                    student.getName(), student.getSurname(), student.getEmail(), student.getPassword());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        String sql = "SELECT * from user";
        List<Student> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
                result.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM user WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Student getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email='" + email + "' and password = '" + password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        String sql = "DELETE from student where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
