package com.example.studentLessons.manager;

import com.example.studentLessons.db.DBConnectionProvider;
import com.example.studentLessons.model.Lesson;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {


    private Connection connection = DBConnectionProvider.getProvider().getConnection();
    private StudentManager studentManager = new StudentManager();

    public void addLesson(Lesson lesson) {
        try {
            String query = "INSERT INTO `lesson` (`name`,`description`,`student_id`) " +
                    "VALUES(?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, lesson.getName());
            pStatement.setString(2, lesson.getDescription());
            pStatement.setInt(3, lesson.getStudent().getId());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateLesson(Lesson lesson) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE lesson SET name = '%s', description = '%s' WHERE id=" + lesson.getId(),
                    lesson.getName(), lesson.getDescription());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Lesson> getLessons() {
        String sql = "SELECT * from lesson";
        List<Lesson> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Lesson lesson = Lesson.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .student(studentManager.getStudentById(resultSet.getInt(4)))
                        .build();
                result.add(lesson);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM book WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .student(studentManager.getStudentById(resultSet.getInt(4)))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteLesson(int id) {
        String sql = "DELETE from lesson where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
