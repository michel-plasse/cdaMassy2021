<%-- 
    Document   : questionnaires
    Created on : 4 mai 2021, 10:07:51
    Author     : sandra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/questionnaire_style.css">
  <title>Questionnaires</title>
</head>

<body>
  <div class="row">
    <div class="title">Bienvenu</div>
    <div class="subtitle">Répondre aux questions</div><br>
    <div class="column">
      <form class="form" action="">
        <div class="bordered">
          <p>Veuillez choisir la meilleure option ci-dessous :</p>
          <div> <br>
            <div class="reponseQuestion">
              <input type="radio" name="card" id="option_1" value="option_1">
              <label for="option_1" aria-label="option_1">
                <span></span>
                Option 1
              </label>
            </div>
            <div class="option">
              <input type="radio" name="card" id="option_2" value="option_2">
              <label for="option_2" aria-label="option_2">
                <span></span>
                Option 2
              </label>
            </div>
            <div class="option">
              <input type="radio" name="card" id="option_3" value="option_3">
              <label for="option_3" aria-label="option_3">
                <span></span>
                Other
              </label>
            </div>
            <div class="button">
              <button class="btn">Envoyer</button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</body>

</html>