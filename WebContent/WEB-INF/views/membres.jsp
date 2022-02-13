<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021" />
<h1>Membres</h1>
<c:if test="${empty membres}">

	<p> <c:out value="La liste est vide"></c:out></p>
</c:if>
<ol>
    <c:forEach items="${membres}" var="membre">
        <li>
            <form action="canaux/${canal.idCanal}/${idPersonne}/enleve" method="post">
                <input type="hidden" id="idMembreAEffacer" value="${membre.idPersonne}"
                       name="idMembreAEffacer" /> 
                <input type="hidden" value="${canal.idCanal}" name="idCanal" />
                ${membre.prenom}  ${membre.nom}
                <button type="submit" >Supprimer</button>
            </form>
        </li>

    </c:forEach>
</ol>

<h3>Ajout De Membres Au Canal</h3>

<form id="formAjouter" action="ajouterMembre" method="post">
    ID Du Membre A Ajouter : <br>
    <input type="text" name="idPersonneAjouter">
    <input type="hidden" value="${idCanal}" name="idCanalAjouter" />
    <br>
    <button>Ajouter</button>
</form>

<!-- <script>
    let formsSupprimer = document.querySelectorAll("form[action='enleverMembre']");
    console.log("numbre de formulaires :" + formsSupprimer.length);
    for (let i = 0; i < formsSupprimer.length; i++) {
        formsSupprimer[i].onsubmit = function (event) {
            console.log(i);
            if (!confirm("Voulez vous supprimer ?")) {
                event.preventDefault();
            }
        }
    }
</script> -->