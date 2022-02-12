<%-- 
   Document   : repondrequestions
   Created on : 1 jan. 2022, 00:12:33
   Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<style type="text/css">
 		<%@include file="../../css/questionaire_style.css" %>  
</style> <!-- @include =  directive de compilation (jsp pure) -->
<h1>Repondez à ces questions:</h1>
<h3 class="success">${messageSuccess}</h3>
<h3 class="erreur">${messageError}</h3>

<div class="questionLayout">
    <c:forEach items="${pendingQuestions}" var="question">
        <form method="post" action="${pageContext.request.contextPath}/reponses/envoyer">
            <div class="form-question">
                <div class="intituleQuestion" id="intituleQuestion">
                    <b>${question.libelle}</b>
                    <p> auteur: ${question.auteur.nom}<p>
                </div>
                <c:if test="${question.propositions.size() gt 1}">
                    <br>
                </c:if>
                <c:forEach items="${question.propositions}" var="proposition">
                    <div>
                        <label for="01">${proposition.libelle}</label>
                        <input id="01" type="radio" name="reponse" value="${proposition.libelle}">
                    </div>
                </c:forEach>
                <c:if test="${question.propositions.size() lt 1}">
                    <br>
                    <p><i> Inscrivez votre réponse: </i></p>
                    <textarea name="reponse" placeholder="..."></textarea>
                    <br>
                </c:if>
                 <button type="submit" name="question" value="${question.idQuestion}" >Valider</button>
            </div>  
        </form>
    </c:forEach>
    <br>
</div>