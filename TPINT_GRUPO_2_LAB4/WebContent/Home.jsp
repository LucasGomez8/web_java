<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\home.css"></jsp:include>
</style>
<title>Home</title>
</head>
<body>
	<!-- INCLUYE NAVBAR -->
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	<!-- INICIO CONTENIDO -->
	
	<div class="container home-content">
		<div class="row">
			<div class="col-md-12">
				<div class="home-title text-center">
					<h1>Bienvenido al Sistema de Gestion del Banco G.R.G.M.R</h1>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="item">
							<h4>Prestamos que son para vos</h4>
							<p>Un préstamo para lo que necesites, en cuotas a tasa fija y en pesos.</p>
							<a href="SolicitarPrestamo.jsp">Solicitá un Prestamo</a>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="item">
							<h4>Transferencias sin limites</h4>
							<p>Podés enviar y recibir plata desde cualquier cuenta. Gratis y en el acto.</p>
							<a href="SolicitarPrestamo.jsp">Hacer una transferencia</a>
						</div></div>
						
					<div class="col-md-4">
						<div class="item">
							<h4>Gestioná tus movimientos</h4>
							<p>Consultá tus resúmenes de cuentas de manera simple y cómoda.</p>
							<a href="Movimientos.jsp">Ver Movimientos</a>
						</div>
					</div>
		
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- FIN CONTENIDO -->
	
	<!-- INCLUYE FOOTER -->
	<jsp:include page="./includes/Footer.jsp"></jsp:include>
</body>
</html>