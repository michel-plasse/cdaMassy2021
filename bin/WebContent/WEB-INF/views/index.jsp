<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<p:header title="CDA Massy 2021"/>
<%-- Message d'erreur ou pas, au cas o� --%>
<h3 class="success">${messageSuccess}</h3>
<h3 class="erreur">${messageError}</h3>
<h1>CDA Massy 2021</h1>
<section class="flexh">
  <p>Cette application est le projet fil rouge des CDA 2021 du Greta de Massy.</p>
  <p>Elle explore un support � la formation en ligne, compl�mentaire
    aux moyens audio/vid�o/partage d'�cran.</p>
  <p>Elle est d�velopp�e en Java.  
    <a href="http://dev.formationgreta91.fr:8080/cdamassy2021/">Version en ligne</a>
  </p>
</section>
<h2>Quelques points techniques</h2>
<section id="index_articles">
  <article>
    <h3>MVC</h3>
    <p>A chaque URL est associ�e un contr�leur, impl�ment� par une servlet. 
      Par exemple, <code>servlet.ConnexionServlet</code>, classe h�ritant de
      <code>javax.servlet.http.HttpServlet</code>. L'association entre l'URL
      et la servlet se fait via l'annotation
      <code>@WebServlet(urlPatterns = {"/connexion"})</code> plac�e au-desuss
      de la d�claration de la classe.</p>
    <p>Le controleur fait appel au mod�le pour acc�der � la base de donn�es, et
      passe la main � une JSP, qui affiche les donn�es, apr�s avoir ajout�
      � l'objet requ�te HTTP des attributs (des "post-it") contenant ces donn�es.
      Ex : <code>request.setAttribute("user", user)</code>, o�
      <code>user</code> est une instance de la classe <code>Personne</code>.</p>
    <p>La vue (la JSP) acc�de � ces donn�es via <code>&dollar;{user}</code>. Elle
      peut aussi acc�der aux param�tres de la requ�te via
      <code>&dollar;{param["unParametre"]}</code>, de la session via
      <code>&dollar;{sessionScope["uneVariableDeSession"]}</code>, ou de l'application
      via <code>&dollar;{applicationScope["uneVariableDApplication"]}</code>.</p>
  </article>

  <article>
    <h3>Mise en commun de menus et bas de page</h3>
    <p>Toutes les pages de l'application affichent le m�me menu en haut et le
      m�me bas de page.
      <br/>Le naut de page et le bas de page sont �crits dans les fichiers 
      <code>header.tag</code> et <code>footer.tag</code> situ�s dans 
      <code>WEB-INF/tags/</code>.
      <br/>Ils sont inclus dans les JSP via <code>&lt;p:header/></code> et 
      <code>&lt;p:footer/></code>.</p>
  </article>

  <article>
    <h3>Pages � acc�s prot�g�</h3>
    <p>Except� quelques pages d'acc�s public, la plupart n�cessitent une 
      authentification.
    </p>
    <p>Ceci est contr�l� par la classe <code>context.ConnectionFilter</code>,
      qui, impl�mentant l'interface <code>javax.servlet.Filter</code>, intercepte 
      toutes les requ�tes HTTP avant de les passer � la servlet correspondante.</p>
  </article>

  <article style="clear:left;">
    <h3>Initialisations au d�marrage de l'application</h3>
    <p>La classe <code>context.ContextListener</code>, qui impl�mente l'interface
      <code>javax.servlet.ServletContextListener</code>, initialise les deux
      compteurs (nombre d'utilisateurs et nombre de ceux identifi�s apr�s une
      connexion r�ussie).</p>
    <p>Ces deux compteurs ont pour port�e application, et sont
      accessible dans les servlets et les JSP. Par exemple, dans une servlet :</p>
    <pre>
nb = getServletContext().getAttribute("nbUtilisateurs");
setAttribute("nbUtilisateurs", unEntier)</pre>
    Et dans une JSP�:
    <pre>&dollar;{applicationScope["nbUtilisateurs"]}</pre>
  </article>

  <article>
    <h3>D�marrage/fin de session</h3>
    <p>Le nombre d'utilisateurs (nombre de navigateurs consultant l'application)
      est incr�ment�/d�cr�ment� � chaque cr�ation/fin
      d'une session utilisateur, dans la classe <code>context.SessionListener</code> 
      qui impl�mente l'interface <code>javax.servlet.http.HttpSessionListener</code>.
    </p>
    <p>Le nombre d'utilisateurs connect�s est quant � lui incr�ment�/d�cr�ment�
      dans la servlet <code>ConnexionServlet</code>�:
    <pre>int nb = (int) context.getAttribute("nbIdentifies");
context.setAttribute("nbIdentifies", nb + 1);</pre>
  </article>
</section>

<section>
  <h1>R�f�rence technique</h1>
  <ul>
    <li><a href="https://www.alsacreations.com/article/lire/1376-html5-section-article-nav-header-footer-aside.html">Les balises section, article, nav, 
        aside, header, footer</a> de HTML5.</li>
    <li><a href="https://www.alsacreations.com/tuto/lire/1493-css3-flexbox-layout-module.html">Le mod�le flexbox</a> des CSS.</li>
</section>
<p:footer/>