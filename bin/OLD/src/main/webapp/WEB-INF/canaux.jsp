<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Canaux" />
<h1>Canaux</h1>
<nav>
	<c:if test="${empty canaux}">
		<p>
			<c:out value="La liste est vide"></c:out>
		</p>
	</c:if>
	<c:forEach items="${canaux}" var="canal">
    ${canal.nomCanal} : <a href="membrescanal?idCanal=${canal.idCanal}">Membres</a>
    <input type="hidden" 
		<a href="ListeEFGs?idCanal=${canal.idCanal}">Exercices en groupe</a>
		/>
	</c:forEach>
</nav>