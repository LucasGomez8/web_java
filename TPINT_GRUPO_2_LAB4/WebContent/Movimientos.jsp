<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body>

	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<p class="usuario">¡Hola, Cristian Martinez!</p>
	<div class="container-fluid" style="padding:3vh;">
		<div class="card mb-4">
			<div class="card-header" style="background-color: #033649;">
				<i class="fas fa-table me-1"></i>
				<h6>Movimientos</h6><!-- 
				<nav class="navbar navbar-expand bg-light">
					<div class="container-fluid">
						<div id="navbarSupportedContent">
							<ul class="navbar-nav me-auto">
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Periodo</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Hoy</a></li>
										<li><a class="dropdown-item" href="#">Ayer</a></li>
										<li><a class="dropdown-item" href="#">Ultimo mes</a></li>
										<li><a class="dropdown-item" href="#">Ultimo año</a></li>
										<li><a class="dropdown-item" href="#">Desde el principio</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Operacion</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Todos</a></li>
										<li><a class="dropdown-item" href="#">Ingreso</a></li>
										<li><a class="dropdown-item" href="#">Egreso</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">Cuenta</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Todas</a></li>
										<li><a class="dropdown-item" href="#">C C. A A. <b>04123021312408124</b></a></li>
										<li><a class="dropdown-item" href="#">C. C. <b>03123021312408124</b></a></li>
									</ul>
								</li>
							</ul>
						</div>
						<form class="d-flex" role="search">
							<input class="form-control me-2" type="search"
								placeholder="Buscar" aria-label="Buscar">
							<button class="btn btn-primary" style="background-color: #036564" type="submit">Buscar</button>
						</form>
					</div>
				</nav> -->
				
				
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
				<table id="dt-filter-select"
					class="display responsive table table-striped" style="width: 100%">
					<thead>
						<tr>
							<th>Descripcion</th>
							<th>Cuenta</th>
							<th>Fecha</th>
							<th>Importe</th>
						</tr>
					</thead>
					<tbody>
						<%for(int i=0; i<10; i++){ %>
						<tr>
							<td>Descripcion</td>
							<td>Cuenta</td>
							<td>Fecha</td>
							<td>Importe</td>
						</tr>

						<%} %>
					</tbody>
					
					<tfoot>
						<tr>
							<th>Descripcion</th>
							<th>Cuenta</th>
							<th>Fecha</th>
							<th>Importe</th>
						</tr>
					</tfoot>
				</table>
				
				<a class="btn btn-primary" style="background-color: #036564"
					href="#">Atras</a>
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