<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- @page = directive de compilation (jsp pure) -->
<p:header title="CDA Massy 2021"/>

    <style type="text/css">
  		<%@include file="../../css/coquille_style.css" %>  
	</style>
	
<h1>affichage questionnaires:</h1>

<form>
    <div class="questionLayout">
            <c:forEach items="${questionnaires}" var="questionnaire"> 
                <div class="form-questionnnaire">
                    <div class="intituleQuestionnaire" id="intituleQuestionnaire">
                            
                            <p> auteur: ${questionnaire.idCreateur} - libellé: ${questionnaire.libelle}<p>
                    </div>
                </div>
            </c:forEach>
            <br>
    </div>
</form>

<p:footer />
