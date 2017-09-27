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
                        <li class="active"><a href="/SmartDB-war/home" class="smothscroll">Accueil</a></li>
                        <li><a href="account" class="smothScroll">Mon compte</a></li>
                        <li><a href="/SmartDB-war/DeconnexionServlet" class="smothScroll">Deconnexion</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        <!-- INTRO WRAP -->
        <div id="intro" style="padding : 0">

            <div class="container">
                <div class="col-lg-12">
                    <h2>Resume</h2>
                </div>
                <br>
            </div> <!--/ .container -->

            <div class="row">
                <div class="col-sm-2"></div>
                <div class="table-responsive col-sm-8">
                    
                    <h4>&nbsp;Mes capsules</h4>
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Taille</th>
                                <th>Date d'envoi</th> 
                            </tr>
                        </thead>
                        <tbody>
                            ${profil}                                   
                        </tbody>
                    </table>
                </div>
                <div class="col-sm-2"></div>
            </div> 
            <br>
            <div  align="center">
                    <a  style="margin-top: 5px;" href="/SmartDB-war/AddBddServlet" class="btn btn-danger">Programmer votre capsule</a>
                    <a  style="margin-top: 5px;" href="/SmartDB-war/DelBddServlet" class="btn btn-danger">Supprimer une capsule</a>
                    <!--<a  style="margin-top: 5px;" href="/SmartDB-war/seeBdd.jsp" class="btn btn-danger">MongoDB Help</a> -->
                    <!--<a style="margin-top: 5px;"   href="/SmartDB-war/MonitoreBddServlet" class="btn btn-danger">Monitoring Database</a>-->
            </div>
        </div><!--/ #introwrap -->


        <!-- /.navbar-collapse -->
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
    <!-- /#wrapper -->

</body>
</html>
