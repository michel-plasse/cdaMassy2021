<%-- 
   Document   : sondages
   Created on : 31 déc. 2021, 16:52:58
   Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css" />
<h1>affichage sondages</h1>
<form>
    <div class="questionLayout">
        <c:forEach items="${sondages}" var="sondage">
            <div class="form-question">
                <div class="intituleQuestion" id="intituleQuestion">
                    <b>${sondage.libelle}</b>
                    <p> auteur: ${sondage.nomAuteur}<p>
                </div>
                <c:forEach items="${sondage.getResults()}" var="result">
                    <div>réponse: ${result.libelle}</div>
                    <div>a voté: ${result.nomsPersonnes}</div>
                    <c:set var="pourcentage" value = "${result.nbVotes/sondage.reponses.size()*100}"></c:set>
                        <div id="container" style="width:100%; height:12px; border:1px solid black;">
                            <div id="progress-bar" style="width:${pourcentage}%;/*change this width */
                                 background-color:#bfbffd;
                                 height:12px;">
                            </div>
                        </div>

                        <div> ----------- </div>
                </c:forEach>
            </div>
        </c:forEach>
        <br>
    </div>
</form>

