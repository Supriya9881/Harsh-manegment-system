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
<body>
	<div class="container">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Print All Barcode</h4>
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
										<th class="text-left">Customer Name</th>
										<th>Invoice No</th>
										<th style="display: none;">Order Id</th>
										<th>Order Date</th>
										<th>Due Date</th>
										<th>Total Qty</th>
										<th style="display: none;">Total Amount</th>
										<th style="display: none;">Paid</th>
										<th style="display: none;">Remaining</th>
										<th class="text-center">Order Details</th>
										<th class="text-center">Barcode</th>
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
											<td style="display: none;"><c:out value="${v.totalAmount}"></c:out></td>
											<td style="display: none;"><c:out value="${v.amountPaid}"></c:out></td>
											<td style="display: none;"><c:out value="${v.amountRemaining}"></c:out></td>
											<td><button class="btn btn-success mb-4 mr-2" onclick="onBtnOrderDetailsClick(this.parentNode.parentNode)"
													id="DeliverOnlyReadyCloths" data-toggle="modal"
													data-target="#exampleModal" type="button">
													<b>Order Details</b>
												</button></td>

											<%-- <td class="text-center"><a href="printOutsideServesesBarcode.html?orderId=${v.orderId}"
												target="_blank">
													<button class="btn btn-secondary mb-2 mr-2" type="submit">Order
														Details</button>
											</a></td> --%>
											<td class="text-center"><a href="printAllBrcodeOfOrder.html?orderId=${v.orderId}"
												target="_blank">
													<button class="btn btn-primary mb-2 mr-2" type="submit">Print
														All Barcode</button>
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
										<th style="display: none;">Total Amount</th>
										<th style="display: none;">Paid</th>
										<th style="display: none;">Remaining</th>
										<th class="text-center">Order Details</th>
										<th class="text-center">Barcode</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function onBtnOrderDetailsClick(a) {
			//var ClothTypeID = a.children[1].textContent;
			document.getElementById('invoiceNo').innerHTML = 'Invoice No : '+a.children[2].textContent;
			document.getElementById('cName').innerHTML = 'Name : '+a.children[1].textContent;
			document.getElementById('orderDate').innerHTML = 'Order Date : '+a.children[4].textContent;
			document.getElementById('dueDate').innerHTML = 'Due Date : '+a.children[5].textContent;
			
			document.getElementById('qty').innerHTML = 'Qty : '+a.children[6].textContent;
			document.getElementById('totAmt').innerHTML = 'Tot : '+a.children[7].textContent;
			document.getElementById('paid').innerHTML = 'Paid : '+a.children[8].textContent;
			document.getElementById('rem').innerHTML = 'Rem : '+a.children[9].textContent;
			
			
			var orderId = a.children[3].textContent;
			var invoiceNo = a.children[2].textContent;
			$.getJSON('getOrderChiDetailNew.html', {
				orderId : orderId,
				invoiceNo : invoiceNo
			}).done(function(data) {
				$("#invoiceNo").val(data[0].invoiceNo);
				$("#orderId").val(data[0].orderId);
				var table = document.getElementById("orderDetailsTable");
				table.innerHTML	=	'';
				for (var i = 0; i < data.length; i++) {
					var row =  table.insertRow(0); 
					  var cell1 = row.insertCell(0);   var cell2 = row.insertCell(1); 
					  var cell3 = row.insertCell(2);   var cell4 = row.insertCell(3);
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
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
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
						<div class="col-xl-5">
							<label id="invoiceNo">invoice</label>
						</div>
						<div class="col-xl-7">
							<label id="cName">name</label>
						</div>
						<div class="col-xl-5">
							<label id="orderDate">orderDate</label>
						</div>
						<div class="col-xl-7">
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
					<button class="btn btn-danger" data-dismiss="modal">
						<i class="flaticon-cancel-12"></i> Discard
					</button> 
					<%-- <a href="printAllBrcodeOfOrder.html?orderId=${v.orderId}"
						target="_blank">
						<button class="btn btn-success mb-2 mr-2" type="submit">Print
							All Barcode</button>
					</a> --%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>