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
                        <li class="active"><a href="home" class="smothscroll">Accueil</a></li>
                        <li><a href="account" class="smothScroll">Mon compte</a></li>
                        <li><a href="/SmartDB-war/DeconnexionServlet" class="smothScroll">Deconnexion</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        <!-- INTRO WRAP -->
        <div id="intro" style="padding : 0">

            <div class="container">
                <h2>SUpprimer votre capsule</h2>
                <br>
                <h3>Choisissez la capsule à supprimer</h3>

                <h4 style="color: #ac2925">Attention : Un retour arriere n'est pas possible.</h4>
                <br>
                <div>
                    <form method="POST" action="DelBddServlet">                  
                         ${entreprise}                                

                        <div class="form-group" align="center">
                            <a href="home"  class="btn btn-warning" type="submit">Annuler</a>&nbsp;
                            <input  class="btn btn-success" type="submit" value="Valider" />
                        </div>

                    </form>
                </div>
            </div> <!--/ .container -->


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
