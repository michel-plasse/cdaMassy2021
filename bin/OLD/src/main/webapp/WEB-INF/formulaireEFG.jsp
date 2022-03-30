<%-- 
    Document   : formulaireEFG
    Created on : 2 janv. 2022, 23:03:23
    Author     : Florian
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<p:header title="Creation d'un Exercice en groupe"/>
<body>
    <c:out value="Il y a actuellement : ${nbreMembres} membres dans ce canal."></c:out>
        <form action = "AfficheEFG" method = "post">
            <label for="intitulé">Intitule</label>
            <input type="text" id="intitulé" name="intitulé" />
            </br>


            <label for="nbPerso">Nb de personnes par groupe</label>
            <input type="number" min="2" max="5" id="nbPerso" name="nbPerso" onChange="reliquat()"/>
            <p id="messageReliquat">Aucun membre par groupe.</p>

            <input type="submit" value="Valider" />
            <input type="button" value="Annuler" />
        </form>

        <script>
            const nbPersoCanal = parseInt(${nbreMembres});
            console.log(nbPersoCanal);
            const nbPersonnes = document.querySelector("#nbPerso");
            let messageReliquat = document.querySelector("#messageReliquat");
            let message;
            let nbGroupe;

            function reliquat() {

                let num = nbPersonnes.value;
                let modulo = nbPersoCanal % num;
                console.log(modulo);
                let sum = nbPersoCanal / num;
                console.log(sum);

                switch (modulo) {
                    case 0 :
                        nbGroupe = sum;
                        message = "Il y a " + nbGroupe + "groupes de " + num + "personnes.";
                        break;
                    case 1 :
                        nbGroupe = Math.floor(sum);
                        message = "Il y a "+ nbGroupe + ", mais l'un ou plusieurs groupes a un membre de plus que le nombre souhaité.";
                        break;
                    default:
                        nbGroupe = Math.floor(sum);
                        message = "Il reste " + modulo + " personne(s) sans groupe. Que souhaitez-vous faire ?</br>";
                        message += "<label for='choixReliquat'></label><input type='radio' name='choixReliquat' onClick='rajouteGroupe()' value='" + nbGroupe +"' id='rajout'>Rajouter un groupe</input><input type='radio' value='" + nbGroupe + "' name='choixReliquat' id='dispatch' onClick='dispatcher()'>Dispatcher les élèves dans des groupes.</input>";

                }

                messageReliquat.innerHTML = message;
            }

            function dispatcher() {
                let choice = document.querySelector("#dispatch");
                nbGroupe = parseInt(choice.value);
                // let nbPerson = nbPersoCanal / nbGroupe;
                // let modulo = nbPersoCanal % nbPerson;
                // while (modulo !== 0) {
                let modulo = nbPersoCanal % nbGroupe;
                let nbPersonnes = Math.floor(nbPersoCanal / nbGroupe);

                let nbPersonnesInit = Math.floor(nbPersoCanal / nbGroupe);
                while (modulo !== 0) {
                    nbPersonnes++;
                    modulo--;
                }
                message = "Vous avez dispatché " + nbPersonnesInit + "élèves dans les " + nbGroupe + " demandés.";
                messageReliquat.innerHTML = message;
            }


            function rajouteGroupe() {
                let choice = document.querySelector("#rajout");
                nbGroupe = parseInt(choice.value) + 1;
                message = "Il y a " + nbGroupe + "groupes au lieu de " + (nbGroupe - 1) + ".";
                messageReliquat.innerHTML = message;
            }
    </script>
</body>
