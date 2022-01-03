<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CDA Massy 2021"/>
<h1>Membres</h1>
<ol>
  <c:forEach items="${membres}" var="membre">
    <li>${membre.prenom} ${membre.nom}</li>
  </c:forEach>
</ol>