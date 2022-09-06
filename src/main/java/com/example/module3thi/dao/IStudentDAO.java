package com.example.module3thi.dao;

import com.example.module3thi.model.Student;

import java.util.List;

public interface IStudentDAO<Student> {
    List<Student> findAll();
    void insertStudent(Student student);

    Student selectStudentById(int id);


    boolean deleteStudent(int id);

    boolean updateStudent(Student student);

    public List<Student>selectStudentByName(String name);
}
