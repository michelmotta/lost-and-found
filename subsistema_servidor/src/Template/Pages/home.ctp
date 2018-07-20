<?php
$this->layout = false;
?>
<!doctype html>
<html>
<head>
    <?= $this->Html->charset() ?>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App Server</title>
    <?= $this->Html->meta('icon') ?>

    <?= $this->Html->css('main.css') ?>

    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <?= $this->fetch('meta') ?>
    <?= $this->fetch('css') ?>
    <?= $this->fetch('script') ?>
</head>
  <body class="home-back">
    <div class="container">
        <nav class="navbar navbar-expand-lg nav-back">
            <a class="navbar-brand" href="#">App Server</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <?= $this->Html->link(__('Home'), ['controller' => 'Pages', 'action' => 'display'], ['class' => 'nav-link']) ?>
                    </li>
                    <li class="nav-item active">
                        <?= $this->Html->link(__('Dashboard'), ['controller' => 'Pages', 'action' => 'dashboard'], ['class' => 'nav-link']) ?>
                    </li>
                    <li class="nav-item">
                        <?= $this->Html->link(__('Login'), ['controller' => 'Users', 'action' => 'login'], ['class' => 'nav-link']) ?>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4" style="margin-top:30px;margin-bottom:30px;">
                <center><?= $this->Html->image('logo-branca.png', ['alt' => 'Logo', 'class' => 'img-fluid']); ?></center>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
            <div class="row">
                <?php
                    $post_counter = 0;
                    $per_row = 3;

                    foreach ($objects as $object) {
                        if ( $post_counter % $per_row == 0 && $post_counter !== 0 )
                            echo '</div><div class="row text-center">';
                ?>
                <div class="col-md-4">
                    <div class="box">
                        <div class="object-thumb">
                            <img src="<?= $object->image; ?>" class="img-fluid"/>
                        </div>
                        <div class="object-title" style="margin-top:30px;">
                            <center><p class="p1">Título</p></center>
                            <center><p class="p2"><?= $object->title; ?></p></center>
                        </div>
                        <hr>
                        <div class="object-solved">
                            <center><p class="p1">Situação do Objeto</p></center>
                            <center><p class="p2"><?= $object->solved; ?></p></center>
                        </div>
                        <hr>
                        <div class="object-type">
                            <center><p class="p1">Classificação do Objeto</p></center>
                            <center><p class="p2"><?= $object->type; ?></p></center>
                        </div>
                        <hr>
                        <div class="object-date">
                            <center><p class="p1">Data do Ocorrido</p></center>
                            <center><p class="p2"><?= $object->date; ?></p></center>
                        </div>
                        <hr>
                        <div class="object-date">
                            <center><p class="p1">Publicado Por</p></center>
                            <center><p class="p2"><?= $object->user->username; ?></p></center>
                        </div>
                        <center><?= $this->Html->link(__('Ver'), ['controller' => 'Objects', 'action' => 'single', $object->id], ['class' => 'btn btn-primary']) ?></center>
                    </div>
                </div>
                <?php $post_counter++; } ?>
            </div>
            </div>  
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
  </body>
</html>
