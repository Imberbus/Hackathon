<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Pratt - Free Bootstrap 3 Theme">
        <meta name="author" content="Alvarez.is - BlackTie.co">
        <link rel="shortcut icon" href="assets/2.png">

        <title>Capsuline</title>

        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="assets/css/main.css" rel="stylesheet">

        <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/smoothscroll.js"></script>


    </head>

    <body data-spy="scroll" data-offset="0" data-target="#navigation">

        <!-- Fixed navbar -->
        <div id="navigation" class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/SmartDB-war/"><img src="assets/1.png" height="46px"/></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="home" class="smothscroll">Accueil</a></li>
                        <li class="active"><a href="account" class="smothScroll">Mon compte</a></li>
                        <li><a href="/SmartDB-war/DeconnexionServlet" class="smothScroll">Deconnexion</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        <!-- INTRO WRAP -->
        <div id="intro" align="center" style="padding : 0 0;">
            <form method="POST"  class="container">
                    <h2>Gestion du compte</h2>
                    <br>
                    <h4>Mon offre : ${acctype}</h4>
                    <br>
                    <h4>Changer son email : </h4>
                    <h5>${errormail}</h5>
                    <input type="password" name="pwd" style="vertical-align: middle" placeholder="Taper votre mot de passe">
                    <input type="text" name="newmail" style="vertical-align: middle" placeholder="Nouveau mail">
                    &nbsp;<input class="btn btn-sm btn-info" type="submit" name ="changemail" value="Valider">
                    <br>
                    <br>
                    <h4>Changer son mot de passe : </h4>
                    <h5>${errorpwd}</h5>
                    <input type="password" name="oldpwd" style="vertical-align: middle" placeholder="Ancien mot de passe">
                    <input type="password" name="newpwd" style="vertical-align: middle" placeholder="Nouveau mot de passe">
                    &nbsp;<input class="btn btn-sm btn-info" type="submit" name ="changepwd" value="Valider">
                    <br>
            </div> <!--/ .container -->
        </div><!--/ #introwrap -->


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="assets/js/bootstrap.js"></script>
        <script>
            $('.carousel').carousel({
                interval: 3500
            })
        </script>
    </body>
</html>
