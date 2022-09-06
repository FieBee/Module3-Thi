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
                break;
            case "sort":
                break;
            case "find":
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
            dispatcher = request.getRequestDispatcher("student/error-404.jsp");
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
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "sort":
                break;
            case "find":
                break;
            default:
                break;
        }
    }
}
