<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe" />

<h1>Liste d'EFGs</h1>

<c:if test="${empty EFGs}">
	<p>
		<c:out value="Il n'y a pas encore d'exercice de groupe."></c:out>
	</p>
</c:if>

<c:if test="${!empty EFGs}">
	<table>
		<thead>
			<tr>
				<th>N°</th>
				<th>Intitule</th>
				<th>Accès à l'EFG</th>
				<th>Accès au groupe de l'EFG</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${EFGs}" var="efg">
				<tr>
					<td>${efg.idEFG}</td>
					<td>${efg.intitule}</td>
					<td>
						<button type="button" onClick="location.href='EFGs/${efg.idEFG}'"
							class="btn-efg">Accéder à l'EFG</button>
					</td>
					<td>
						<button type="button" class="btn-efg"
							onClick="location.href='listerGroupesEfg?idEfg=${efg.idEFG}'">Accéder
							aux groupes de l'EFG</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<button type="button" class="btn-efg" onClick="location.href='listerGroupesEfg?idEfg=${efg.idEFG}'">Créer un exercice en groupe</button>

</body>
</html>
