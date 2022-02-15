<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe" />


<h1>Exercice n°${EFG.idEfg}</h1>
<table>
	<thead>
		<tr>
			<th>Intitulé</th>
			<th>Répartition des groupes</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${EFG.intitule}</td>
			<td>${EFG.groupes}</td>
		</tr>
	</tbody>
</table>

<!-- créateur :
<c:out value="${EFG.getIdCreateur() }" /> -->
</body>
</html>
