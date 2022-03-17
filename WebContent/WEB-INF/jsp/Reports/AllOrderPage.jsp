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
</head>
<body>

	<div class="container">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<!-- <div class="widget-header">
						<div class="row">
							<div class="col-xl-10 col-md-12 col-sm-12 col-12">
								<h4>All Orders</h4>
							</div>
							<div class="col-xl-2 col-md-12 col-sm-12 col-12">
								<h4><a href="printAllOrderReport.html" target="_blank">Print PDF</a></h4>
							</div>
						</div>
					</div> -->

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
										<th style="display: none;">Ready Qty</th>
										<th style="display: none;">Not Ready Qty</th>
										<th style="display: none;">Delivered Qty</th>
										<th style="display: none;">Order Amount</th>
										<th style="display: none;">Paid</th>
										<th style="display: none;">Actual Paid</th>
										<th style="display: none;">Remaining</th>
										<th class="text-center">Order Details</th>
										<th style="display: none;">Wallet</th>
										<th style="display: none;">Pay Now</th>
									</tr>
								</thead>
								<!-- AllOrderList -->
								<tbody>
									<c:set var="status1" value="0.0" />
									<c:forEach var="v" items="${AllOrderList}">
										<tr class="text-center">
											<td style="display: none;"><c:out value="${v.cId}"></c:out></td>
											<td class="text-left"><c:out value="${v.cName}"></c:out></td>
											<td><c:out value="${v.invoiceNo}"></c:out></td>
											<td style="display: none;"><c:out value="${v.orderId}"></c:out></td>
											<td><c:out value="${v.orderDate}"></c:out></td>
											<td><c:out value="${v.dueDate}"></c:out></td>
											<td><c:out value="${v.totalQuantity}"></c:out></td>
											<td style="display: none;"><c:out value="${v.ReadyQty}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.NotReadyQty}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.DeliveredQty}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.totalAmount}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.amountPaid}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.actualPaid}"></c:out></td>
											<td style="display: none;"><c:out
													value="${v.amountRemaining}"></c:out></td>
											<td><button class="btn btn-success mb-4 mr-2"
													onclick="onBtnOrderDetailsClick(this.parentNode.parentNode)"
													id="DeliverOnlyReadyCloths" data-toggle="modal"
													data-target="#exampleModal1" type="button" required>
													<b>Order Details</b>
												</button></td>
											<td style="display: none;"><c:out value="${v.cWallet}"></c:out></td>
											<c:if test="${v.amountRemaining == status1}">
												<td class="text-center" style="display: none;">
													<button class="btn btn-success mb-2 mr-2" disabled
														onclick="onPayAmtClick(this.parentNode.parentNode)"
														data-toggle="modal" data-target="#exampleModal"
														type="button">Pay</button>
												</td>
											</c:if>
											<c:if test="${v.amountRemaining > status1}">
												<td class="text-center" style="display: none;">
													<button class="btn btn-success mb-2 mr-2"
														onclick="onPayAmtClick(this.parentNode.parentNode)"
														data-toggle="modal" data-target="#exampleModal"
														type="button">Pay</button>
												</td>
											</c:if>
											<!--  <td class="text-center">
												<button class="btn btn-success mb-2 mr-2" onclick="onPayAmtClick(this.parentNode.parentNode)"
													data-toggle="modal" data-target="#exampleModal"
													type="button">Pay</button>
											</td>  -->
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
										<th style="display: none;">Ready Qty</th>
										<th style="display: none;">Not Ready Qty</th>
										<th style="display: none;">Delivered Qty</th>
										<th style="display: none;">Order Amount</th>
										<th style="display: none;">Paid</th>
										<th style="display: none;">Actual Paid</th>
										<th style="display: none;">Remaining</th>
										<th class="text-center">Order Details</th>
										<th style="display: none;">Wallet</th>
										<th style="display: none;">Pay Now</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="payRemAmt.html">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<b>Pay Remaining Amt of Order</b>
						</h5>
						<button type="submit" class="close" data-dismiss="modal"
							onclick="onCloseBtnClick()" aria-label="Close">
							<svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
								width="24" height="24" viewBox="0 0 24 24" fill="none"
								stroke="currentColor" stroke-width="2" stroke-linecap="round"
								stroke-linejoin="round" class="feather feather-x">
													<line x1="18" y1="6" x2="6" y2="18"></line>
													<line x1="6" y1="6" x2="18" y2="18"></line></svg>
						</button>

					</div>
					<div class="modal-body">
						<div class="n-chk">
							<label class="new-control new-radio radio-primary"> <input
								type="radio" class="new-control-input" name="custom_radio_1"
								checked id="CollectPayment"> <span
								class="new-control-indicator"></span>Collect Payment
							</label>
							<!-- onclick="document.getElementById('Paid').disabled = false" -->
							<div class="form-row">
								<div class="col-xl-6">
									<label>Total Amt</label> <input
										class="form-control form-control-sm" type="text" id="TotAmt"
										style="color: #000;" readonly name=""> <input
										class="form-control form-control-sm" type="hidden" id="cId2"
										style="color: #000;" readonly name="cId2"
										value="${customerId}">
								</div>
								<div class="col-xl-6">
									<label>Paid</label> <input class="form-control form-control-sm"
										type="text" id="PaidAmt" autocomplete="off" readonly
										onclick="onPaidClick()" style="color: #000;" name="" value="0"
										onchange="onAmtPaidChange()">
								</div>
								<div class="col-xl-6">
									<label>Remaining Amt</label> <input
										class="form-control form-control-sm" type="text" id="RemAmt"
										style="color: #000;" readonly name=""> <input
										type="hidden" id="oId2" name="oId2">
								</div>
								<div class="col-xl-6">
									<label>Now Pay</label> <input
										class="form-control form-control-sm" type="text"
										id="nowPaidAmt" autocomplete="off" required
										onclick="onNowPayClick()" style="color: #000;"
										name="nowPaidAmt" value="" onkeyup="onNowPayChange()">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal"
							onclick="onCloseBtnClick()">
							<i class="flaticon-cancel-12"></i> Discard
						</button>
						<button type="submit" class="btn btn-primary" name="btnPayRemAmt"
							id="btnPayRemAmt" disabled>Pay</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onNowPayClick() {
			document.getElementById("nowPaidAmt").select();
		}
		function onCloseBtnClick() {
			/* setTimeout(function(){
				   window.location.reload(1);
				}, 1000); */
			window.location.reload(1);
		}
	</script>

	<script type="text/javascript">
		function onNowPayChange() {
			var BalanceAmt = parseInt($("#RemAmt").val());
			var Paid = parseInt($("#nowPaidAmt").val());
			/* if (Paid > BalanceAmt) {
				alert("Enter Amount Less than or Equal to " + BalanceAmt);
				document.getElementById("nowPaidAmt").focus();
				document.getElementById("nowPaidAmt").value = '';
				document.getElementById("nowPaidAmt").select();
				document.getElementById("btnPayRemAmt").disabled = true;
			}else{
				document.getElementById("btnPayRemAmt").disabled = false;
			} */
			document.getElementById("btnPayRemAmt").disabled = false;
		}
	</script>
	<script type="text/javascript">
		function onPayAmtClick(a) {
			$("#cId2").val(a.children[0].textContent);
			$("#oId2").val(a.children[3].textContent);
			$("#TotAmt").val(a.children[7].textContent);
			$("#PaidAmt").val(a.children[8].textContent);
			$("#RemAmt").val(a.children[9].textContent);
		}
	</script>


	<script type="text/javascript">
		function onBtnOrderDetailsClick(a) {
			//var ClothTypeID = a.children[1].textContent;
			document.getElementById('invoiceNo').innerHTML = 'Invoice No : '
					+ a.children[2].textContent;
			document.getElementById('cName').innerHTML = 'Name : '
					+ a.children[1].textContent;
			document.getElementById('orderDate').innerHTML = 'Order Date : '
					+ a.children[4].textContent;
			document.getElementById('dueDate').innerHTML = 'Due Date : '
					+ a.children[5].textContent;

			document.getElementById('qty').innerHTML = 'Qty : '
					+ a.children[6].textContent;
			document.getElementById('totAmt').innerHTML = 'Tot : '
					+ a.children[10].textContent;
			document.getElementById('paid').innerHTML = 'Paid : '
					+ a.children[11].textContent;
			document.getElementById('rem').innerHTML = 'Rem : '
					+ a.children[13].textContent;

			var orderId = a.children[3].textContent;
			var invoiceNo = a.children[2].textContent;
			
			$("#inNoForDelete").val(invoiceNo);
			$("#oIdForDelete").val(orderId);
			
			$.getJSON('getOrderChiDetailNew.html', {
				orderId : orderId,
				invoiceNo : invoiceNo
			}).done(function(data) {
				$("#invoiceNo").val(data[0].invoiceNo);
				$("#orderId").val(data[0].orderId);
				
				
				var table = document.getElementById("orderDetailsTable");
				table.innerHTML = '';
				for (var i = 0; i < data.length; i++) {
					var row = table.insertRow(0);
					var cell1 = row.insertCell(0);
					var cell2 = row.insertCell(1);
					var cell3 = row.insertCell(2);
					var cell4 = row.insertCell(3);
					var cell5 = row.insertCell(4);

					cell1.innerHTML = data[i].clothType;
					cell2.innerHTML = data[i].serviceType;
					cell3.innerHTML = data[i].quantity;
					cell4.innerHTML = data[i].rate;
					cell5.innerHTML = data[i].amount;
				}
			});

		}
	</script>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<b>Order Details</b>
					</h5>
					<button type="submit" class="close" data-dismiss="modal"
						aria-label="Close">
						<svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
							width="24" height="24" viewBox="0 0 24 24" fill="none"
							stroke="currentColor" stroke-width="2" stroke-linecap="round"
							stroke-linejoin="round" class="feather feather-x">
													<line x1="18" y1="6" x2="6" y2="18"></line>
													<line x1="6" y1="6" x2="18" y2="18"></line></svg>
					</button>

				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xl-6">
							<label id="invoiceNo">invoice</label>
						</div>
						<div class="col-xl-6">
							<label id="cName">name</label>
						</div>
						<div class="col-xl-6">
							<label id="orderDate">orderDate</label>
						</div>
						<div class="col-xl-6">
							<label id="dueDate">dueDate</label>
						</div>
					</div>
					<div>
						<div class="table">
							<table id="example1" class="display nowrap" style="width: 100%">
								<thead>
									<tr>
										<th>Particular</th>
										<th>Service Type</th>
										<th>Quantity</th>
										<th>Rate</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody id="orderDetailsTable">

								</tbody>
								<!-- <tfoot>
								<tr>
									<th>Particular</th>
									<th>Service Type</th>
									<th>Quantity</th>
									<th>Rate</th>
									<th>Amount</th>
								</tr>
							</tfoot> -->
							</table>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-xl-3">
							<label id="qty">dueDate</label>
						</div>
						<div class="col-xl-3">
							<label id="totAmt">invoice</label>
						</div>
						<div class="col-xl-3">
							<label id="paid">name</label>
						</div>
						<div class="col-xl-3">
							<label id="rem">orderDate</label>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- <button class="btn btn-danger" data-dismiss="modal">
						<i class="flaticon-cancel-12"></i> Delete Order
					</button>  -->
					<form action="deleteThisOrder.html" target="_blank"> 
						<input type="hidden" id="oIdForDelete" name="oIdForDelete">
						<input type="hidden" id="inNoForDelete" name="inNoForDelete">
						<button class="btn btn-danger mb-2 mr-2" type="submit" name="btnDeleteOrder">
							Delete Order</button>
							
						<button class="btn btn-primary mb-2 mr-2" type="submit" name="btnGetDuplicateSlip">
							Generate Duplicate Recipts</button>
					</form>
					<!-- com.report AllReportsController -->
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
								{ extend: 'csv', className: 'btn' }, 
								{
									extend : 'excel',
									className : 'btn'
								}, {
									extend : 'print',
									className : 'btn'
								} */]
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
							"lengthMenu" : [ 7, 10, 20, 50 ],
							"pageLength" : 7
						});
	</script>
</body>
</html>