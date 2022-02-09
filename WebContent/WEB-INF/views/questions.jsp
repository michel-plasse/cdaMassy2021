<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
<h1>affichage questions:</h1>
<form>
    <div class="questionLayout">
            <c:forEach items="${questions}" var="question">
                <div class="form-question">
                    <div class="intituleQuestion" id="intituleQuestion">
                            <b>${question.libelle}</b>
                            <p> auteur: ${question.nomAuteur}<p>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
</form>
