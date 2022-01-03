<%-- 
    Document   : formulaireEFG
    Created on : 2 janv. 2022, 23:03:23
    Author     : Florian
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Creation d'un Exercice en groupe"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function fn1() {
                var txt = document.getElementById("Intitulé").value;
                alert(txt);
            }
            function fn2() {
                var txt = document.getElementById("Consignes").value;
                alert(txt);
            }
            function fn3() {
                var txt = document.getElementById("nbrPersoInput").value;
                alert(txt);
            }
        </script>
    </head>
    <body>

        <label for="nbrPersoInput">Nb de personnes dans le groupe</label>
        <br />
        <br />
        <select id="nbrPersoInput">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>

        <p></p>

        <script>
            const select = document.querySelector('select');
            const para = document.querySelector('p');

            select.addEventListener('change', setNbrPersoInput);

            function setNbrPersoInput() {
                const choice = select.value;
                if (choice === '1') {
                    para.textContent = 'Il manque 3 personnes pour completer le groupe';
                } else if (choice === '2') {
                    para.textContent = 'Il manque 2 personnes pour completer le groupe';
                } else if (choice === '3') {
                    para.textContent = 'Il manque 1 personne pour completer le groupe';
                } else if (choice === '4') {
                    para.textContent = '';
                } else {
                    para.textContent = '';
                }
            }
        </script>
        <form action ="AfficheEFG" method ="post">
            <p>
                <label for="Intitulé">Intitulé:</label>
                <br>   
                <input id="Intitulé" name="Intitulé"
                       rows="5" cols="33"/>
                <br>  
                <!--<label for="Consignes">Consignes:</label>
                <br> 
            <textarea id="Consignes" name="Consignes"
                rows="5" cols="33">
            </textarea> -->
                <br />

                <input type="submit" value="valider" />
                <input type="button" value="Annuler" onclick="alert('Annuler');" />
                <br />
                <br />
                <button onClick="fn1();
                        fn2();
                        fn3();" id = "btn1">ClickOK</button>
                <br />
            </p>
        </form>
    </body>
</html>