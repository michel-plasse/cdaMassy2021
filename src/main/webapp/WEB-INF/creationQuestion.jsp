<%-- 
    Document   : Creation Question

    Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CreationQuestion"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/question.css"/>
<style>
    .bloc {
        display:flex;
        flex-grow: true;
        min-height: 3vh;
        min-width: 25vh;
        border: #181a24;
        border-width: 2px;
        background-color: lavender;
        color: darkslateblue;
        vertical-align: top;
    }
    .bloc > * {
    }
</style>
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

        Ajouter une proposition de réponse : 
        <button type="button" id="addProposition">+</button>
        <div class="bloc">
            <div id="blocProposition"> Propositions<br>
                 <input type="text" name="proposition" id="proposition">
            </div>
           
        </div>

        <br/>

        <button type="submit">Valider</button>

        <script>
            document.getElementById("addProposition").onclick = function (event) {
                let ele = document.getElementById("proposition");
                let copie = ele.cloneNode(true);
                let rank = ele.parentNode.childElementCount;
                copie.id = ele.id + rank;
                ele.parentNode.appendChild(copie);
            }
        </script>



    </form>
</div>

</body>

</html>
