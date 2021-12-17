<%-- 
    Document   : questions
    Created on : 17 déc. 2021, 02:52:58
    Author     : thoma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<h1>Question</h1>
<ol>
  <c:forEach items="${questions}" var="question">
    <li>${question.idCreateur} ${question.libelle}</li>
  </c:forEach>
</ol>