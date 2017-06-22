
<%@ page import="com.oleginno.webapp.model.ContactType" %>
<%@ page import="com.oleginno.webapp.web.HtmlUtil" %>
<%@ page import="com.oleginno.webapp.storage.XmlFileStorage" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.oleginno.webapp.model.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 23.05.17
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Список резюме</title>
</head>
<body>

</body>
<section>
    <table>
        <tr>
            <td colspan="5" style="text-align: right"><a
                    href="resume?action=create"><img src="img/add.png">Добавить Резюме</a></td>
        </tr>
        <tr>
            <td>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>Имя</th>
                        <th>Проживание</th>
                        <th>Email</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    <%
                        XmlFileStorage storage = new XmlFileStorage("/Users/oleg/Documents/webapp5/file_storage/");
                        Collection<Resume> resumes = storage.getAllSorted();
                        request.setAttribute("resumeList", resumes);
                    %>

                    <c:forEach items="${resumeList}" var="resume">
                        <jsp:useBean id="resume" type="com.oleginno.webapp.model.Resume"/>
                        <tr>
                            <td><a href="resume?uuid=${resume.uuid}&action=view2">${resume.fullName}</a></td>
                            <td><%=HtmlUtil.mask(resume.getLocation())%></td>
                            <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                        </tr>
                    </c:forEach>
                    <%--<% for(Resume r: resumes) {--%>
                        <%--request.setAttribute("r", r);--%>
                    <%--%>--%>
                    <%--<tr>--%>
                        <%--<td><a href="resume?uuid=${r.uuid}&action=view">${r.fullName}</a></td>--%>
                        <%--<td>${r.location}</td>--%>
                        <%--<td><%=HtmlUtil.getContact(r, ContactType.MAIL)%></td>--%>
                        <%--<td><a href="resume?uuid=${r.uuid}&action=delete"><img src="img/delete.png"></a></td>--%>
                        <%--<td><a href="resume?uuid=${r.uuid}&action=edit"><img src="img/pencil.png"></a></td>--%>
                    <%--</tr>--%>
                    <%--<%}%>--%>
                </table>
            </td>
        </tr>
    </table>
</section>
</html>
