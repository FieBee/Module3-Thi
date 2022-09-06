package com.example.module3thi.dao;

import com.example.module3thi.connection.ConnectionJDBC;
import com.example.module3thi.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudenDAO implements IStudentDAO<Student>{

    private Connection connection = ConnectionJDBC.getConnect();
    private static final String INSERT_STUDENT = "INSERT INTO student VALUE (?,?,?,?,?,?,?);";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id =?";
    private static final String SELECT_STUDENT_BY_NAME = "SELECT * FROM student WHERE name LIKE ?;";
    private static final String SELECT_ALL_STUDENT = "SELECT * FROM student ";
    private static final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?;";
    private static final String UPDATE_STUDENT = "UPDATE student SET name = ?,date_of_birth= ?, address =?, phone_number =?, email =?, class_id =? where id = ?;";

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT);){
                System.out.println(statement);

                ResultSet resultSet =statement.executeQuery();
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String date_of_birth = resultSet.getString("date_of_birth");
                    String address = resultSet.getString("address");
                    String phone_number = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    int class_id = resultSet.getInt("class_id");
                    students.add(new Student(id,name,date_of_birth,address,phone_number,email,class_id));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    @Override
    public void insertStudent(Student student) {
        System.out.println(INSERT_STUDENT);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getDateOfBirth());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setString(5,student.getPhoneNumber());
            preparedStatement.setString(6,student.getEmail());
            preparedStatement.setInt(7,student.getClassRoom());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student selectStudentById(int id) {
        Student student = null;
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String date_of_birth = rs.getString("date_of_birth");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                int class_id = rs.getInt("class_id");
                student = new Student(id,name,date_of_birth,address,phone_number,email,class_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> selectStudentByName(String supperName) {
        List<Student> students = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_NAME);) {
            preparedStatement.setString(1, "%"+supperName+"%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name= rs.getString("name");
                String date_of_birth = rs.getString("date_of_birth");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                int class_id = rs.getInt("class_id");
                students.add(new Student(id,name,date_of_birth,address,phone_number,email,class_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean deleteStudent(int id) {
        boolean rowDeleted;
        try(
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);){
            statement.setInt(1,id);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean rowUpdated;
        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getDateOfBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getEmail());
            statement.setInt(6, student.getClassRoom());
            statement.setInt(7, student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

}
