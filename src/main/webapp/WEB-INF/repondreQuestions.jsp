<%-- 
   Document   : repondreQuestions
   Created on : 1 jan. 2022, 00:12:33
   Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
<h1>affichage questions:</h1>

<div class="questionLayout">
    <c:forEach items="${pendingQuestions}" var="question">
        <form method="post" action="envoyerreponseservlet">
            <div class="form-question">
                <div class="intituleQuestion" id="intituleQuestion">
                    <b>${question.libelle}</b>
                    <p> auteur: ${question.nomAuteur}<p>
                </div>
                <c:if test="${question.propositions.size() gt 1}">
                    <br>
                </c:if>
                <c:forEach items="${question.getPropositions()}" var="proposition">
                    <div>
                        <input id="01" type="radio" name="reponse" value="1" checked>
                        <label for="01">${proposition.libelle}</label>
                    </div>
                </c:forEach>
                <c:if test="${question.propositions.size() lt 1}">
                    <br>
                    <p><i> Inscrivez votre réponse: </i></p>
                    <textarea name="reponse" type="text" size="80px"  placeholder="..."></textarea>
                    <br>
                </c:if>
            </div>
                <button type="submit" name="question" value="${question.id}" >Valider</button>
        </form>
    </c:forEach>
    <br>
</div>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />


