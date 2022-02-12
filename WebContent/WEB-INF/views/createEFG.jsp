
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<p:header title="Creation d'un Exercice en groupe" />
<body>
<h1>Création d'un Exercice de Groupe</h1>

<form:form method="post" modelAttribute="newEFG">

    <form:input path="intitule" placeholder="intitule" id="intitule"/>
    <form:input path="groupes" placeholder="groupes" id="groupes"/>
    <!-- Gestion du reliquat avant de transmettre la répartition des groupes -->
    <button type="submit">Envoyer</button>
</form:form>

	<script>
		function getValue() {
			// Sélectionner l'élément input et récupérer sa valeur
			var input = document.getElementById("intitule").value;
			
			// Afficher la valeur
			console.log(input);
		}
	</script>
</body>
