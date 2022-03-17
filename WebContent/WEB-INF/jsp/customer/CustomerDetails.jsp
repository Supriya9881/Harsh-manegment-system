<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
<title>Perclean Laundry</title>
</head>
<body onload="onBodyLoad()">
	<script type="text/javascript">
		function onBodyLoad() {
			/* if($('#CustomerId').val()!=''){
				$('#CustomerId').trigger('change');
			} */
			$("#payingAmount").val($("#cAmount").val());
			$("#cId2").val($("#cId").val());
			
			if($('#CustomerId').val()!=''){
				$('#CustomerId').trigger('change');
			}
			
			if(parseFloat($("#cAmount").val()) > 0){
				document.getElementById("makePayment").disabled = false;
			}
		}
		function onPaidClick() {
			document.getElementById("paidAmt").select();
		}
	</script>
	<script type="text/javascript">
		function onPaidChange() {
			var BalanceAmt = parseInt($("#payingAmount").val());
			var Paid = parseInt($("#paidAmt").val());
			/* if (Paid > BalanceAmt) {
				document.getElementById("msgPaidAmt").innerHTML = "Invalid Amount...";
				document.getElementById("paidAmt").value = "";
				document.getElementById("paidAmt").focus();
			} */
		}
	</script>
	<script type="text/javascript">
		function onPaidAmtKeyPress() {
			document.getElementById("msgPaidAmt").innerHTML = "";
		}
	</script>
	<div class="container-fluid pt-0">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="row col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Customer Details</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<c:forEach var="v" items="${CustomerInfo}">
							<div class="form-row"
								style="border-bottom: 2px solid #eeeeee; border-right: 2px solid #eeeeee;">
								<div class="col-md-2 mb-2">
									<label>Name</label>
									<div class="details" style="display: none;">
										<b><c:out value="${v.cId }"></c:out></b>
										<input id="cId" value="${v.cId }">
									</div>
									<div class="details">
										<b><c:out value="${v.cName}"></c:out></b>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Contact Number</label>
									<div class="details">
										<b><c:out value="${v.cMobile}"></c:out></b>
									</div>
								</div>
								<%-- <div class="col-md-2 mb-2">
									<label>Address</label>
									<div class="details">
										<c:out value="${v.cAddress}"></c:out>
									</div>
								</div> --%>
								<div class="col-md-2 mb-2">
									<label>Balance</label>
									<div class="details">
										<b><c:out value="${v.cAmount }"></c:out></b>
										<input id="cAmount" value="${v.cAmount }" type="hidden">
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Wallet</label>
									<div class="details">
										<b><c:out value="${v.cWallet }"></c:out></b>
										<input id="cWallet" value="${v.cWallet }" type="hidden">
									</div>
								</div>
								<div class="col-md-2 pt-2"></div>
								<div class="col-md-2 pt-2">
									<a href="GetOrderPage.html?customerId=${v.cId}"><button
											class="btn btn-success btn-block mb-4 mr-2">Get
											Order</button></a>
								</div>
								 <!-- <div class="col-md-2 pt-2">
									 <button class="btn btn-primary btn-block mb-4 mr-2" disabled id="makePayment"
											data-toggle="modal" data-target="#exampleModal">Make Payment</button> 
								</div>  -->
							</div>
						</c:forEach>
						<div class="container pt-3"
							style="border-bottom: 2px solid #ffffff;"></div>
						<div class="table">
							<table id="example" class="display" style="width: 100%">
								<thead>
									<tr>
										<th>Invoice No</th>
										<th style="display: none">Order Id</th>
										<th>Order Date</th>
										<th>Due Date</th>
										<th>Total Qty</th>
										<th>Ready Qty</th>
										<th>Not Ready Qty</th>
										<th>Delivered Qty</th>
										<th>Order Amount</th>
										<th>Paid</th>
										<th>Actual Paid</th>
										<th>Remaining</th>
										<th>Order Status</th>
										<!-- <th>Order Status</th> -->
									</tr>
								</thead>
								<tbody>
									<c:set var = "status1" value = "Not Ready"/>
									<c:set var = "status2" value = "Incomplete"/>
									<c:set var = "status3" value = "Ready"/>
									<c:set var = "status4" value = "Delivered"/>
									<c:forEach var="v" items="${AllOrderDetails}">
										<tr class="text-center">
											<td><c:out value="${v.invoiceNo}"></c:out></td>
											<td style="display: none"><c:out value="${v.orderId}"></c:out></td>
											<td><c:out value="${v.orderDate}"></c:out></td>
											<td><c:out value="${v.dueDate}"></c:out></td>
											<td><c:out value="${v.totalQuantity}"></c:out></td>
											<td><c:out value="${v.ReadyQty}"></c:out></td>
											<td><c:out value="${v.NotReadyQty}"></c:out></td>
											<td><c:out value="${v.DeliveredQty}"></c:out></td>
											<td><c:out value="${v.totalAmount}"></c:out></td>
											<td><c:out value="${v.amountPaid}"></c:out></td>
											<td><c:out value="${v.actualPaid}"></c:out></td>
											<td><c:out value="${v.amountRemaining}"></c:out></td>
											<c:if test="${v.orderStatus == status1}">
												<td class="text-center">
												<a href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
													<span class="text-primary">${v.orderStatus}</span>
											</a></td>
											</c:if>
											<c:if test="${v.orderStatus == status2}">
												<td class="text-center">
												<a href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
													<span class="text-danger">${v.orderStatus}</span>
											</a></td>
											</c:if>
											<c:if test="${v.orderStatus == status3}">
												<td class="text-center">
												<a href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
													<span class="text-primary">${v.orderStatus}</span>
											</a></td>
											</c:if>
											<c:if test="${v.orderStatus == status4}">
												<td class="text-center">
												 <a href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}"> 
													<span class="text-success">${v.orderStatus}</span>
											 </a> </td>
											</c:if>
											<%-- <td class="text-center"><a
												href="Customer_NotReadyPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}">
													<span class="text-danger">${v.orderStatus}</span>
											</a></td> --%>

											<%-- <td class="text-center"><a href="ReturnOrderPage.html?i=${v.invoiceNo}&o=${v.orderId}&c=${v.cId}&q=${v.totalQuantity}"><span
													class="btn btn-primary mb-2 mr-2">Return Order</span></a></td>  --%>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Invoice No</th>
										<th style="display: none">Order Id</th>
										<th>Order Date</th>
										<th>Due Date</th>
										<th>Total Qty</th>
										<th>Ready Qty</th>
										<th>Not Ready Qty</th>
										<th>Delivered Qty</th>
										<th>Order Amount</th>
										<th>Paid</th>
										<th>Actual Paid</th>
										<th>Remaining</th>
										<th>Order Status</th>
										<!-- <th>Order Status</th> -->
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
				<form action="makeReamainingPayment.html" method="get">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<b>Make Remaining Payments</b>
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
						<div class="n-chk">
							<label class="new-control new-radio radio-primary"> <input
								type="radio" class="new-control-input" name="custom_radio_1"
								checked id="CollectPayment"> <span
								class="new-control-indicator"></span>Collect Payment
							</label>
							<!-- onclick="document.getElementById('Paid').disabled = false" -->
							<div class="form-row">
								<div class="col-xl-6">
									<label>Amount</label> <input
										class="form-control form-control-sm" type="text"
										id="payingAmount" style="color: #000;" readonly
										name="payingAmount"> <input
										class="form-control form-control-sm" type="hidden" id="cId2"
										style="color: #000;" readonly name="cId2">
								</div>
								<div class="col-xl-6">
									<label>Paid</label> <input class="form-control form-control-sm"
										type="text" id="paidAmt" autocomplete="off"
										onkeypress="onPaidAmtKeyPress()" onclick="onPaidClick()"
										style="color: #000;" name="paidAmt" value="0"
										onchange="onPaidChange()">
									<p id="msgPaidAmt" style="color: red;"></p>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal"
							style="background-color: red; color: #fff;">
							<i class="flaticon-cancel-12"></i> Close
						</button>
						<button type="submit" class="btn btn-success">Pay Amount</button>
					</div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function onAmountChange(){
		payingAmount
	}
</script>


	<script>
		$(document).ready(function() {
			App.init();
		});
	</script>
	<script src="plugins/highlight/highlight.pack.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!--  BEGIN CUSTOM SCRIPTS FILE  -->
	<script src="assets/js/scrollspyNav.js"></script>
	<script
		src="assets/js/forms/bootstrap_validation/bs_validation_script.js"></script>
	<!--  END CUSTOM SCRIPTS FILE  -->

	<script src="plugins/table/datatable/datatables.js"></script>
	<script src="plugins/flatpickr/custom-flatpickr.js"></script>
	<script src="plugins/flatpickr/flatpickr.js"></script>
	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script>
		var f2 = flatpickr(document.getElementById('dateTimeFlatpickr'), {
			enableTime : true,
			dateFormat : "d-m-y H:i",
		});
	</script>


</body>
</html>