        <div class="container top">
      <label for="Usuario" style="font-size:12px">Usuario:<?php echo $usuario=$this->session->userdata('user_name');?></label> 
      <ul class="breadcrumb">
        <li>
          <a href="<?php echo site_url("admin"); ?>">
            <?php echo ucfirst($this->uri->segment(1));?>
          </a> 
          <span class="divider">/</span>
        </li>
        <li class="active">
          <?php echo ucfirst($this->uri->segment(2));?>
        </li>
      </ul>

      <div class="page-header users-header">
        <h2>
          <!--<label for="Usuario" style="font-size:16px"><?php echo ucfirst($this->uri->segment(2));?></label>-->
            <label for="Usuario" style="font-size:16px">Documentos</label>
          <!--<a  href="<?php echo site_url("admin").'/'.$this->uri->segment(2); ?>/add" class="btn btn-success">Add a new</a>-->
        </h2>
      </div>
      
      <div class="row">
        <div class="span12 columns">
          <div class="well">
              
              Fecha inicio:<input type="date" name="fecha" >
              
              Fecha fin:<input type="date" name="fecha" >
           
            <?php
           
            $attributes = array('class' => 'form-inline reset-margin', 'id' => 'myform');
           
            //save the columns names in a array that we will use as filter         
            $options_documents = array();    
            foreach ($documents as $array) {
              foreach ($array as $key => $value) {
                $options_documents[$key] = $key;
              }
              break;
            }

            echo form_open('admin/documents', $attributes);
     
              echo form_label('Buscar:', 'search_string');
              echo form_input('search_string', $search_string_selected);

              echo form_label('Ordenado por:', 'order');
              echo form_dropdown('order', $options_documents, $order, 'class="span2"');

              $data_submit = array('nombres' => 'mysubmit', 'class' => 'btn btn-primary', 'value' => 'IR');

              $options_order_type = array('Asc' => 'Asc', 'Desc' => 'Desc');
              echo form_dropdown('order_type', $options_order_type, $order_type_selected, 'class="span1"');

              echo form_submit($data_submit);

            echo form_close();
            ?>

          </div>

          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <!--<th class="header">#</th>-->
                  <th class="yellow header headerSortDown">Fecha</th>
                <th class="yellow header headerSortDown">Nombres</th>
                  <th class="yellow header headerSortDown">Ruc/Cedula</th>
                  <th class="yellow header headerSortDown">Clave</th>
                  <th class="yellow header headerSortDown">Documento</th>
                  <th class="yellow header headerSortDown">Valor</th>
                  <th class="yellow header headerSortDown">Descargar Pdf</th>
                  <th class="yellow header headerSortDown">Descargar Xml</th>
              </tr>
            </thead>
            <tbody>
              <?php
              foreach($documents as $row)
              {
                  echo '<tr>';
                //echo '<td>'.$row['id'].'</td>';
                  echo '<td style="font-size:11px">'.$row['fecha'].'</td>';
                  echo '<td style="font-size:11px">'.$row['nombres'].'</td>';
                  echo '<td style="font-size:11px">'.$row['ruc_cedula'].'</td>';
                  echo '<td style="font-size:11px">'.$row['clave_acceso'].'</td>';
                  echo '<td style="font-size:11px">'.$row['tipo_documento'].'</td>';
                  echo '<td style="font-size:11px">'.$row['valor'].'</td>';
                  //echo '<td>'.$row['pdf'].'</td>';
                 echo '<td style="font-size:11px"> <a href="'.base_url('DocumentosDigitales/Facturas/'.$row['pdf']).'"  download="'.$row['pdf'].'".pdf"><img src="'.base_url("assets/fact/pdf.jpg").'" height="70px" width="70px"> </a></td>';
                  echo '<td style="font-size:11px"> <a href="'.base_url('DocumentosDigitales/Facturas/'.$row['xml']).'"  download="'.$row['xml'].'".xml"><img src="'.base_url("assets/fact/xml.jpg").'" height="70px" width="70px"> </a></td>';
                //echo '<td class="crud-actions">
                //  <a href="'.site_url("admin").'/documents/update/'.$row['id'].'" class="btn btn-info">view & edit</a>  
                //  <a href="'.site_url("admin").'/documents/delete/'.$row['id'].'" class="btn btn-danger">delete</a>
                //</td>';
                echo '</tr>';
              }
              ?>      
            </tbody>
          </table>

          <?php echo '<div class="pagination">'.$this->pagination->create_links().'</div>'; ?>

      </div>
    </div>