<%-- 
    Document   : activitequestions
    Created on : 27 d�c. 2021, 03:31:56
    Author     : thoma
--%>
<%-- Message d'erreur ou pas, au cas o� --%>
${msg}
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<p:header title="Activit�: Mes questions."/>
 <style type="text/css">
  		<%@include file="../../css/questionaire_style.css" %>  
</style> <!-- @include =  directive de compilation (jsp pure) -->
<%-- Message d'erreur ou pas, au cas o� --%>
${msg}
<h1>Activit�: Mes questions.</h1>
<h2 class="erreur">${message}</h2>
<h2 class="success">${messageSuccess}</h2>
<section class="flexh">
    <p>Ceci est votre espace de travail depuis lequel vous pouvez editer, 
        gerer, afficher ou reproposer des questions que vous avez d�ja pos�
        ainsi qu'en creer de nouvelles.</p>
    <a href="http://dev.formationgreta91.fr:8080/cdamassy2021/">Version en ligne</a>
</p>
</section>
<section id="index_articles">
    <article>
        <h3>Afficher vos questions (Not Implemented yet)</h3>
        <p>Affiche la liste des questions que vous avez d�j� pos�es.</p>
        <form action="/CeLienNEstPasImplemente"class="boutonActivite" >
            <button type="submit">Afficher Questions</button>
        </form>
    </article>

    <article>
        <h3>Poser une question</h3>
        <p>Proposez une nouvelle question sur un canal.</p>
        <form action="${pageContext.request.contextPath}/questions/creer"class="boutonActivite" >
            <button type="submit">Nouvelle Question</button>
        </form>
    </article>

    <article>
        <h3>Afficher les sondages</h3>
        <p>Affiche la liste des sondages.</p>
        <form action="${context}/CeLienNEstPasImplemente"class="boutonActivite" >
            <button type="submit">Afficher Sondages</button>
        </form>
    </article>

    <article>
        <h3>Repondre aux questions</h3>
        <p>Affiche la liste des question qui vous ont �t� pos�es.</p>
        <form action="${pageContext.request.contextPath}/questions/repondre/${canal}"class="boutonActivite" >
                <label for="canal">Choisissez un canal:</label>
                <select name="canal" id="canal-select">
                    <c:forEach items="${allCanauxMembre}" var="canal">
                        <option value="${canal.idCanal}">${canal.nom}</option>
                    </c:forEach>
                </select>
            <button type="submit">Repondre aux questions</button>
        </form>
    </article>
    
    <article>
        <h3>Afficher les questionnaires</h3>
        <p>Affiche la liste des questionnaires.</p>
        <form action="${pageContext.request.contextPath}/questionnaire/afficher" class="boutonActivite" >
            <button type="submit">Afficher Questionnaires</button>
        </form>
    </article>

    <article style="clear:left;">
        <h3>Editer une question(Not Implemented yet)</h3>
        <p>En cours d'impl�mentation</p>
    </article>
    <p:footer/>