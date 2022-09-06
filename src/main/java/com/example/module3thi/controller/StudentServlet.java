package com.example.module3thi.controller;

import com.example.module3thi.dao.IStudentDAO;
import com.example.module3thi.dao.StudenDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet (name = "StudentServlet", value = "/students")
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
        try{
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void listUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser  = userDAO.selectAllUsers();
        request.setAttribute("listUser",listUser );
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try{
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
