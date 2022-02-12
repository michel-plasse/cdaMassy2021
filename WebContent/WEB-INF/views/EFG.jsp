<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe" />
<h1>Exercice n°${EFG.idEfg}</h1>

<p>Initutlé : ${EFG.intitule}</p>

<p>Répartition des groupes : ${EFG.groupes}.</p>
<button>Créer les groupes</button>



</body>
</html>
