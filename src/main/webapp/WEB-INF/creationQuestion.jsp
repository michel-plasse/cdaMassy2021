<%-- 
    Document   : Creation Question

    Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CreationQuestion"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css"/>
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
<h1>Formulaire de création question</h1>
<h2 class="erreur">${message}</h2>
<h2 class="success">${messageSuccess}</h2>
<form class="form-question" method="POST">
    <legend> Nouvelle Question: </legend>
    <p><i>Complétez le formulaire. Les champs marqué par </i><em>*</em> sont <em>obligatoires</em></p>
    <div class="questionLayout">
        <div class="intituleQuestion" id="intituleQuestion">
            <b>
                <label for="libelleQuestion"> Libelle <em>*</em></label><h4 class="erreur">${erreur_libelle}</h4>
                <fieldset>
                    <textarea id="libelle" placeholder="Ecrivez votre question" autofocus=""  name="libelleQuestion"></textarea><br>
                </fieldset>
            </b>
            <div class="reponseEdit">
                <div class="blocAllPropositions">
                    Ajouter une proposition:<button type="button" id="addProposition">+</button>
                    <div id="blocProposition">
                        <input type="textarea" id="proposition" placeholder="Ecrivez votre proposition" name="proposition"></textarea><br>
                        <input type="radio" id="correctness" name="correctness" value="Undefined" checked="true">Undefined
                        <input type="radio" id="correctness" name="correctness" value="Correct">Correct
                        <input type="radio" id="correctness" name="correctness" value="Incorrect">Incorrect               
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</div>
<button type="submit">Valider</button>

<script>
    document.getElementById("addProposition").onclick = function (event) {
        let ele = document.getElementById("blocProposition");
        let copie = ele.cloneNode(true);
        let rank = ele.parentNode.childElementCount;
        copie.id = ele.id + rank;
        copie.getElementsByTagName('input')[1].name = "correctness" +rank;
        copie.getElementsByTagName('input')[1].checked = "checked";
        copie.getElementsByTagName('input')[2].name = "correctness" +rank;
        copie.getElementsByTagName('input')[3].name = "correctness" +rank;
        ele.parentNode.appendChild(copie);
    };

</script>
</form>
</div>
</body>
</html>
