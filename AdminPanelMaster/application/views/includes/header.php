<!DOCTYPE html> 
<html lang="en-US">
<head>
  <title>Facturación Electronica</title>
  <meta charset="utf-8">
  <link href="<?php echo base_url(); ?>assets/css/admin/global.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-fixed-top">
	  <div class="navbar-inner">
	    <div class="container">
	      <!--<a class="brand">Project Name</a>-->
            <a class="brand"><?php echo '<img src="'.base_url("assets/img/logo.png").'" height="100px" width="90px">' ?> </a>
            <a class="brand">Facturacion Electronica</a>
            
            
	      <ul class="nav">
	        <!--<li <?php if($this->uri->segment(2) == 'products'){echo 'class="active"';}?>>
	          <a href="<?php echo base_url(); ?>admin/products">Products</a>
	        </li>
	        <li <?php if($this->uri->segment(2) == 'manufacturers'){echo 'class="active"';}?>>
	          <a href="<?php echo base_url(); ?>admin/manufacturers">Manufacturers</a>
	        </li>-->
              <li <?php if($this->uri->segment(2) == 'documents'){echo 'class="active"';}?>>
	          <a href="<?php echo base_url(); ?>admin/documents">Documentos</a>
	        </li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administración <b class="caret"></b></a>
	          <ul class="dropdown-menu">
	            <li>
	              <a href="<?php echo base_url(); ?>admin/logout">Cerrar Sesión</a>
	            </li>
	          </ul>
	        </li>
	      </ul>
	    </div>
	  </div>
	</div>	
