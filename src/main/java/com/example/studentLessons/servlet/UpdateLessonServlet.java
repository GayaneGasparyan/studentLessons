package com.example.studentLessons.servlet;


import com.example.studentLessons.manager.LessonManager;
import com.example.studentLessons.model.Lesson;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/updateLesson")

public class UpdateLessonServlet extends HttpServlet {



    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        Lesson lesson = lessonManager.getLessonById(lessonId);
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("/WEB-INF/updateLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        Lesson lessonById = lessonManager.getLessonById(lessonId);
        if(lessonById != null) {

            String name = req.getParameter("name");
            String description = req.getParameter("description");


           Lesson lesson= Lesson.builder()
                    .id(lessonId)
                    .name(name)
                   .description(description)
                    .build();
            lessonManager.updateLesson(lesson);
            req.getSession().setAttribute("msg", "lesson was updated");
            resp.sendRedirect("/home");
        }

    }
}
