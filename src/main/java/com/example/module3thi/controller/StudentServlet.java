package com.example.module3thi.controller;

import com.example.module3thi.dao.IStudentDAO;
import com.example.module3thi.dao.StudenDAO;
import com.example.module3thi.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet (name = "StudentServlet", value ="/students")
public class StudentServlet extends HttpServlet {

    private IStudentDAO studentDAO;
    public void init() {
        studentDAO = new StudenDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }
    }


    private void listStudent(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Student> students  = studentDAO.findAll();
        request.setAttribute("students",students );
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = (Student) studentDAO.selectStudentById(id);
        RequestDispatcher dispatcher;

        if (student == null){
            dispatcher = request.getRequestDispatcher("student/fail.jsp");
        }else {
            request.setAttribute("student",student);
            dispatcher = request.getRequestDispatcher("student/edit.jsp");
        }
        dispatcher.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createUser(request,response);
                break;
            case "edit":
                editUser(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }
    }
    private void createUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String dateOfBirth = new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8");
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phoneNumber = new String(request.getParameter("phoneNumber").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classRoom = Integer.parseInt(request.getParameter("classRoom"));
        Student newStudent = new Student(name, dateOfBirth, address,phoneNumber,email,classRoom);

        studentDAO.insertStudent(newStudent);
        request.setAttribute("message", "Thêm học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
    }

    private void editUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String dateOfBirth = new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8");
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phoneNumber = new String(request.getParameter("phoneNumber").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classRoom = Integer.parseInt(request.getParameter("classRoom"));
        Student newStudent = new Student(id,name, dateOfBirth, address,phoneNumber,email,classRoom);

        studentDAO.updateStudent(newStudent);

        request.setAttribute("message","Sửa thông tin học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);

        List<Student> students = studentDAO.findAll();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }

    private void  findByName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = new String(request.getParameter("search").getBytes("iso-8859-1"),"utf-8");

        List<Student> students = (List<Student>) studentDAO.selectStudentByName(name);

        request.setAttribute("students",students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
    }
}
