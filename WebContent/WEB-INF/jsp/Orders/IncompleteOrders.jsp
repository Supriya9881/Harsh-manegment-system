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
</head>
<body>

	<div class="container">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-10 col-md-12 col-sm-12 col-12">
								<h4>Incomplete Orders</h4>
							</div>
							<div class="col-xl-2 col-md-12 col-sm-12 col-12">
								<!-- <h4><a href="printAllOrderReport.html" target="_blank">Print PDF</a></h4> -->
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<!--  BEGIN CONTENT AREA  -->
						<div class="table">
							<table id="example" class="display" style="width: 100%">
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
									<c:forEach var="v" items="${IncompleteOrdersList}">
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
											<td class="text-center"><a href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
												<span class="text-danger">${v.orderStatus}</span></a></td>
										</tr>
										
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th style="display: none;">Customer Id</th>
										<th>Customer Name </th>
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
					<button type="submit" class="close" data-dismiss="modal" onclick="onCloseBtnClick()"
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
									style="color: #000;" readonly name="cId2" value="${customerId}">
							</div>
							<div class="col-xl-6">
								<label>Paid</label> <input class="form-control form-control-sm"
									type="text" id="PaidAmt" autocomplete="off" readonly
									onclick="onPaidClick()" style="color: #000;" name=""
									value="0" onchange="onAmtPaidChange()">
							</div>
							<div class="col-xl-6">
								<label>Remaining Amt</label> <input
									class="form-control form-control-sm" type="text" id="RemAmt"
									style="color: #000;" readonly name=""> 
									<input type="hidden" id="oId2" name="oId2">
							</div>
							<div class="col-xl-6">
								<label>Now Pay</label> <input class="form-control form-control-sm"
									type="text" id="nowPaidAmt" autocomplete="off" required
									onclick="onNowPayClick()" style="color: #000;" name="nowPaidAmt"
									value="" onkeyup="onNowPayChange()" >
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" onclick="onCloseBtnClick()">
						<i class="flaticon-cancel-12"></i> Discard
					</button>
					<button type="submit" class="btn btn-primary"
						name="btnPayRemAmt" id="btnPayRemAmt" disabled>
						Pay</button>
				</div>
			  </form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onNowPayClick() {
			document.getElementById("nowPaidAmt").select();
		}
		function onCloseBtnClick(){
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
		if (Paid > BalanceAmt) {
			alert("Enter Amount Less than or Equal to " + BalanceAmt);
			document.getElementById("nowPaidAmt").focus();
			document.getElementById("nowPaidAmt").select();
		}else{
			document.getElementById("btnPayRemAmt").disabled = false;
		}
	}
	</script>
	<script type="text/javascript">
		function onPayAmtClick(a){
			$("#cId2").val(a.children[0].textContent);
			$("#oId2").val(a.children[3].textContent);
			$("#TotAmt").val(a.children[7].textContent);
			$("#PaidAmt").val(a.children[8].textContent);
			$("#RemAmt").val(a.children[9].textContent);
		}
	</script>
</body>
</html>