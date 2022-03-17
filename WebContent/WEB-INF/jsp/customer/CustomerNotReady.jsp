<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function onOrderIdChange() {
		var orderId = $("#od").val();
		var invoiceNo = $("#invoice").val();
		$
				.getJSON('getOrderChiAllDetail.html', {
					orderId : orderId,
					invoiceNo : invoiceNo
				})
				.done(
						function(data) {
							$("#invoiceNo").val(data[0].invoiceNo);
							$("#orderId").val(data[0].orderId);
							var table = document
									.getElementById("orderDetailsTable");
							for (var i = 0; i < data.length; i++) {
								var row = table.insertRow(0);
								var cell1 = row.insertCell(0);
								var cell2 = row.insertCell(1);
								var cell3 = row.insertCell(2);
								var cell4 = row.insertCell(3);
								var cell5 = row.insertCell(4); //var cell6 = row.insertCell(5);

								if (data[i].Status == 'Not Ready') {
									cell1.innerHTML = "<input type='checkbox' onclick='onCheckBoxClick(this.parentNode.parentNode)' value='"
											+ data[i].saparateId
											+ "' name='SelectedId' id='CheckBox"
											+ i + "'>";
								}
								if (data[i].Status == 'Ready') {
									cell1.innerHTML = "<input type='checkbox' disabled checked onclick='onCheckBoxClick(this.parentNode.parentNode)' >";
								}
								if (data[i].Status == 'Delivered') {
									cell1.innerHTML = "<input type='checkbox' checked disabled onclick='onCheckBoxClick(this.parentNode.parentNode)' >";
								}
								//cell1.innerHTML = data[i].saparateId;
								cell2.innerHTML = data[i].clothType;
								cell3.innerHTML = data[i].serviceType;
								cell4.innerHTML = data[i].Status;
								cell5.innerHTML = data[i].deleveredDate;
							}
						});

		var ordercheStatus = $('#ordercheStatus').val();
		//alert("order che Status is "+ordercheStatus);
		if (ordercheStatus == 'Ready') {
			document.getElementById("SaveReadyOrder").disabled = true;
			document.getElementById("sendReadySMS").disabled = false;
		}
		if (ordercheStatus == 'Delivered') {
			document.getElementById("DeliverOnlyReadyCloths").disabled = true;
			document.getElementById("SaveReadyOrder").disabled = true;
		}

		/* var cAmount = parseInt($("#cAmount").val());
		
		   if( cAmount === 0 ){
			   
			  $("#disRemAmt").text(0);
			  
		   } */
	}
</script>
<!-- $("#MainRate").val(data[0].rate); -->
<script type="text/javascript">
	function onCheckBoxClick(a) {
		var i = a.children[0].textContent;
		///alert("Okkk "+a.children[0].textContent);
		var cb = document.getElementById("CheckBox" + i);
		if (cb.checked == true) {
			//alert(i+"th checkbox is Checked");
		} else {
			//alert(i+"th checkbox is Unchecked");
		}
	}
</script>
<script type="text/javascript">
	function onBodyLoad() {
		if ($('#od').val() != '') {
			$('#od').trigger('change');
			$("#cId2").val($('#cId').val());
			$("#Amt").val($('#cAmount').val());
			$("#oAmt").val($('#amountRemaining').val());
			$("#cMobile2").val($('#cMobile').val());
		}

	}
</script>
<style type="text/css">
#table-wrapper {
	position: relative;
}

#table-scroll {
	height: 200px;
	overflow: auto;
	margin-top: 5px;
}

#table-wrapper table {
	width: 100%;
}

