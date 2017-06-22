package com.oleginno.webapp.web;

import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import com.oleginno.webapp.model.SectionType;
import com.oleginno.webapp.storage.IStorage;
import com.oleginno.webapp.storage.XmlFileStorage;
import com.oleginno.webapp.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Oleh Savych
 * 21.05.17
 */

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    public static XmlFileStorage storage = new XmlFileStorage("/Users/oleg/Documents/webapp5/file_storage/");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        Resume r = Util.isEmpty(uuid) ? new Resume(name, location) : storage.load(uuid);

        r.setFullName(name);
        r.setLocation(location);
        r.setHomePage(request.getParameter("home_page"));

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.isEmpty()) {
                r.removeContact(type);
            } else {
                r.addContact(type, value);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (type.getHtmlType() == SectionHtmlType.ORGANIZATION) {
                continue;
            }
            if (value == null || value.isEmpty()) {
                r.getSections().remove(type);
            } else {
                r.addSection(type, type.getHtmlType().createSection(value));
            }
        }

        if (Util.isEmpty(uuid)) {
            storage.save(r);
        } else {
            storage.update(r);
        }

        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        Writer w = response.getWriter();
//        String name = request.getParameter("name");
//        w.write("Тест сервлет: -> " + name);
//        w.close();

        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        Resume r;

        //IStorage storage = WebAppConfig.get().getStorage();

        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("list");
                return;
            case "create":
                r = Resume.EMPTY;
                break;
            case "view2":
            case "edit":
                r = storage.load(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }

        request.setAttribute("resume", r);

        request
                .getRequestDispatcher(("view2".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp"))
                .forward(request, response);
    }
}