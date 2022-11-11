<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos Personales</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<style type="text/css">
	/*<jsp:include page="css\bootstrap.css"></jsp:include>*/
		<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body style="background-color:#1c232e;">

<!-- Incluye Nav -->
<jsp:include page="./includes/Navbar.jsp"></jsp:include>

<!-- Empieza Contenido -->
	<p class="usuario">¡Hola, Cristian Martinez!</p>
	<div class="container-fluid" style="padding:3vh;">
	<div class="card mb-4">
        <div class="card-header" style="background-color:#033649;">
            <i class="fas fa-table me-1"></i><h6>Información Personal</h6>              
        </div>
        
        <div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        <p style="color:#033649;">Datos Personales</p>
      </button>
    </h2>
    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
      <div class="accordion-body">
        
        <div class="card-body">
            <table id="tablaMarca" class="display responsive table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>CUIL</th>   
                        <th>Nombre y Apellido</th>
                        <th>Fecha Nacimiento</th>
                        <th>Usuario</th>
                        <th>Correo Electrónico</th>
                        <th>Dirección</th>
                        <th>Localidad</th>   
                        <th>País</th>
                    </tr>
                </thead>
                <tbody>
					<tr>
                    	<td>DNI</td>
                        <td>CUIL</td>   
                        <td>Nombre y Apellido</td>
                        <td>Fecha Nacimiento</td>
                        <td>Usuario</td>
                        <td>Correo Electrónico</td>
                        <td>País de Residencia</td>
                        <td>Localidad</td>   
                        <td>País</td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
            
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
        <p style="color:#033649;">Cuentas</p>
      </button>
    </h2>
    <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
      <div class="accordion-body">
      
                <div class="card-body">
            <table id="tablaMarca" class="display responsive table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>Numero de Cuenta</th>
                        <th>CBU</th>   
                        <th>Tipo de Cuenta</th>
                        <th>Fecha de Creación</th>
                        <th>Saldo</th>
                    </tr>
                </thead>
                <tbody>
					<tr>
						<td>Numero de Cuenta</td>
                        <td>CBU</td>   
                        <td>Tipo de Cuenta</td>
                        <td>Fecha de Creación</td>
                        <td>Saldo</td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
      
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
        <p style="color:#033649;">Préstamos</p>
      </button>
    </h2>
    <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
      <div class="accordion-body">   
        
                <div class="card-body">
            <table id="tablaMarca" class="display responsive table table-striped" style="width: 100%">
                <thead>
                    <tr>
                        <th>Préstamo Nro.</th>
                        <th>Fecha de Solicitud</th>   
                        <th>Importe Solicitado</th>
                        <th>Plazo de Pago (Meses)</th>
                        <th>Cuota Mensual</th>
                        <th>Cuotas Restantes</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
					<tr>
                        <td>Préstamo Nro.</td>
                        <td>Fecha de Solicitud</td>   
                        <td>Importe Solicitado</td>
                        <td>Plazo de Pago (Meses)</td>
                        <td>Cuota Mensual</td>
                        <td>Cuotas Restantes</td>
                        <td>Estado</td>					
                    </tr>
                    
                </tbody>
            </table>
        </div>
        
      </div>
    </div>
  </div>

</div>
        
    </div>
</div>
	<!-- Incluye Footer -->
	<jsp:include page="./includes/Footer.jsp"></jsp:include>
</body>
</html>