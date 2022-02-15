<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe" />

<h1>Liste d'EFGs</h1>
<p>Il y a ${nbMembres} membres présents dans ce canal.</p>


<!-- Nous vérifions qu'il existe bien des EFGs dans la base de données -->
<!-- S'il n'y a pas d'EFGs dans la BDD, nous affichons un message -->
<c:if test="${empty EFGs}">
	<p>Il n'y a pas encore d'exercice de groupe.</p>
</c:if>

<!-- Sinon, nous affichons le ou les EFG(s) présent(s) dans la BDD -->
<c:if test="${!empty EFGs}">
	<table>
		<thead>
			<tr>
				<th>N°</th>
				<th>Intitule</th>
				<th>Accès à l'EFG</th>
				<!-- <th>Accès au groupe de l'EFG</th> -->
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${EFGs}" var="efg">
				<tr>
					<td>${efg.idEfg}</td>
					<td>${efg.intitule}</td>
					<td>
						<button type="button" onClick="location.href='EFGs/${efg.idEfg}'"
							class="btn-efg">Accéder à l'EFG</button>
					</td>
					<!-- <td>
						<button type="button" class="btn-efg"
							onClick="location.href='listerGroupesEfg?idEfg=${efg.idEfg}'">Accéder
							aux groupes de l'EFG</button>
					</td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</c:if>

<!-- ----------------------------------------------------------------------- -->
<!-- Nous vérifions ici le nombre de membres présents dans le canal afin de donner
(ou non) la possibilité au formateur de créer un exerice de groupe -->

<!-- Empêche la création d'un EFG -->
<c:if test="${nbMembres <= 5 }">
	<p style="color:red">Le nombre minimum requis d'élèves pour créer un exercice de
		groupe est de 4.<br/> Si vous souhaitez créer un exercice de groupe, veuillez rajouter
		${4 - nbMembres } personnes dans votre canal.</p>
</c:if>
<!-- Permet la création d'un EFG -->
<c:set var="idCanal" value="${idCanal}" />
<c:if test="${nbMembres >= 5}">
	<button type="button" class="btn-efg"
		onClick="location.href='EFGs/new'">Créer un exercice en
		groupe</button>
</c:if>

</body>
</html>