#table-wrapper table thead th .text {
	position: absolute;
	top: -20px;
	z-index: 2;
	height: 20px;
	width: 35%;
	border: 1px solid red;
}
}
</style>
</head>
<body onload="onBodyLoad()">
	<div class="container-fluid pt-0">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="row col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Customer Order Details</h4>
							</div>
						</div>
					</div>
					<div class="widget-content widget-content-area">
						<div class="form-row" style="border-right: 2px solid #eeeeee;">
							<c:forEach var="v" items="${CustomerInfo}">
								<div class="col-md-2 mb-2">
									<label>Name</label>
									<div class="details" style="display: none;">
										<c:out value="${v.cId }"></c:out>
										<input id="cId" value="${v.cId }">
									</div>
									<div class="details">
										<b><c:out value="${v.cName}"></c:out></b> <input id="cName"
											value="${v.cName }" type="hidden">
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Contact Number</label>
									<div class="details">
										<b><c:out value="${v.cMobile}"></c:out></b> <input
											id="cMobile" value="${v.cMobile }" type="hidden">
									</div>
								</div>
								<%-- <div class="col-md-2 mb-2">
									<label>Address</label>
									<div class="details">
										<b><c:out value="${v.cAddress}"></c:out></b>
									</div>
								</div> --%>
								<div class="col-md-2 mb-2">
									<label>All Balance</label>
									<div class="details">
										<b><c:out value="${v.cAmount }"></c:out></b> <input
											id="cAmount" value="${v.cAmount }" type="hidden">
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Wallet</label>
									<div class="details">
										<b><c:out value="${v.cWallet }"></c:out></b> <input
											id="cAmount" value="${v.cWallet }" type="hidden">
									</div>
								</div>
							</c:forEach>
							<c:forEach var="v" items="${AllOrderDetails}">
								<div class="col-md-2 mb-2">
									<label>Invoice No</label>
									<div class="details">
										<b><c:out value="${v.invoiceNo}"></c:out></b>
									</div>
								</div>

								<div class="col-md-2 mb-2">
									<div class="details">
										<button class=" btn btn-success mb-4 mr-2" disabled
											onclick="onSendReadySmsBtn()" id="sendReadySMS" type="submit"
											required>
											<b>Send Ready</b>
										</button>
									</div>
								</div>
								
								<div class="col-md-2 mb-2">
									<label>Tot Cloths</label>
									<div class="details">
										<b><c:out value="${v.totalQuantity}"></c:out></b>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Ready Qty</label>
									<div class="details">
										<b><c:out value="${v.ReadyQty}"></c:out></b>
									</div>
								</div>
								<div class="col-md-2 mb-2">
									<label>Not Ready</label>
									<div class="details">
										<b><c:out value="${v.NotReadyQty}"></c:out></b>
									</div>
								</div>

								<div class="col-md-2 mb-2">
									<label>Delivered</label>
									<div class="details">
										<b><c:out value="${v.DeliveredQty}"></c:out></b>
									</div>
									<input type="hidden" id="invoice" value="${v.invoiceNo}">
									<input type="hidden" id="od" value="${v.orderId}"
										onchange="onOrderIdChange()">
								</div>
								<div class="col-md-2 mb-2">
									<label>Order Rem Amt</label>
									<div class="details">
										<b><c:out value="${v.amountRemaining}"></c:out></b> <input
											type="hidden" id="amountRemaining"
											value="${v.amountRemaining}" onchange="onRemChange()">
									</div>
								</div>
								<%-- <div class="col-md-2 mb-2">
									<label>Remaining Amt</label>
									<div class="details" id="disRemAmt">
										<span id="disRemAmt" ><c:out value="${v.amountRemaining}"></c:out></span> 
										<input type="hidden" id="amountRemaining" value="${v.amountRemaining}" onchange="onRemChange()">
									</div>
								</div> --%>
								<div class="col-md-2 mb-2">
									<label>Status</label>
									<div class="details">
										<b><c:out value="${v.orderStatus }"></c:out></b> <input
											id="orderStatus" value="${v.orderStatus }" type="hidden">
										<input id="ordercheStatus" value="${v.orderStatus }"
											type="hidden">
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="container pt-3"
							style="border-bottom: 2px solid #ffffff;"></div>
						 
						<form action="SaveReadyClothsInOrder.html" method="post"> 
								<!-- com.Order.ReturnOrderControllerNew -->
							<div id="table-wrapper">
								<div id="table-scroll">
									<table id="">
										<thead class="text-center">
											<tr class="bg-warning">
												<th><input type="checkbox" onclick="onSelctClick()"
													id="cbAll" value="Select All"> Select All</th>
												<th>Cloth Type</th>
												<th>Service Type</th>
												<th>Status</th>
												<th>Delivered Date</th>
											</tr>
										</thead>

										<tbody id="orderDetailsTable" class="text-center">

										</tbody>
										<tfoot class="text-center">
											<tr class="bg-success">
												<th>Select All</th>
												<th>Cloth Type</th>
												<th>Service Type</th>
												<th>Status</th>
												<th>Delivered Date</th>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
							<input type="hidden" name="invoiceNo" id="invoiceNo"> <input
								type="hidden" name="orderId" id="orderId"> <input
								type="hidden" name="cMobile2" id="cMobile2"> <br>
							<div class="form-row">
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-primary mb-4 mr-2"
										name="SaveReadyOrder" id="SaveReadyOrder" type="submit"
										required>
										<b>Save Ready Cloths</b>
									</button>
								</div>
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-success mb-4 mr-2"
										id="DeliverOnlyReadyCloths" data-toggle="modal"
										data-target="#exampleModal" type="button" required>
										<b>Deliver Only Ready Cloths</b>
									</button>
								</div>
								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">
													<b>Deliver Cloths</b>
												</h5>
												<button type="submit" class="close" data-dismiss="modal"
													aria-label="Close">
													<svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
														width="24" height="24" viewBox="0 0 24 24" fill="none"
														stroke="currentColor" stroke-width="2"
														stroke-linecap="round" stroke-linejoin="round"
														class="feather feather-x">
													<line x1="18" y1="6" x2="6" y2="18"></line>
													<line x1="6" y1="6" x2="18" y2="18"></line></svg>
												</button>

											</div>
											<div class="modal-body">
												<div class="n-chk">
													<label class="new-control new-radio radio-primary">
														<input type="radio" class="new-control-input"
														onclick="onCollectPaymentClick()" name="custom_radio_1"
														checked id="CollectPayment"> <span
														class="new-control-indicator"></span>Collect Payment
													</label>
													<!-- onclick="document.getElementById('Paid').disabled = false" -->
													<div class="form-row">
														<div class="col-xl-4">
															<label>Payment Mode</label> <select id="PreferecesName"
																class="form-control form-control-sm" name="paymentMode">
																<option id="Cash" value="Cash">Cash</option>
																<option id="" value="Credit Card">Credit Card</option>
																<option id="" value="UPI Payment">UPI Payment</option>

															</select>
														</div>
														<div class="col-xl-4">
															<label>All Rem Amount</label> <input
																class="form-control form-control-sm" type="text"
																id="Amt" style="color: #000;" readonly name="balanceAmt">
															<input class="form-control form-control-sm" type="hidden"
																id="cId2" style="color: #000;" readonly name="cId2"
																value="${customerId}">
														</div>
														<div class="col-xl-4">
															<label>Order Rem Amount</label> <input
																class="form-control form-control-sm" type="text"
																id="oAmt" style="color: #000;" readonly
																name="balanceAmt"> <input
																class="form-control form-control-sm" type="hidden"
																id="cId2" style="color: #000;" readonly name="cId2"
																value="${customerId}">
														</div>
														<div class="col-xl-4">
															<label>Paid</label> <input
																class="form-control form-control-sm" type="text"
																id="Paid" autocomplete="off" onclick="onPaidClick()"
																style="color: #000;" name="paidAmt" value="0"
																onkeyup="onAmtPaidChange()"
																onkeypress="return restrictAlphabets(event)">
														</div>
														<div class="col-xl-8">
															<label>Discription</label> <input type="text"
																id="discription" name="discription"
																placeholder="Discription"
																class="form-control form-control-sm">
														</div>
													</div>
												</div>
												<div class="n-chk mt-4">
													<label class="new-control new-radio"> <input
														type="radio" class="new-control-input"
														name="custom_radio_1" onclick="onSkipPaymentClick()">
														<span class="new-control-indicator"></span>Skip Payment
													</label>
												</div>
											</div>
											<div class="modal-footer">
												<button class="btn btn-danger" data-dismiss="modal">
													<i class="flaticon-cancel-12"></i> Discard
												</button>
												<button type="submit" class="btn btn-success"
													name="DeliverClothOrder" id="btnDeliverClothOrder" disabled>
													Deliver</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onSendReadySmsBtn() {
			var orderId = $('#od').val();
			var invoiceNo = $('#invoice').val();
			var cName = $('#cName').val();
			var cMobile = $('#cMobile').val();
			/* com.customer.CustomerController */
			$.getJSON('sendReadyOrderSMS.html', {
				cName : cName,
				orderId : orderId,
				invoiceNo : invoiceNo,
				cMobile : cMobile
			});

			//sendReadySMS
			let sendReadySMS = document.getElementById('sendReadySMS');
			sendReadySMS.style.backgroundColor = '#ff4d00';
			sendReadySMS.textContent = 'Ready SMS Sended';
			sendReadySMS.disabled = true;
		}
	</script>
	<script type="text/javascript">
		function onSelctClick() {
			var checkBox1 = document.getElementById("cbAll");
			if (checkBox1.checked == true) {
				//alert("checked");
				var items = document.getElementsByName('SelectedId');
				for (var i = 0; i < items.length; i++) {
					if (items[i].type == 'checkbox')
						items[i].checked = true;
				}
			} else {
				//alert("unchecked");
				var items = document.getElementsByName('SelectedId');
				for (var i = 0; i < items.length; i++) {
					if (items[i].type == 'checkbox')
						items[i].checked = false;
				}
			}
		}
	</script>

	<script type="text/javascript">
		function onSkipPaymentClick() {
			document.getElementById("Paid").value = 0;
			document.getElementById("btnDeliverClothOrder").disabled = false;
		}

		function onCollectPaymentClick() {
			document.getElementById("btnDeliverClothOrder").disabled = true;
			//document.getElementById("Paid").focus();
			//document.getElementById("Paid").select();
		}

		function onPaidClick() {
			document.getElementById("Paid").select();
			document.getElementById("CollectPayment").checked = true;
		}
	</script>

	<script type="text/javascript">
		function onAmtPaidChange() {
			var BalanceAmt = parseInt($("#Amt").val());
			var Paid = parseInt($("#Paid").val());
			/* if (Paid > BalanceAmt) {
				alert("Enter Amount Less than or Equal to " + BalanceAmt);
				document.getElementById("Paid").focus();
				document.getElementById("Paid").value='';
				document.getElementById("btnDeliverClothOrder").disabled = true;
			}else{
				document.getElementById("btnDeliverClothOrder").disabled = false;
			} */
			document.getElementById("btnDeliverClothOrder").disabled = false;
		}
	</script>
</body>
</html>