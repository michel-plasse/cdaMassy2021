<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe" />
<h1>Exercice n°${EFG.idEfg} : ${EFG.intitule}</h1>

<p>Consignes : à venir</p>


<button >${EFG.groupes}</button>

créateur : <c:out value="${EFG.getIdCreateur() }"/>

</body>
</html>
