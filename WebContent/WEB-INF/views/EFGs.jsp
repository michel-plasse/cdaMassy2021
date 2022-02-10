<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Exercices de groupe"/>
<h1>Liste d'EFGs </h1>

Coucou, tu es sur la page des exercices en groupe
<!-- 
<table>
    <thead> 
        <tr>
            <th>N°</th>
            <th>Intitule</th>
            <th>Accès au groupe de l'EFG</th>
        </tr>
    </thead>
    <tbody> 
        <c:forEach items="${EFGs}" var="efg">

            <tr>
                <td>${efg.id}</td>
                <td>${efg.intitule}</td>
                <td><a href="listerGroupesEfg?idEfg=${efg.id}"><button type="button" class="btn-efg">Accéder aux groupes de l'EFG</button></a></td>
            </tr>

        </c:forEach>
    </tbody>
</table>
<a href="CreationEFG">Créer un Exercice en groupe</a> -->
</body>
</html>
