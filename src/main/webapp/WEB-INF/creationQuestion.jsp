<%-- 
    Document   : Creation Question

    Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CreationQuestion"/>
<h1>Formulaire de création d'une question !</h1>
<h2 class="erreur">${message}</h2>
<h2 class="success">${messageSuccess}</h2>
<div id="formCreationQuestion">
    <form method="POST">
        <p><i>Complétez le formulaire. Les champs marqué par </i><em>*</em> sont <em>obligatoires</em></p>
        <fieldset>
            <legend> Nouvelle Question: </legend>
            <label for="libelleQuestion"> Libelle <em>*</em></label><h4 class="erreur">${erreur_libelle}</h4>
            <textarea id="libelle" placeholder="Ecrivez votre question" autofocus=""  name="libelleQuestion"></textarea><br>
        </fieldset>
            <input id="proposition" type="text"    name="proposition"><br>
        <p><input type="submit" value="Valider"></p>
    </form>
</div>
            
</body>

</html>
