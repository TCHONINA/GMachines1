
<%@page import="beans.Machine"%>
<%@page import="service.MachineService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion des Salles</title>
<%@include file="template/css.jsp"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="jquery/machinesalle.js" type="text/javascript"></script>
</head>
<body>
	<div id="preloader" style="display: none;">
		<div class="sk-three-bounce">
			<div class="sk-child sk-bounce1"></div>
			<div class="sk-child sk-bounce2"></div>
			<div class="sk-child sk-bounce3"></div>
		</div>
	</div>

	<div id="main-wrapper" class="show">
		<%@include file="template/header.jsp"%>
		<%@include file="template/side_bare.jsp"%>
		<div class="content-body">
			<div class="container-fluid">
				<div class="row page-titles mx-0">
					<div class="col-sm-6 p-md-0">
						<div class="welcome-text">
							<h4>Bienvenue</h4> 
							<p class="mb-0">Nous sommes dans votre service</p>
						</div>
					</div>
					<div
						class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="javascript:void(0)">Acceuil</a></li>
							<li class="breadcrumb-item active"><a
								href="javascript:void(0)">Services</a></li>
						</ol>
					</div>
				</div>
				<!-- row -->
				<div class="row">
					<div class="col-xl-12 col-xxl-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title"></h4>
							</div>
							<div class="card-body">
								<form action="#" id="step-form-horizontal"
									class="step-form-horizontal">
									<div>
										<h4>Liste des Salles</h4>
										<section>
											<div class="row">
												<div class="col-lg-6 mb-4">
													<div class="form-group">
														<label class="text-label">Code</label> <input
															type="text"  id="code"
															class="form-control" required>
													</div>
												</div>
												
												</div>

										</section>

									</div>
									<button type="button" id="find" class="btn btn-primary  "
										style="float: right;">find</button>

								</form>

							</div>
						</div>
					</div>
				</div>
				<div class="row page-titles mx-0">
					<div class="col-sm-6 p-md-0">
						<div class="welcome-text">
							<h4>Hi, welcome back!</h4>
							<span class="ml-1">Datatable</span>
						</div>
					</div>
					<div
						class="col-sm-6 p-md-0 justify-content-sm-end mt-2 mt-sm-0 d-flex">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="javascript:void(0)">Table</a></li>
							<li class="breadcrumb-item active"><a
								href="javascript:void(0)">Datatable</a></li>
						</ol>
					</div>
				</div>
				<!-- row -->


				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">Basic Datatable</h4>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table id="example" class="display" style="min-width: 845px">
										<thead>
											<tr>
												<th>Id</th>
												<th>Réference</th>
												<th>Marque</th>
												<th>Date d'achat</th>
												<th>Prix d'achat</th>
												<th>Salle</th>
												
											</tr>
										</thead>
										<tbody id="contenu">

										</tbody>
										<tfoot>
											<tr>
												<th>Id</th>
												<th>Réference</th>
												<th>Marque</th>
												<th>Date d'achat</th>
												<th>Prix d'achat</th>
												<th>Salle</th>
												
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>



	<%@include file="template/footer.jsp"%>
	<%@include file="template/js.jsp"%>
</body>
</html>