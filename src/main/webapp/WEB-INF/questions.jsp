

<%-- 
   Document   : questions
   Created on : 17 déc. 2021, 02:52:58
   Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
<h1>Questionnaire</h1>
<h3>Répondre aux questions</h3>
<form>
    <div class="questionLayout">
            <c:forEach items="${questions}" var="question">
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
                            <input id="01" type="radio" name="r" value="1" checked>
                            <label for="01">${proposition.libelle}</label>
                        </div>
                    </c:forEach>
                    <c:if test="${question.propositions.size() lt 1}">
                        <br>
                        <p><i> Inscrivez votre réponse: </i></p>
                        <textarea type="text" size="80px"  placeholder="..."></textarea>
                        <br>
                    </c:if>
                </div>
            </c:forEach>
            <button class="btn">Envoyer</button>
            <br>
    </div>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
</form>

