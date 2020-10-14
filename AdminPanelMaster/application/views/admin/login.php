<!DOCTYPE html> 
<html lang="en-US">
  <head>
    <title>Facturación Electronica</title>
    <meta charset="utf-8">
    <link href="<?php echo base_url(); ?>assets/css/admin/global.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div class="container login">
      <?php 
      $attributes = array('class' => 'form-signin');
      echo form_open('admin/login/validate_credentials', $attributes);
      echo '<h2 class="form-signin-heading">Inicio Sesión</h2>';
      echo form_input('user_name', '', 'placeholder="Usuario"');
      echo form_password('password', '', 'placeholder="Contraseña"');
      if(isset($message_error) && $message_error){
          echo '<div class="alert alert-error">';
            echo '<a class="close" data-dismiss="alert">×</a>';
            //echo '<strong>Oh snap!</strong> Change a few things up and try submitting again.';
          echo '<strong>Algo esta mal!</strong>Usuario o contraseña incorrecta.';
          echo '</div>';             
      }
      echo "<br />";
      //echo anchor('admin/signup', 'Signup!');
      echo "<br />";
      echo "<br />";
      echo form_submit('submit', 'Iniciar', 'class="btn btn-large btn-primary"');
      echo form_close();
      ?>      
    </div><!--container-->
    <script src="<?php echo base_url(); ?>assets/js/jquery-1.7.1.min.js"></script>
    <script src="<?php echo base_url(); ?>assets/js/bootstrap.min.js"></script>
  </body>
</html>    
    