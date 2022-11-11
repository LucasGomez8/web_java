<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferir</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\bootstrap.min.css"></jsp:include>
	<jsp:include page="css\global.css"></jsp:include>
</style>
</head>
<body  style="background: #1c232e">

	<jsp:include page="./includes/Navbar.jsp"></jsp:include>
	
	
	<p class="usuario">¡Hola, Cristian Martinez!</p>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-8 text-center mt-5 mb-3">
				<div class="card mb-4">
					<div class="card-header"
						style="background-color: #033649;">
						<h3 style="color: #fff">Transferir</h3>
					</div>
					<div class="card-body">
						<div class="filters-content">
							<div class="current-money-wrapper mb-3">
								<small>Tu saldo: $120000</small>
							</div>
							<p class="mb-0 text-start"><small>Desde:</small></p>
							<div class="form-check text-start">
								<input class="form-check-input" type="radio"
									name="flexRadioDefault" id="flexRadioDefault1">
									<label class="form-check-label" for="flexRadioDefault1">
									CC. AA.: <b>0041124124124124</b></label>
							</div>
							<div class="form-check text-start">
								<input class="form-check-input" type="radio"
									name="flexRadioDefault" id="flexRadioDefault2" checked>
								<label class="form-check-label" for="flexRadioDefault2">
									C. C.: <b>0031124124124124</b></label>
							</div>
							<p class="mb-0 text-start"><small>Hasta:</small></p>
							<div class="form-floating mb-3">
								<input class="form-control" id="floatingInputNumber"
									placeholder="Numero de cuenta a transferir"> <label
									for="floatingInputNumber">Numero de cuenta a transferir</label>
							</div>
							<div class="transfer-wrapper mt-5">
								<span>Ingrese el monto</span>
								<div class="d-flex justify-content-center pt-2">
									<div class="d-inline form-control w-auto">
										<span class="form-control d-inline w-auto border-0 px-10">$</span>
										<input class="form-control d-inline w-auto border-0 "
											id="formControlInputCurrency" type="number"
											placeholder="0.00">
									</div>
								</div>
							</div>
						</div>
						<div class="mt-4 botoner">
							<a href="#">
								<button class="btn btn-secondary">Atras</button>
							</a>
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#transferModal">
								Continuar</button>
							<div class="modal fade" id="transferModal" tabindex="-1"
								aria-labelledby="transferModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="transferModalLabel">
											Transferis a Leandro Ariel Gomez $25000</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<div class="after-money-wrapper mb-3">
												<small>Tu saldo restante sera: $95000</small>
											</div>
											<div class="dni-wrapper text-start">
												<span>DNI: </span><b>39000000</b>
											</div>
											<div class="account-wrapper text-start">
												<span>Numero de cuenta: </span><b>0400133245964058500345</b>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Atras</button>
											<button type="button" class="btn btn-primary">Transferir</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<jsp:include page="./includes/Footer.jsp"></jsp:include>
</body>
</html>