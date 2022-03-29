<%-- 
    Document   : Creation Question

    Author     : thoma
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="CreationQuestion"/>
 <style type="text/css">
  		<%@include file="../../css/questionaire_style.css" %>  
  		
</style> <!-- @include =  directive de compilation (jsp pure) -->
<h3 class="success">${messageSuccess}</h3>
<h3 class="erreur">${messageError}</h3>

<h1>Formulaire de création question</h1>

<form class="form-question" action="${pageContext.request.contextPath}/questions/enregistrer" method="POST">
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
                <label for="libelleQuestion"> Propositions </label><h4 class="erreur">${erreur_libelle}</h4>
                <fieldset>
                    <div class="blocAllPropositions">
                        Ajouter une proposition:<button type="button" id="addProposition">+</button>
                        <div id="blocProposition">
                            <textarea type="text"  id="proposition" placeholder="Ecrivez votre proposition" name="proposition"></textarea>
                            <input type="radio" id="correctness" name="correctness" value="2" checked="true">Indéfinie
                            <input type="radio" id="correctness" name="correctness" value="1">Correcte
                            <input type="radio" id="correctness" name="correctness" value="0">Incorrecte             
                        </div>
                    </div>
                </fieldset>
            </b>
            <b>
                <label for="canalChoisi">Canal<em>*</em></label><h4 class="erreur">${erreur_canal}</h4>
                <fieldset>
                    <label for="Situation-select">Choisissez un canal:</label>
                    <select name="canalChoisi" id="canal-select">
                        <c:forEach items="${allCanauxMembre}" var="canal">
                            <option value="${canal.idCanal}">${canal.nom} 1</option>
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
<p:footer/>




