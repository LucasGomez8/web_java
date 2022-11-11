<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitudes de Préstamos</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
		<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body>

	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<p class="usuario">¡Hola, Cristian Martinez!</p>
	<div class="container-fluid" style="padding:3vh;">
	<div class="card mb-4">
        <div class="card-header" style="background-color:#033649;">
            <i class="fas fa-table me-1"></i><h6>Solicitudes de préstamos pendientes</h6>
            
            
              <div class="row" style="width:100%;height:100%">
  
				    <div class="col">
					    <input class="form-control" type="number" placeholder="Importe Minimo: $..." value="" />
		            	            	
				    </div>
				    
				    <div class="col col-background">	
				    	<input class="form-control" type="number" placeholder="Importe Maximo: $..." value="" />	    	
				    </div>
				    
				    				    <div class="col col-background">
                    	<a class="btn btn-success" style="background-color:#036564" href="ABMMarcas?Type=a">Buscar</a>
                	</div>
				    
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>
				    
				    <div class="col col-background"></div>

    
  			</div>
  			 						
                              
        </div>
        <div class="card-body">
            <table id="dt-filter-select" class="table" style="width: 100%">
                <thead>
                    <tr>
                        <th>Número de Solicitud</th>
                        <th>ID Usuario</th>   
                        <th>Nombre y Apellido</th>
                        <th>Importe Solicitado</th>   
                        <th>Plazo de Pago (Meses)</th>
                        <th>Detalles</th>   
                        <%--<th>Stock</th>--%>
                        <th>Aprobación</th>

                    </tr>
                </thead>
                <tbody>
                    <%for(int i=0; i<10; i++){ %>
                    <tr>
                        <td>Número de Solicitud</td>
                        <td>ID Usuario</td>
                        <td>Nombre y Apellido</td>   
                        <td>Importe Solicitado</td> 
                        <td>Plazo de Pago (Meses)</td>
                        <td>
                        	<a href="#" class="btn">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-up-right" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8.636 3.5a.5.5 0 0 0-.5-.5H1.5A1.5 1.5 0 0 0 0 4.5v10A1.5 1.5 0 0 0 1.5 16h10a1.5 1.5 0 0 0 1.5-1.5V7.864a.5.5 0 0 0-1 0V14.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z" />
                                    <path fill-rule="evenodd" d="M16 .5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793L6.146 9.146a.5.5 0 1 0 .708.708L15 1.707V5.5a.5.5 0 0 0 1 0v-5z" />
                                </svg>
							</a>
                        </td>
                        <td>
                            <a href="#" class="btn btn-outline-success btn-sm">Aprobar solicitud</a>
                            <a href="#" class="btn btn-outline-danger btn-sm">Rechazar solicitud</a>
                        </td>
                    </tr>
                    
                    <%} %>
                </tbody>
                
                					<tfoot>
						<tr>
						
                        <th>Número de Solicitud</th>
                        <th>ID Usuario</th>   
                        <th>Nombre y Apellido</th>
                        <th>Importe Solicitado</th>   
                        <th>Plazo de Pago (Meses)</th>
                        <th>Detalles</th>   


						</tr>
					</tfoot>
					
            </table>
            <a class="btn btn-primary" style="background-color:#036564" href="#">Atrás</a>
        </div>
    </div>
</div>

	<jsp:include page="./includes/Footer.jsp"></jsp:include>


			<script>
	$(document).ready(function () {
		  $('#dt-filter-select').dataTable({

		    initComplete: function () {
		      this.api().columns().every( function () {
		          var column = this;
		          var select = $('<select  class="browser-default custom-select form-control-sm"><option value="" selected>Buscar</option></select>')
		              .appendTo( $(column.footer()).empty() )
		              .on( 'change', function () {
		                  var val = $.fn.dataTable.util.escapeRegex(
		                      $(this).val()
		                  );

		                  column
		                      .search( val ? '^'+val+'$' : '', true, false )
		                      .draw();
		              } );

		          column.data().unique().sort().each( function ( d, j ) {
		              select.append( '<option value="'+d+'">'+d+'</option>' )
		          } );
		      } );
		  }
		  });
		});
	</script>
</body>
</html>