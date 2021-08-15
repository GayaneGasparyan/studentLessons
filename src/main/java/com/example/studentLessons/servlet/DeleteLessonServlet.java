package com.example.studentLessons.servlet;



import com.example.studentLessons.manager.LessonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteBook")
public class DeleteLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        lessonManager.deleteLesson(lessonId);
        req.getSession().setAttribute("msg", "book was removed");
        resp.sendRedirect("/home");
    }

}

