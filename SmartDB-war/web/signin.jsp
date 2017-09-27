<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Pratt - Free Bootstrap 3 Theme">
        <meta name="author" content="Alvarez.is - BlackTie.co">
        <link rel="shortcut icon" href="assets/2.png">

        <title>S'authentifier</title>

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
                        <li><a href="/SmartDB-war/" class="smothscroll">Accueil</a></li>
                        <li><a href="register" class="smothScroll">Creer un compte</a></li>
                        <li class="active"><a href="login" class="smothScroll">S'authentifier</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>


        <section id="home" name="home"></section>
        <div id="headerwrap">
            <div class="container">
                <div class="row centered">
                    <div class="col-lg-12">
                        <h3>Renseigner vos identifiants.</h3>
                        <br>
                        <br>

                        <div>
                            <form method="POST" action="login">
                                <div class="form-group">
                                    <div><input name="username" placeholder="Username" class="form-control" type="text" id="UserUsername"/></div>
                                </div> 

                                <div class="form-group">
                                    <div><input name="password" placeholder="Password" class="form-control" type="password" id="UserPassword"/></div>
                                </div> 

                                <div class="form-group">
                                    <div ><input  class="btn btn-success btn btn-success" type="submit" value="Connect"/></div>
                                </div>

                            </form>
                        </div>
                            <p style="text-align:center">Pas de compte ? Creer en <a href="register" target="_blank">ici</a>.<br>Mot de passe oublié ? Cliquer <a href="resetpwd" target="_blank">ici</a>.</p>

                    </div>


                </div>
            </div> <!--/ .container -->
        </div><!--/ #headerwrap -->


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
