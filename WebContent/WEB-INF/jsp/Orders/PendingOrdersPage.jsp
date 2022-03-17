<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
<title>Perclean Laundry</title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.png" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="plugins/animate/animate.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="assets/css/tables/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<!-- END PAGE LEVEL STYLES -->
<!--  BEGIN CUSTOM STYLE FILE  -->
<link href="assets/css/scrollspyNav.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/components/custom-modal.css" rel="stylesheet"
	type="text/css" />
<!--  END CUSTOM STYLE FILE  -->

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL CUSTOM STYLES -->
<link rel="stylesheet" type="text/css"
	href="plugins/table/datatable/datatables.css">
<link rel="stylesheet" type="text/css"
	href="plugins/table/datatable/custom_dt_html5.css">
<link rel="stylesheet" type="text/css"
	href="plugins/table/datatable/dt-global_style.css">
<!--  END CUSTOM STYLE FILE  -->
</head>
<body>

	<div class="container">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-10 col-md-12 col-sm-12 col-12">
								<h4>All Pending Report</h4>
							</div>
							<div class="col-xl-2 col-md-12 col-sm-12 col-12">
								<h4>
									<a href="allPaindingReport.html" target="_blank">Print PDF</a>
								</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<!--  BEGIN CONTENT AREA  -->
						<div class="table">
							<table id="html5-extension" class="display" style="width: 100%">
								<thead>
									<tr>
										<th style="display: none;">Customer Id</th>
										<th>Customer Name</th>
										<th>Invoice No</th>
										<th style="display: none;">Order Id</th>
										<th>Order Date</th>
										<th>Due Date</th>
										<th>Total Qty</th>
										<th>Total Amount</th>
										<th>Paid</th>
										<th>Remaining</th>
										<th>Order Status</th>
									</tr>
								</thead>
								<!-- AllOrderList -->
								<tbody>
									<c:forEach var="v" items="${TodaysOrdersDetails}">
										<tr class="text-center">
											<td style="display: none;"><c:out value="${v.cId}"></c:out></td>
											<td class="text-left"><c:out value="${v.cName}"></c:out></td>
											<td><c:out value="${v.invoiceNo}"></c:out></td>
											<td style="display: none;"><c:out value="${v.orderId}"></c:out></td>
											<td><c:out value="${v.orderDate}"></c:out></td>
											<td><c:out value="${v.dueDate}"></c:out></td>
											<td><c:out value="${v.totalQuantity}"></c:out></td>
											<td><c:out value="${v.totalAmount}"></c:out></td>
											<td><c:out value="${v.amountPaid}"></c:out></td>
											<td><c:out value="${v.amountRemaining}"></c:out></td>
											<td class="text-center"><a
												href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
													<span class="text-danger">${v.orderStatus}</span>
											</a></td>
										</tr>

									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th style="display: none;">Customer Id</th>
										<th>Customer Name</th>
										<th>Invoice No</th>
										<th style="display: none;">Order Id</th>
										<th>Order Date</th>
										<th>Due Date</th>
										<th>Total Qty</th>
										<th>Total Amount</th>
										<th>Paid</th>
										<th>Remaining</th>
										<th>Order Status</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
	<!--   <script src="assets/js/libs/jquery-3.1.1.min.js"></script> -->
	<script src="bootstrap/js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="assets/js/app.js"></script>

	<script>
		$(document).ready(function() {
			App.init();
		});
	</script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!-- BEGIN PAGE LEVEL CUSTOM SCRIPTS -->
	<script src="plugins/table/datatable/datatables.js"></script>
	<!-- NOTE TO Use Copy CSV Excel PDF Print Options You Must Include These Files  -->
	<script
		src="plugins/table/datatable/button-ext/dataTables.buttons.min.js"></script>
	<script src="plugins/table/datatable/button-ext/jszip.min.js"></script>
	<script src="plugins/table/datatable/button-ext/buttons.html5.min.js"></script>
	<script src="plugins/table/datatable/button-ext/buttons.print.min.js"></script>
	<script>
		$('#html5-extension')
				.DataTable(
						{
							"scrollX" : true,
							dom : '<"row"<"col-md-12"<"row"<"col-md-6"B><"col-md-6"f> > ><"col-md-12"rt> <"col-md-12"<"row"<"col-md-5"i><"col-md-7"p>>> >',
							buttons : {
								buttons : [
								/* { extend: 'copy', className: 'btn' },
								{ extend: 'csv', className: 'btn' }, */
								{
									extend : 'excel',
									className : 'btn'
								}, {
									extend : 'print',
									className : 'btn'
								} ]
							},
							"oLanguage" : {
								"oPaginate" : {
									"sPrevious" : '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>',
									"sNext" : '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>'
								},
								"sInfo" : "Showing page _PAGE_ of _PAGES_",
								"sSearch" : '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
								"sSearchPlaceholder" : "Search...",
								"sLengthMenu" : "Results :  _MENU_",
							},
							"stripeClasses" : [],
							"lengthMenu" : [ 10, 20, 50 ],
							"pageLength" : 10
						});
	</script>
</body>
</html>