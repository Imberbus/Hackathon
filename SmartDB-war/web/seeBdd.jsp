<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Pratt - Free Bootstrap 3 Theme">
        <meta name="author" content="Alvarez.is - BlackTie.co">
        <link rel="shortcut icon" href="assets/ico/favicon.png">

        <title>SmartDB</title>

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
                    <a class="navbar-brand" href="/SmartDB-war/"><img src="assets/logo.png" height="46px"/></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/SmartDB-war/home" class="smothscroll">Overview</a></li>
                        <li><a href="account" class="smothScroll">My Account</a></li>
                        <li><a href="/SmartDB-war/DeconnexionServlet" class="smothScroll">Sign Out</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>



        <!-- INTRO WRAP -->
        <div id="intro" style="padding : 0">

            <div class="container">
                <div class="col-lg-12">
                    <h2>How to use MongoDB</h2>
                </div>
                <br>
            </div> <!--/ .container -->
            
            <div class="container">
                    <h3>Installation</h3>
                
                <ul style="padding-left: 15px">
                    <li><a href="https://docs.mongodb.org/manual/tutorial/install-mongodb-on-windows/">Windows</a> </li>
                    <li><a href="https://docs.mongodb.org/manual/tutorial/install-mongodb-on-os-x/">Mac</a> </li>
                    <li><a href="https://docs.mongodb.org/manual/tutorial/install-mongodb-on-ubuntu/">Ubuntu</a> </li>
                    <li><a href="https://docs.mongodb.org/manual/">Others</a> </li>
                </ul>
            </div>
            
             <div class="container">
                    <h3>Connection</h3>
                
                    <br>
                    <p>Access MongoDB via this command : </p><br>
                    <p style="background: #FFFFFF;font-style: bold"> &nbsp;mongo 149.202.70.58:   your_port  / your_dbname -u your_uname -p your_password</p>
            </div>

        </div> 


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
