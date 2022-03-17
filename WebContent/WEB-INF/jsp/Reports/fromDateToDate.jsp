<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(function() {
		$("#dateStart").datepicker({
			dateFormat : "dd-mm-yy",
			changeMonth : true,
			changeYear : true
		}).val()
		$("#dateEnd").datepicker({
			dateFormat : "dd-mm-yy",
			changeMonth : true,
			changeYear : true
		}).val()
	});
</script>

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
</head>
<body>
	<div class="container pt-0">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-content widget-content-area">
						<form action="getFromToDateReport.html"
							method="post">
							<div class="form-row">
								<div class="col-md-4 mb-4">
									<label for="mobileNo">Select From Date</label> <input
										id="dateStart" value="${fromDate}"
										class="form-control flatpickr flatpickr-input active"
										type="text" placeholder="Select From Date.."
										style="color: green;" required name="dateStart" autocomplete="off">
								</div>
								<div class="col-md-4 mb-4">
									<label for="mobileNo">Select To Date</label> <input
										id="dateEnd" value="${toDate}"
										class="form-control flatpickr flatpickr-input active"
										type="text" placeholder="Select To Date.."
										style="color: green;" required name="dateEnd" autocomplete="off"
										onchange="">
								</div>
								<div class="col-md-4 pt-4">
									<button for="validationCustom05" onclick="onGetReportClick()"
										class="form-control btn btn-primary mb-2 mr-2" type="submit"
										required>
										<svg width="1em" height="1em" viewBox="0 0 16 16"
											class="bi bi-inbox" fill="currentColor"
											xmlns="http://www.w3.org/2000/svg">
                                                <path
												fill-rule="evenodd"
												d="M4.98 4a.5.5 0 0 0-.39.188L1.54 8H6a.5.5 0 0 1 .5.5 1.5 1.5 0 1 0 3 0A.5.5 0 0 1 10 8h4.46l-3.05-3.812A.5.5 0 0 0 11.02 4H4.98zm9.954 5H10.45a2.5 2.5 0 0 1-4.9 0H1.066l.32 2.562a.5.5 0 0 0 .497.438h12.234a.5.5 0 0 0 .496-.438L14.933 9zM3.809 3.563A1.5 1.5 0 0 1 4.981 3h6.038a1.5 1.5 0 0 1 1.172.563l3.7 4.625a.5.5 0 0 1 .105.374l-.39 3.124A1.5 1.5 0 0 1 14.117 13H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .106-.374l3.7-4.625z" />
                                              </svg>
										Get Report
									</button>
								</div>
							</div>
						</form>

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
										<!--  <th style="display: none;">Ready Qty</th>
										<th style="display: none;">Not Ready Qty</th>
										<th style="display: none;">Delivered Qty</th> -->
										<th style="display: none;">Order Amount</th>
										<th style="display: none;">Paid</th>
										<!-- <th style="display: none;">Actual Paid</th> -->
										<th style="display: none;">Remaining</th> 
										<!-- <th class="text-center">Order Details</th> --> 
									</tr>
								</thead>
								<!-- AllOrderList -->
								<tbody>
									<c:forEach var="v" items="${AllOrderList}">
										<tr class="text-center">
											<td style="display: none;"><c:out value="${v.cId}"></c:out></td>
											<td class="text-left"><c:out value="${v.cName}"></c:out></td>
											<td><c:out value="${v.invoiceNo}"></c:out></td>
											<td style="display: none;"><c:out value="${v.orderId}"></c:out></td>
											<td><c:out value="${v.orderDate}"></c:out></td>
											<td><c:out value="${v.dueDate}"></c:out></td>
											<td><c:out value="${v.totalQuantity}"></c:out></td>
											 <%-- <td style="display: none;"><c:out value="${v.ReadyQty}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.NotReadyQty}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.DeliveredQty}"></c:out></td> --%>
											<td style="display: none;"><c:out
													value="${v.totalAmount}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.amountPaid}"></c:out></td>
											<%-- <td style="display: none;"><c:out
													value="${v.actualPaid}"></c:out></td> --%>
											<td style="display: none;"><c:out
													value="${v.amountRemaining}"></c:out></td> 
											<%--<td><button class="btn btn-success mb-4 mr-2"
													onclick="onBtnOrderDetailsClick(this.parentNode.parentNode)"
													id="DeliverOnlyReadyCloths" data-toggle="modal"
													data-target="#exampleModal1" type="button" required>
													<b>Order Details</b>
												</button></td>--%>
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
										<!--  <th style="display: none;">Ready Qty</th>
										<th style="display: none;">Not Ready Qty</th>
										<th style="display: none;">Delivered Qty</th> -->
										<th style="display: none;">Order Amount</th>
										<th style="display: none;">Paid</th>
										<!-- <th style="display: none;">Actual Paid</th> -->
										<th style="display: none;">Remaining</th> 
										<!-- <th class="text-center">Order Details</th> --> 
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