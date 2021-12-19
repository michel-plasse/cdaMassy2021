

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
<h1>Question</h1>
<H3>Bienvenu</H3>
<h4>Répondre aux questions</h4>
<form>
    <div class="questionLayout">
        <ol>
            <c:forEach items="${questions}" var="question">
                <div class="form-question">
                    <li> <b>${question.libelle}</b></li>
                    <br> autorId:${question.idCreateur}
                    <c:if test="${question.propositions.size() gt 1}">
                        <p><i> Choisissez une réponses: </i></p>
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
        </ol>
    </div>
</form>

