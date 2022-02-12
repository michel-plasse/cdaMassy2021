<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- @page = directive de compilation (jsp pure) -->
<p:header title="CDA Massy 2021 COQUILLE"/>

<style type="text/css">
	<%@include file="../../css/coquille_style.css" %>
</style>

<nav> <c:set var="context" value="${pageContext.servletContext.contextPath}"/>

	<form id="resetDbForm" action="${context}/resetDb" method="post">
	</form>

	<nav id="mainNav">
		<nav>
			<a href="${context}/.">Accueil</a>
			<a href="javascript: document.getElementById('resetDbForm').submit()">Reinitialiser la BD </a>
		 </nav>
	
		<c:if test="${sessionScope['user'] != null}">
			<form action="${context}/connexion" method="post">
				<button type="submit">Deconnecter ${sessionScope['user'].email}</button>
				<input type="hidden" name="action" value="deconnecter"/>
			</form>
		</c:if>
	
		<c:if test="${sessionScope['user'] == null}">
			<a href="${context}/inscription">Inscription</a>
			<a href="${context}/connexion">Connexion</a>
			<a href="${context}/canaux">Canaux</a>
		</c:if>	
			${applicationScope["nbUtilisateurs"]} utilisateurs
				<br/>
			${applicationScope["nbIdentifies"]} identifies
		</nav>
	</nav>

<section>

	<nav>
		<p>	
			NOM CANAL
			<br><br><br>
			<a href="${context}/activitequestions">Mes Questions</a> <br><br>
			<a href="${context}/questionnaires">Mes Questionnaires</a>
		</p>
	</nav>

	<main>
		<center> MAIN </center>
		<ul>
			<li>lorem</li>
			<li>ipsum</li>
			<li>dolor</li>
			<li>sit</li>
			<li>amet</li>
		 </ul>
	</main>

	<aside>		
		<center> ASIDE </center>				
		<ul>
			<li>lorem</li>
			<li>ipsum</li>
			<li>dolor</li>
			<li>sit</li>
			<li>amet</li>
		</ul>
	</aside>
	
</section>


