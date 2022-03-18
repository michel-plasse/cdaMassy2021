<%-- 
    Document   : Creation Question

    Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
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
            <b>
                <label for="libelleQuestion"> Propositions <em>*</em></label><h4 class="erreur">${erreur_libelle}</h4>
                <fieldset>
                    <div class="blocAllPropositions">
                        Ajouter une proposition:<button type="button" id="addProposition">+</button>
                        <div id="blocProposition">
                            <textarea type="text"  id="proposition" placeholder="Ecrivez votre proposition" name="proposition"></textarea>
                            <input type="radio" id="correctness" name="correctness" value="Undefined" checked="true">Indéfinie
                            <input type="radio" id="correctness" name="correctness" value="Correct">Correcte
                            <input type="radio" id="correctness" name="correctness" value="Incorrect">Incorrecte             
                        </div>
                    </div>
                </fieldset>
            </b>
            <b>
                <label for="canalChoisi">Canal<em>*</em></label><h4 class="erreur">${erreur_canal}</h4>
                <fieldset>
                    <label for="Situation-select">Choisissez un canal:</label>
                    <select name="canalChoisi" id="canal-select">
                        <c:forEach items="${canauxMembre}" var="canal">
                            <option value="${canal.idCanal}">${canal.nomCanal}</option>
                        </c:forEach>
                    </select>
                </fieldset>
            </b>
        </div>
    </div>
    <br/>
</div>
<button type="submit">Valider</button>
</form>
</div>
</body>
<script>
    document.getElementById("addProposition").onclick = function (event) {
        let ele = document.getElementById("blocProposition");
        let copie = ele.cloneNode(true);
        let rank = ele.parentNode.childElementCount;
        copie.id = ele.id + rank;
        copie.getElementsByTagName('input')[0].name = "correctness" + rank;
        copie.getElementsByTagName('input')[0].id = "correctness" + rank;
        copie.getElementsByTagName('input')[0].checked = "checked";
        copie.getElementsByTagName('input')[1].name = "correctness" + rank;
        copie.getElementsByTagName('input')[1].id = "correctness" + rank;
        copie.getElementsByTagName('input')[2].name = "correctness" + rank;
        copie.getElementsByTagName('input')[2].id = "correctness" + rank;
        ele.parentNode.appendChild(copie);
    };

</script>
</html>


