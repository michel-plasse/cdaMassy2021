<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Canaux" />
<%-- Message d'erreur ou pas, au cas où --%>
<h3 class="success">${messageSuccess}</h3>
<h1>Canaux</h1>
<nav>
	<c:if test="${empty canaux}">
		<p>
			<c:out value="La liste est vide"></c:out>
		</p>
	</c:if>
	<c:forEach items="${canaux}" var="canal">
    ${canal.nom} : 
    	<a href="membrescanal?idCanal=${canal.idCanal}">Membres</a>

		<button type="button"
			onClick="location.href='canaux/${canal.idCanal}/EFGs'"
			class="btn-efg">Exercices</button>

		<a href="questions/${canal.idCanal}/afficher">Questions</a>
	</c:forEach>

</nav>
<p:footer />

