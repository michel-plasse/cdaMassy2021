<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- @page = directive de compilation (jsp pure) -->
<p:header title="CDA Massy 2021"/>

    <style type="text/css">
  		<%@include file="../../css/questionaire_style.css" %>  
	</style>
	
<h1>affichage questionnaires:</h1>

<form>
    <div class="questionLayout">
            <c:forEach items="${questionnaires}" var="questionnnaire"> 
                <div class="form-questionnnaire">
                    <div class="intituleQuestionnaire" id="intituleQuestionnaire">
                            <b>${questionnaire.libelle}</b>
                            <p> auteur: ${questionnaire.idCreateur.nom}<p>
                    </div>
                    <!-- si le questionnaire contient au moins une question => afficher-->
                    <c:if test="${questionnaire.question.size() gt 1}">
                        <br>
                    </c:if>
                    <c:forEach items="${questionnaire.getQuestion()}" var="question">
                        <div>
                            <input id="01" type="radio" name="r" value="1" checked>
                            <label for="01">${question.libelle}</label>
                        </div>
                    </c:forEach>
                    <!-- si un questionnaire n'a pas de questions -->
                    <c:if test="${questionnaire.questions.size() lt 1}">
                        <br>
                        <p><i> Questionnaire vide - ne doit pas arriver </i></p>
                        <textarea type="text" size="80px"  placeholder="..."></textarea>
                        <br>
                    </c:if>
                </div>
            </c:forEach>
            <br>
    </div>
</form>

