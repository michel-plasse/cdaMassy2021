<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- @page = directive de compilation (jsp pure) -->
<p:header title="CDA Massy 2021"/>

    <style type="text/css">
  		<%@include file="../../css/questionaire_style.css" %>  
	</style> <!-- @include =  directive de compilation (jsp pure) -->
<h1>affichage questionnaire:</h1>
<form>
	<div class="questionnaireLayout">
		<c:forEach items="${allQuestionnaires}" var="questionnaire">
			 <h2>${questionnaire.libelle}</h2>
			 <div class="questionLayout">
            <c:forEach items="${questionnaire.allQuestions}" var="question"> <!-- c:foreach = directive de compilation (lib core de jstl) -->
            
                <div class="form-question">
                    <div class="intituleQuestion" id="intituleQuestion">
                            <b>${question.libelle}</b>
                            <p> auteur: ${question.auteur.nom}<p>
                    </div>
                    <!-- si la question a afficher a au moins une proposition de reponse: l afficher-->
                    <c:if test="${question.propositions.size() gt 1}">
                        <br>
                    </c:if>
                    <c:forEach items="${question.getPropositions()}" var="proposition">
                        <div>
                            <input id="01" type="radio" name="r" value="1" checked>
                            <label for="01">${proposition.libelle}</label>
                        </div>
                    </c:forEach>
                    <!-- si une question n a pas de poropostition de reponse: afficher un champs reponse (reponse libre);-->
                    <c:if test="${question.propositions.size() lt 1}">
                        <br>
                        <p><i> Inscrivez votre réponse: </i></p>
                        <textarea type="text" size="80px"  placeholder="..."></textarea>
                        <br>
                    </c:if>
                </div>
            </c:forEach>
            <br>
    </div>
		</c:forEach> 
    
</form>

