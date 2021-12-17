<%-- 
    Document   : questions
    Created on : 17 déc. 2021, 02:52:58
    Author     : thoma
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
 <link rel="stylesheet" href="/css/questionnaire_style.css">
<h1>Question</h1>
<ol>
    
  <c:forEach items="${questions}" var="question">
    <li>${question.idCreateur} ${question.libelle}</li>
     <form class="form" action="">
        <div class="bordered">
          <p>Veuillez choisir la meilleure option ci-dessous :</p>
          <div> <br>
            <div class="reponseQuestion">
              <input type="radio" name="card" id="option_1" value="option_1">
              <label for="option_1" aria-label="option_1">
                <span></span>
                Option 1
              </label>
            </div>
            <div class="option">
              <input type="radio" name="card" id="option_2" value="option_2">
              <label for="option_2" aria-label="option_2">
                <span></span>
                Option 2
              </label>
            </div>
            <div class="option">
              <input type="radio" name="card" id="option_3" value="option_3">
              <label for="option_3" aria-label="option_3">
                <span></span>
                Other
              </label>
            </div>
            <div class="button">
              <button class="btn">Envoyer</button>
            </div>
          </div>
        </div>
      </form>
  </c:forEach>
</ol>