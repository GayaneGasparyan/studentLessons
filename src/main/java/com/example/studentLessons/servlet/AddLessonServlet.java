package com.example.studentLessons.servlet;


import com.example.studentLessons.manager.LessonManager;
import com.example.studentLessons.model.Lesson;
import com.example.studentLessons.model.Student;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/addLesson")


public class AddLessonServlet extends HttpServlet {


    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        Lesson lesson = Lesson.builder()
                .name(name)
                .description(description)
                .student(student)
                .build();

        lessonManager.addLesson(lesson);
        req.getSession().setAttribute("msg", "lesson was added");
        resp.sendRedirect("/addLesson");

    }
}
