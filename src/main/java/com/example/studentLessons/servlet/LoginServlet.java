package com.example.studentLessons.servlet;


import com.example.studentLessons.manager.StudentManager;
import com.example.studentLessons.model.Student;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    StudentManager studentManager=new StudentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Student student = studentManager.getUserByEmailAndPassword(email, password);
        if(student == null){
            req.getSession().setAttribute("msg", "Wrong username or password");
            resp.sendRedirect("/");
        } else {
            req.getSession().setAttribute("student", student);
            resp.sendRedirect("/home");
        }

    }
}
