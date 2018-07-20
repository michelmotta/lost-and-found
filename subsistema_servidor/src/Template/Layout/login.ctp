<?php
/**
 * CakePHP(tm) : Rapid Development Framework (https://cakephp.org)
 * Copyright (c) Cake Software Foundation, Inc. (https://cakefoundation.org)
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright     Copyright (c) Cake Software Foundation, Inc. (https://cakefoundation.org)
 * @link          https://cakephp.org CakePHP(tm) Project
 * @since         0.10.0
 * @license       https://opensource.org/licenses/mit-license.php MIT License
 */

?>
<!DOCTYPE html>
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
<body>

    <section class="material-half-bg">
        <div class="cover"></div>
    </section>

    <section class="login-content">
      <div class="logo">
        <h1>App Server</h1>
      </div>
      <?= $this->Flash->render(); ?>
      <?= $this->fetch('content'); ?>
    </section>

    <!-- Essential javascripts for application to work-->
    <?= $this->Html->script('popper.min.js'); ?>
    <?= $this->Html->script('bootstrap.min.js'); ?>
    <?= $this->Html->script('main.js'); ?>

    <!-- The javascript plugin to display page loading on top-->
    <?= $this->Html->script('plugins/pace.min.js'); ?>
    <?= $this->Html->script('plugins/bootstrap-notify.min.js'); ?>

    <script type="text/javascript">
        // Login Page Flipbox control
        $('.login-content [data-toggle="flip"]').click(function() {
            $('.login-box').toggleClass('flipped');
            return false;
        });
    </script>

</body>
</html>
