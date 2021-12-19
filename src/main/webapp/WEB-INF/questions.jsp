

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
<div class="questionLayout">
   <ol>
      <c:forEach items="${questions}" var="question">
         <form class="form-question">
            <li><b>${question.idCreateur} ${question.libelle}</b></li>
            <br>
            <p><i>Veuillez choisir la meilleure option ci-dessous :</i></p>
            <br>
            <label for="01">Option 1</label>
            <input id="01" type="radio" name="r" value="1" checked>
            <label for="02">Option 2</label>
            <input id="02" type="radio" name="r" value="2">
            <label for="03">Others</label>
            <input id="03" type="radio" name="r" value="3">
            <button class="btn">Envoyer</button>
         </form>
         <br>
      </c:forEach>
   </ol>
</div>

