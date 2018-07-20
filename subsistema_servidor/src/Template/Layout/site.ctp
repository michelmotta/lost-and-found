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

    <?= $this->Html->script('jquery-3.2.1.min.js'); ?>

    <?= $this->Html->css('main.css') ?>

    <?= $this->Html->css('animate.css') ?>

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
                <?= $this->Flash->render() ?>
          	    <?= $this->fetch('content') ?>
            </div>
        </div>
    </div>
    
    <!-- Essential javascripts for application to work-->
    <?= $this->Html->script('popper.min.js'); ?>
    <?= $this->Html->script('bootstrap.min.js'); ?>
    <?= $this->Html->script('main.js'); ?>

    <!-- The javascript plugin to display page loading on top-->
    <?= $this->Html->script('plugins/pace.min.js'); ?>
    <?= $this->Html->script('plugins/bootstrap-notify.min.js'); ?>
  </body>
</html>
