<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\reportes.css"></jsp:include>
</style>
</head>
<body  style="background: #1c232e">

	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<p class="usuario">¡Hola, Cristian Martinez!</p>
<%! Date md = new Date();%>
<%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
<%! int solicitados = 0; %>
<%! int aprobados = 0; %>
	<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-8 text-center mt-5 mb-3">
			<div class="card mb-4">
		        <div class="card-header" style="background-color:#033649; text-align: center">
		            <i class="fas fa-table me-1"></i><h3 style="color: #fff">Reportes</h3>
		        </div>
		        <div class="card-body">
		        	<div class="filters-content">
		        		<form method="post" action="ReportesServlet">
			        	<div class="item">
			          		<h5>Fecha de inicio</h5>
			          		<input class="form-control" name="desde" type="date" max=<%= sdf.format(md) %> />
			          	</div>
			          	<div class="item">
			          		<h5>Fecha de Fin</h5>
			          		<input class="form-control" name="hasta" type="date" max=<%= sdf.format(md) %> />
			          	</div>
			          	<div class="mt-4 botoner">
		            	<a href="#">
		            		<button class="sub" type="submit" >Generar Reporte</button>
		            	</a>
		        	</div>
		        		</form>
		        	</div>
		        	<div class="row mt-5">
		        		<div class="col-md-6 item text-center">
		        			<h5 class="item__header">Cantidad de usuarios registrados</h5>
		        			<hr class="line-bottom"/>
		        			<p>0</p>
		        		</div>
		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Cantidad de Prestamos Solicitados</h5>
		        			<hr class="line-bottom"/>
		        			<%if(request.getAttribute("solicitados") != null){
		        				  solicitados = (Integer)request.getAttribute("solicitados");
		        			%>
		        				<p><%=solicitados %></p>
		        			<% }  else { %>
		        				<p>0</p>
		        			<%} %>
		        		</div>
		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Cantidad de Prestamos Aprobados</h5>
		        			<hr class="line-bottom"/>
		        			<%if(request.getAttribute("aprobados") != null){
		        				aprobados = (Integer)request.getAttribute("aprobados");
		        			%>
		        				<p><%=aprobados %></p>
		        			<% }  else { %>
		        				<p>0</p>
		        			<%} %>
		        		</div>
		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Promedio de Prestamos</h5>
		        			<hr class="line-bottom"/>
		        			<p>0</p>
		        		</div>
		        		<div class="col-md-12 item">
		        			<h5 class="item__header">Monto Total de Dinero Gestionado</h5>
		        			<hr class="line-bottom"/>
		        			<p>0</p>
		        		</div>
<!-- 		        		<div class="col-md-6 item">
		        			<h5 class="item__header">Promedio Monto Solicitado en Prestamos</h5>
		        			<hr class="line-bottom"/>
		        			<p>0</p>
		        		</div> -->
		        		<div></div>
		        	
		        	</div>

		    </div>
		</div>
	</div>
</div>


	<jsp:include page="./includes/Footer.jsp"></jsp:include>
	
	
</body>
</html>