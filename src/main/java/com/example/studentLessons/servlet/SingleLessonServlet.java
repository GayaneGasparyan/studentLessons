package com.example.studentLessons.servlet;



import com.example.studentLessons.manager.LessonManager;
import com.example.studentLessons.model.Lesson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/singleLesson")
public class SingleLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Lesson lesson = lessonManager.getLessonById(id);

        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/WEB-INF/singleLesson.jsp").forward(req, resp);
    }
}

