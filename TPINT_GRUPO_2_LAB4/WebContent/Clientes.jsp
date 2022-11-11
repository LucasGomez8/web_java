<%@page import="entidad.Rol"%>
<%@page import="daoImpl.RolDaoImpl"%>
<%@page import="dao.RolDao"%>
<%@page import="entidad.Usuario"%>
<%@page import="daoImpl.UsuarioDaoImpl"%>
<%@page import="dao.UsuarioDao"%>
<%@page import="entidad.TipoCuenta"%>
<%@page import="daoImpl.TipoCuentaDaoImpl"%>
<%@page import="dao.TipoCuentaDao"%>
<%@page import="entidad.Direccion"%>
<%@page import="daoImpl.DireccionDaoImpl"%>
<%@page import="dao.DireccionDao"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="dao.LocalidadDao"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Pais"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daoImpl.PaisDaoImpl"%>
<%@page import="dao.PaisDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clientes</title>
<style type="text/css">
<jsp:include page="css\bootstrap.css"></jsp:include>
<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body>
	
	<!-- VARIABLES DEL FRONT -->
	<%!Date md = new Date();%>
	<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");%>
	<%!PaisDao pDao = new PaisDaoImpl(); %>
	<%!ArrayList<Pais> lPais = pDao.readAll(); %>
	<%!LocalidadDao lDao = new LocalidadDaoImpl(); %>
	<%!ArrayList<Localidad> lLoca = lDao.readAll(); %>
	<%!DireccionDao dDao = new DireccionDaoImpl(); %>
	<%!ArrayList<Direccion> lDir = dDao.readAll(); %>
	<%!RolDao rDao = new RolDaoImpl(); %>
	<%!ArrayList<Rol> rList = rDao.readAll(); %>
	<%!UsuarioDao uDao = new UsuarioDaoImpl(); %>
	<%!ArrayList<Usuario> lClientes = uDao.ComboClientes(); %>	
	
	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	<p class="usuario">¡Hola, Cristian Martinez!</p>
	<div class="container-fluid" style="padding: 3vh;">
		<div class="card mb-4">
			<div class="card-header" style="background-color: #033649;">
				<i class="fas fa-table me-1"></i>
				<h6>Listado de clientes</h6>

			</div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-3">
						<a class="btn btn-success" data-bs-toggle="modal"
							data-bs-target="#altaClienteModal"
							style="background-color: #036564" href="">Alta de cliente</a>
					</div>
					 <div class="col-md-9 text-center">
                	<%
                	String msg = "";
                	if(request.getAttribute("usuarioCreado") != null){
                		msg=(String)request.getAttribute("usuarioCreado");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<%} %>
                	
                	<%if(request.getAttribute("eliminarUsuario") != null){
                		msg=(String)request.getAttribute("eliminarUsuario");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<% }%>
                	
                	<%if(request.getAttribute("edit") != null){
                		msg=(String)request.getAttribute("edit");
                	%>
                		<p class="msgServlet"><%=msg %></p>
                	<% }%>
                	
                </div>
				</div>

				<hr />
				<table id="dt-filter-select"
					class="display responsive table table-striped" style="width: 100%">
					<thead>
						<tr>
							<th>DNI</th>
							<th>ID Usuario</th>
							<th>Nombre y apellido</th>
							<th>Sexo</th>
							<th>Correo</th>
							<th>Estado</th>
							<th>&nbsp Acciones</th>

						</tr>
					</thead>
					<tbody>
						<%for(Usuario u : lClientes){ %>
						<tr>
							<td><%=u.getPersona().getDNI() %></td>
							<td><%=u.getPersona().getDNI() %></td>
							<td><%=u.getPersona().getNombre() + u.getPersona().getApellido() %></td>
							<td><%=u.getPersona().getSexo() %></td>
							<td><%=u.getPersona().getCorreo() %></td>
							<%if(u.isEstado()){%>
							
							<td>Activo</td>
							<% } else{ %>
							<td>Inactivo</td>
							<% } %>
							<td>
							<!-- DETALLE -->
							<a href="#" class="btn"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-box-arrow-up-right"
										viewBox="0 0 16 16"> <path fill-rule="evenodd"
										d="M8.636 3.5a.5.5 0 0 0-.5-.5H1.5A1.5 1.5 0 0 0 0 4.5v10A1.5 1.5 0 0 0 1.5 16h10a1.5 1.5 0 0 0 1.5-1.5V7.864a.5.5 0 0 0-1 0V14.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z" />
									<path fill-rule="evenodd"
										d="M16 .5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793L6.146 9.146a.5.5 0 1 0 .708.708L15 1.707V5.5a.5.5 0 0 0 1 0v-5z" />
									</svg></a>
									
									
							<!--  -->
							<a href="#" role="button" class="btn" data-bs-toggle="modal" value="<%=u.getPersona().getDNI()%>" onclick="mostrarDatos(<%=u.getPersona().getDNI()%>)" id="editar<%=u.getPersona().getDNI()%>" data-bs-target="#editarCliente<%=u.getPersona().getDNI()%>">
							<svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-pencil-square"
										viewBox="0 0 16 16"> <path
										d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
									<path fill-rule="evenodd"
										d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
									</svg></a>
									
									<div class="modal modal-xl fade" id="editarCliente<%=u.getPersona().getDNI()%>" tabindex="-1" aria-labelledby="editarClienteLabel" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-header" style="background-color:#036564">
												<h1 class="modal-title fs-5" style="color: #fff;" id="editarClienteLabel">
													Editar Cliente</h1>
													<%session.setAttribute("edit-client", u.getPersona().getDNI()); %>
												<button type="button" class="btn-close btn-close-white"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<form class="form" method="post" action="ClientesServlet">
													<div class="row">
													<h5>Datos de Cliente</h5>
														<div class="col-md-6 mb-3">
												<label class="form-label">Nombre</label>
												<input class="form-control" type="text" name="txtNombre" value="<%=u.getPersona().getNombre() %>" />
											</div>
											<div class="col-md-6 mb-3">
												<label class="form-label">Apellidos</label>
												<input class="form-control" type="text" name="txtApellido" value="<%=u.getPersona().getApellido()%>"/>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">DNI</label>
													<input class="form-control" id="edit-dni" type="text" name="txtDNI" value="<%=u.getPersona().getDNI()%>"/>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Sexo</label>
												<select class="form-select" name="sexo" id="sexo">
													<option value='-1'>Seleccione una opcion</option>
													<option value='M'>Masculino</option>
													<option value="F">Femenino</option>
													<option value="X">Otro</option>
												</select>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Fecha de Nacimiento</label>
												<div class="item">
													<input class="form-control" type="date"
														max=<%=sdf.format(md)%>  name="fecnac" id="fecnac"/>
												</div>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Correo</label>
												<div class="item">
													<input class="form-control" type="email"  name="email" value="<%=u.getPersona().getCorreo()%>"/>
												</div>
											</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Nacionalidad</label>
											<select class="form-select" id="nacionalidad" name="nacionalidad">
												<option value="-1">Seleccione una opcion</option>
												<%for(Pais p : lPais){ %>
													<option value="<%=p.getIdPais() %>"><%=p.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Localidad</label>
											<select class="form-select" name="localidad" id="localidad">
											<option value="-1">Seleccione una opcion</option>
												<%for(Localidad l : lLoca){ %>
													<option value="<%=l.getCP() %>"><%=l.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Direccion</label>
											<select class="form-select" name="direccion" id="direccion">
											<option value="-1">Seleccione una opcion</option>
												<%for(Direccion dir : lDir){ %>
													<option value="<%=dir.getIdDireccion() %>"><%=dir.getDescripicion() %></option>
												<% } %>
											</select>
										</div>
									<hr/>
									</div>
									<div class="row md-3">
									<h5>Datos de usuario</h5>
									<div class="col-md-4">
											<label class="form-label">Username</label>
											<input type="text" class="form-control" name="username" value="<%=u.getUser()%>">
										</div>
										<div class="col-md-4">
											<label class="form-label">Contraseña</label>
											<input type="text" class="form-control" name="password" value="<%=u.getPass()%>">
										</div>
										<div class="col-md-4 mb-4">
											<label class="form-label">Tipo de Usuario</label>
											<select class="form-select" name="tiporol" id="tiporol">
											<option value="-1">Seleccione una opcion</option>
												<%for(Rol rolcito : rList){ %>
													<option value="<%=rolcito.getIDRol()%>"><%=rolcito.getDescripcion()%></option>
												<% } %>
											</select>
										</div>
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Atras</button>
										<input type="submit" class="btn btn-primary" name="Modificar" value="Modificar"></input>
									</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
									
									
									

<!-- ELIMINAR -->

								 <a href="#" class="btn" data-bs-toggle="modal"
								data-bs-target="#deleteModal<%=u.getPersona().getDNI()%>"> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-trash-fill"
										viewBox="0 0 16 16"> <path
										d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
									</svg></a>
									
<div class="modal fade" id="deleteModal<%=u.getPersona().getDNI() %>" tabindex="-1" aria-labelledby="transferModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header " style="background-color:#036564">
				<h1 class="modal-title fs-5" id="transferModalLabel" style="color: #fff">Atencion!</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
			<form method="POST" action="ClientesServlet" class="form">
				<div class="col-md-12 text-center mb-3">
					<h3 style="color: #000">Advertencia!</h3>
					<h5>Estas a punto de dar de baja a <%=u.getPersona().getApellido() + ", " + u.getPersona().getApellido() %></h5>
					<input type="hidden" name="dni" value="<%=u.getPersona().getDNI()%>"/>;
				</div>
			<div class="col-md-12 text-center">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Atras</button>
				<button type="submit" name="eliminarUsuario" class="btn btn-primary">Dar de Baja</button>
			</div>
			</form>
		</div>
	</div>
</div>

								</td>
						</tr>

						<%} %>
					</tbody>

					<tfoot>
						<tr>
							<th>DNI</th>
							<th>Pais</th>
							<th>ID Usuario</th>
							<th>Nombre y apellido</th>
							<th>Sexo</th>
							<th>Correo</th>

						</tr>
					</tfoot>

				</table>
			</div>
		</div>
	</div>
	
	
<!-- MODALS -->
					<div class="modal modal-xl fade" id="altaClienteModal" tabindex="-1"
						aria-labelledby="transferModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header" style="background-color:#036564">
									<h1 class="modal-title fs-5" id="transferModalLabel" style="color: #fff;">Alta
										de cliente</h1>
									<button type="button" class="btn-close  btn-close-white" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
								<form action="ClientesServlet" method="post" class="form">
									<div class="row mb-3">
											<h5>Datos Personales</h5>
											<div class="col-md-4 mb-3">
												<label class="form-label">Nombre</label>
												<input class="form-control" type="text" name="txtNombres"/>
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">Apellidos</label>
												<input class="form-control" type="text" name="txtApellidos"/>
											</div>
											<div class="col-md-4 mb-3">
												<label class="form-label">DNI</label>
													<input class="form-control" type="text" name="txtDNI"/>
											</div>
											<div class="col-md-3">
												<label class="form-label">CUIL</label>
												<input type="text" class="form-control" name="cuil" />
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Sexo</label>
												<select class="form-select" id="sexo" name="sexo">
													<option value='-1'>Seleccione una opcion</option>
													<option value='M'>Masculino</option>
													<option value="F">Femenino</option>
													<option value="X">Otro</option>
												</select>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Fecha de Nacimiento</label>
												<div class="item">
													<input class="form-control" id="fecnac" type="date"
														max=<%=sdf.format(md)%>  name="fecnac"/>
												</div>
											</div>
											<div class="col-md-3 mb-3">
												<label class="form-label">Correo</label>
												<div class="item">
													<input class="form-control" type="email"  name="email"/>
												</div>
											</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Nacionalidad</label>
											<select class="form-select" name="nacionalidad" id="nacionalidad">
												<option value="-1">Seleccione una opcion</option>
												<%for(Pais p : lPais){ %>
													<option value="<%=p.getIdPais() %>"><%=p.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Localidad</label>
											<select class="form-select" name="localidad">
											<option value="-1">Seleccione una opcion</option>
												<%for(Localidad l : lLoca){ %>
													<option value="<%=l.getCP() %>"><%=l.getDescripcion() %></option>
												<% } %>
											</select>
										</div>
										<div class="col-md-4 mb-3">
											<label class="form-label">Direccion</label>
											<select class="form-select" name="direccion">
											<option value="-1">Seleccione una opcion</option>
												<%for(Direccion dir : lDir){ %>
													<option value="<%=dir.getIdDireccion() %>"><%=dir.getDescripicion() %></option>
												<% } %>
											</select>
										</div>
									</div>
									<hr/>
									<div class="row mb-4">
										<h5>Datos de Usuario</h5>
										<div class="col-md-4">
											<label class="form-label">Username</label>
											<input type="text" class="form-control" name="username">
										</div>
										<div class="col-md-4">
											<label class="form-label">Contraseña</label>
											<input type="text" class="form-control" name="password" >
										</div>
										<div class="col-md-4">
											<label class="form-label">Tipo de Usuario</label>
											<select class="form-select" name="tiporol">
											<option value="-1">Seleccione una opcion</option>
												<%for(Rol rolcito : rList){ %>
													<option value="<%=rolcito.getIDRol()%>"><%=rolcito.getDescripcion()%></option>
												<% } %>
											</select>
										</div>
									</div>
								<div class="col-md-12 text-center">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Atras</button>
									<!--  <button type="button" class="btn btn-primary">Dar de alta</button>  -->
									<input type="submit" class="btn btn-primary"
										value="Dar Alta" name="crearUsuario">
								</div>
						</form>

							</div>
						</div>
					</div>
				</div>
				
				
<!-- MODAL ELIMINAR -->


<!-- MODAL MODIFICAR -->

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
	
	
	function mostrarDatos(value) {
		  
		  let dni = value;
		  
			$.ajax({
				url: "EditarClienteServlet",
				type: "POST",
				data: {
					persona: dni
				},
				success: function(res) {
					let person = $.parseJSON(res);
					console.log(person);
					$("#localidad").val(person.Persona.Direccion.Localidad.CP);
					$("#nacionalidad").val(person.Persona.Pais.IdPais);
					$("#tiporol").val(person.Rol.IDRol);
					$("#direccion").val(person.Persona.Direccion.IdDireccion);
					$("#sexo").val(person.Persona.Sexo);
					
					let day = person.Persona.FechaNac.day;
					let month = person.Persona.FechaNac.month;
					if(day < 10){
						day = "0"+day;
					}
					if(month < 10){
						month= "0"+month;
					}
					
					let dt = person.Persona.FechaNac.year + "-" +month+ "-" + day;
					console.log("fecha:", dt);
					$("#fecnac").val(dt);
				}
				
			})
	  }
	

	</script>
</body>
</html>