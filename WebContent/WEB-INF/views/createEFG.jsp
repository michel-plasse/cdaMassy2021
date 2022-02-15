
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<p:header title="Creation d'un Exercice en groupe" />
<body>

	<h1>Création d'un Exercice de Groupe</h1>
	<p>Il y a ${nbMembres } membres dans ce canal.</p>
	<input type="hidden" id="nbMembresCanal" value="${nbMembres }" />

	<form:form method="post" modelAttribute="newEFG">

		<label>Intitulé</label>
		<form:input path="intitule" placeholder="intitule" id="intitule" />
		<br />

		<label>Nombre de groupes</label>
		<input type="number" min="2" max="4" id="groupes"
			onChange="reliquat()" />
		<form:input path="groupes" type="hidden" id="hiddenField" />
		<p id="messageReliquat">Aucun membre par groupe.</p>

		<button type="submit">Envoyer</button>
	</form:form>

	<script>
		const groupesList = document.querySelector("#groupes");
		let nbCanal = document.getElementById("nbMembresCanal").value;
		let messageHTML = document.querySelector("#messageReliquat");
		let message;

		function reliquat() {
			let arrGroupes = [];
			let nbGroupes = groupesList.value;

			let modulo = nbCanal % nbGroupes;

			let minMembres = Math.floor(nbCanal / nbGroupes);
			let maxMembres = minMembres;

			for (let i = 0; i < nbGroupes; i++) {
				arrGroupes.push(minMembres);
			}

			if (modulo != 0)
				maxMembres++;
			while (modulo != 0) {
				arrGroupes[modulo - 1] = maxMembres;
				modulo--;
			}

			message = "Voici la réparition de vos groupes : "
					+ arrGroupes.join(", ") + ".";
			messageReliquat.innerHTML = message;
			document.getElementById("hiddenField").value = arrGroupes
					.toString();
		}
	</script>
</body>
