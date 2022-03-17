<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Perclean Laundry </title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.png"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="plugins/flatpickr/flatpickr.css" rel="stylesheet" type="text/css">
    <link href="plugins/animate/animate.css" rel="stylesheet" type="text/css" />
    <link href="plugins/flatpickr/custom-flatpickr.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL PLUGINS -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="assets/css/tables/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <!--  BEGIN CUSTOM STYLE FILE  -->
    <link href="assets/css/scrollspyNav.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/components/custom-modal.css" rel="stylesheet" type="text/css" />
    <!--  END CUSTOM STYLE FILE  -->
</head>
<script type="text/javascript">
   function onBodyLoad(){
	   if($('#CustomerId').val()!=''){
			$('#CustomerId').trigger('change');
		}
		var d = new Date();
		$("#orderDate").val(d);
}
</script>
<script type="text/javascript">
	function onIdLoad(){
		var CustomerId = $("#CustomerId").val();
		$.getJSON('getCustomerContactDetails.html', {
			CustomerId : CustomerId
		}).done(function(data) {
			$("#cId").val(data[0].cId);
			$("#CustomerName").val(data[0].cName);
			$("#CustomerAddress").val(data[0].cAddress);
			$("#CustomerContact").val(data[0].cMobile);
			$("#Balance").val(data[0].cAmount);
			$("#NewBalance").val(data[0].cAmount);
			$("#Amt").val(data[0].cAmount);
			//$('#orderDate').focus();
		});
}
</script>
<script type="text/javascript">
	function onClothTypeChange(){
		var orderId = $("#orderId").val();
		var ClothType = $("#ClothTypeID").val();
		var cId = $("#CustomerId").val();

		$.getJSON('getServiceTypeListForReturn.html', {
			ClothType : ClothType,
			orderId : orderId,
			cId : cId
		}).done(function(data) {
			$("#oDate").val(data[0].orderDate);
			document.getElementById("ServiceTypeID").disabled = false;
			let s = document.getElementById("ServiceTypeID");
			s.innerHTML = "<option>Select Service Type</option>";
			let len = document.getElementById("ServiceTypeID").length;
			
			let option ; 
			for(let i = 0; i< data.length; i++){
				option = document.createElement('option');
				option.text = data[i].serviceType;
				option.value = data[i].serviceType;
				s.add(option);
			}	
		});
	}

	function onServiceTypeIDChange(){
		var orderId = $("#orderId").val();
		var ClothTypeID = $("#ClothTypeID").val();
		var ServiceTypeID = $("#ServiceTypeID").val();
	/* ------------------------------------------------------ */
		$.getJSON('getQuantityForReturn.html', {
			ClothTypeID : ClothTypeID,
			orderId : orderId,
			ServiceTypeID : ServiceTypeID
		}).done(function(data) {
			$("#Quantity").val(data[0].quantity);
			document.getElementById("completeQuantity").disabled = false;
		});
		
	}

	function onCompleteQtyChange(){
		var Quantity = parseInt($("#Quantity").val());
		var CompleteQty = parseInt($("#completeQuantity").val());

		if(CompleteQty > Quantity){
			alert("Enter Correct Quantity...");
			document.getElementById("completeQuantity").value = "";
			document.getElementById("completeQuantity").focus();
		}else{
			document.getElementById("btnAddClothReturn").disabled = false;
		}
	}

	function onReturnOrderDateChange(){
		document.getElementById("btnSaveReturnOrder").disabled = false;
	}
</script>
<script type="text/javascript">
	function onClothTypeClick(){
		document.getElementById("ClothTypeID").value = "";
		document.getElementById("ServiceTypeID").value = "";
		document.getElementById("Quantity").value = "";
		document.getElementById("completeQuantity").value = "";
		document.getElementById("btnAddClothReturn").disabled = true;
		document.getElementById("completeQuantity").disabled = true;
	}

	function onServiceTypeClick(){
		//document.getElementById("ServiceTypeID").value = "";
		document.getElementById("Quantity").value = "";
		document.getElementById("completeQuantity").value = "";
		document.getElementById("btnAddClothReturn").disabled = true;
		document.getElementById("completeQuantity").disabled = true;
	}
</script>
<body onload="onBodyLoad()">
	<div class="row layout-top-spacing">
		<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
			<div class="statbox widget box box-shadow">
				<div class="widget-header">
					<div class="row">
						<div class="row col-xl-12 col-md-12 col-sm-12 col-12">
							<!-- invoiceNo -->
							<span><h4>Invoice No.</h4></span>
							<div class="pt-1 col-md-3 mb-2">
								<input class="form-control form-control-sm" type="text"
									id="invoiceNo"
									style="background-color: white; width: 100px; border: none; color: #000;"
									disabled value="${invoiceNo}">
							</div>

							<span><h4>Order Date</h4></span>
							<div class="pt-1 col-md-3 mb-2">
								<input id="oDate"
									class="form-control flatpickr flatpickr-input active"
									type="text" placeholder="Select Order Date.."
									style="color: green;" onchange="onOrderDateChange()" required
									readonly>
							</div>

							<!-- <span><h4>Due Date</h4></span>
							<div class="pt-1 col-md-3 mb-2">
								<input id="dateTimeFlatpickr" value=""
									class="form-control flatpickr flatpickr-input active" required="required"
									type="text" placeholder="Select Due Date.." style="color: red;">
							</div> -->
						</div>
					</div>
				</div>

				<div class="widget-content widget-content-area">

					<div class="form-row">
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Id</label> <input class="form-control form-control-sm"
								type="text" onchange="onIdLoad()" id="CustomerId"
								style="background-color: white; width: 70px; border: none; color: #000;"
								disabled value="${customerId}">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Name</label> <input class="form-control form-control-sm"
								type="text" id="CustomerName" style="border: none; color: #000;"
								disabled>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Contact Number</label> <input
								class="form-control form-control-sm" type="text"
								id="CustomerContact" style="border: none; color: #000;" disabled>
						</div>
						<div class="col-xl-2  col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Address</label> <input
								class="form-control form-control-sm" type="text"
								id="CustomerAddress" style="border: none; color: #000;" disabled>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Balance</label> <input
								class="form-control form-control-sm" type="text" id="Balance"
								style="border: none; color: #000;" disabled>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>OrderId</label> <input
								class="form-control form-control-sm" type="text" id="orderId"
								style="border: none; color: #000;" disabled value="${orderId}">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label for="validationCustom02">Cloth Type</label> <input
								type="text" value="" id="ClothTypeID"
								class="form-control form-control-sm" placeholder="Cloth Type"
								autocomplete="off" list="clothTypeNames"
								onchange="onClothTypeChange()" onclick="onClothTypeClick()">
							<datalist id="clothTypeNames">
								<c:forEach items="${ClothList}" var="trl">
									<option value="${trl.clothType}">${trl.clothType}</option>
								</c:forEach>
							</datalist>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Service Type</label> <select id="ServiceTypeID"
								class="form-control form-control-sm" placeholder="Service Type"
								onchange="onServiceTypeIDChange()"
								onclick="onServiceTypeClick()" disabled>
								<option id="" value="">Select Service Type</option>

							</select>
						</div>

						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Quantity</label> <input type="text" value="" id="Quantity"
								readonly placeholder="Total Quantity"
								class="form-control form-control-sm" style="color: #000;">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Completed</label> <input type="text" id="completeQuantity"
								onkeypress="return restrictAlphabets(event)"
								onchange="onCompleteQtyChange()" placeholder="Completed"
								class="form-control form-control-sm" disabled
								style="color: #000;">
						</div>
						<div class="col-xl-4 col-lg-4 col-md-4 col-sm-6 mb-1 pt-2">
							<label></label>
							<button for="validationCustom05"
								onclick="onAddClothReturnClick()" id="btnAddClothReturn"
								disabled
								class="form-control  btn btn-primary button-block mb-4 mr-2 message"
								type="button" required>Add Cloth for Return</button>

							<div class="valid-feedback">Cloth Added Sucessfully</div>
							<div class="invalid-feedback">Please Fill All Data!</div>
						</div>
					</div>


					<!--  BEGIN CONTENT AREA  -->

					<div class="table">
						<table id="example1" class="display nowrap" style="width: 100%">
							<thead>
								<tr>
									<th>SrNo</th>
									<th>Cloth Type</th>
									<th>Service Type</th>
									<th>Quantity</th>
									<th>Complete Qty</th>
									<th class="no-content">Cancel</th>

								</tr>
							</thead>
							<tbody id="addClothForReturnTBody">

							</tbody>

							<tfoot>
								<tr>
									<th>SrNo</th>
									<th>Cloth Type</th>
									<th>Service Type</th>
									<th>Quantity</th>
									<th>Complete Qty</th>
									<th class="no-content">Cancel</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="row">
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Total Qty</label> <input type="text" id="TotalQuantity"
								class="form-control form-control-sm"
								style="border: none; color: #000;" name=""
								value="${totalQuantity}" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Complete Qty</label> <input type="text"
								id="totalCompleteQty" class="form-control form-control-sm"
								value="0" style="border: none; color: #000;" name="" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Balance Amt</label> <input type="text" id="NewBalance"
								class="form-control form-control-sm" value="0"
								style="border: none; color: #000;" name="" readonly>
						</div>

						<div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 mb-2">
							<label>Return Order Date</label> <input type="date"
								id="ReturnOrderDate" name=""
								class="form-control form-control-sm" value=""
								style="color: green;" onchange="onReturnOrderDateChange()"
								disabled>
						</div>
					</div>
					<div class="form-row">
						<div class="col-sm-6 mb-4">
							<button id="btnSaveReturnOrder" disabled
								class="form-control btn btn-primary mb-2 mr-2" type="button"
								onclick="onSaveReturnOrderClick()">
								<b>Save Return Order</b>
							</button>
							<input type="text"
								style="width: 100%; border: none; color: green; background-color: #fff;"
								id="successMsg">
						</div>
						<div class="col-sm-6 mb-4">
							<button id="btnDeliverCloths" class="form-control  btn btn-success mb-2 mr-2"
								data-toggle="modal" data-target="#exampleModal" type="submit">
								<b>Deliver Cloths</b>
							</button>
						</div>

						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<form action="deliverClothsOrder.html" method="get">
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
													name="custom_radio_1" checked id="CollectPayment">
													<span class="new-control-indicator"></span>Collect Payment
												</label>
												<!-- onclick="document.getElementById('Paid').disabled = false" -->
												<div class="form-row">
													<div class="col-xl-6">
														<label>Amount</label> <input
															class="form-control form-control-sm" type="text" id="Amt"
															style="color: #000;" readonly name="balanceAmt">
														<input class="form-control form-control-sm" type="hidden"
															id="cId2" style="color: #000;" readonly name="cId2"
															value="${customerId}">
													</div>
													<div class="col-xl-6">
														<label>Paid</label> <input
															class="form-control form-control-sm" type="text"
															id="Paid" autocomplete="off" onclick="onPaidClick()"
															style="color: #000;" name="paidAmt" value="0"
															onchange="onPaidChange()">
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
											<button class="btn" data-dismiss="modal">
												<i class="flaticon-cancel-12"></i> Discard
											</button>
											<button type="submit" class="btn btn-primary">
												Deliver</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onSkipPaymentClick() {
			document.getElementById("Paid").value = 0;
		}

		function onPaidClick() {
			document.getElementById("Paid").select();
			document.getElementById("CollectPayment").checked = true;
		}

		function onPaidChange() {
			var BalanceAmt = parseDouble($("#Amt").val());
			var Paid = parseDouble($("#Paid").val());
			if (Paid > BalanceAmt) {
				alert("Enter Amount Less than or Equal to " + BalanceAmt);
				document.getElementById("Paid").focus();
			}
		}
	</script>
<script type="text/javascript">
	function onSaveReturnOrderClick() {
		var cId = $("#CustomerId").val();
		var TotalQuantity = parseInt($("#TotalQuantity").val());
		var totalCompleteQty = parseInt($("#totalCompleteQty").val());
		var invoiceNo = parseInt($("#invoiceNo").val());
		var ReturnOrderDate = $("#ReturnOrderDate").val();
		var orderId = $("#orderId").val();

		$.getJSON('saveReturnOrder.html', {
			cId : cId,
			TotalQuantity : TotalQuantity,
			totalCompleteQty : totalCompleteQty,
			ReturnOrderDate : ReturnOrderDate,
			orderId : orderId,
			invoiceNo : invoiceNo
		});
		document.getElementById("btnDeliverCloths").disabled = false;
		document.getElementById("btnSaveReturnOrder").disabled = true;
		document.getElementById("ClothTypeID").disabled = true;
		document.getElementById("ServiceTypeID").disabled = true;
		$("#successMsg").val("Return Order Saved Successfully...");
	}
</script>
<script type="text/javascript">
	function onAddClothReturnClick() {
		var cId = $("#CustomerId").val();
		var ClothTypeID = $("#ClothTypeID").val();
		var ServiceTypeID = $("#ServiceTypeID").val();
		var Quantity = parseInt($("#Quantity").val());
		var CompleteQty = parseInt($("#completeQuantity").val());
		var invoiceNo = parseInt($("#invoiceNo").val());
		var orderDate = $("#oDate").val();
		var orderId = $("#orderId").val();

		$.getJSON('saveReturnOrderEntry.html', {
			cId : cId,
			ClothTypeID : ClothTypeID,
			ServiceTypeID : ServiceTypeID,
			Quantity : Quantity,
			CompleteQty : CompleteQty,
			orderDate : orderDate,
			orderId : orderId,
			invoiceNo : invoiceNo
		});

		var tBody = document.getElementById("addClothForReturnTBody");
		var row = tBody.insertRow(0);

		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);

		cell1.textContent = '#';
		cell2.textContent = ClothTypeID;
		cell3.textContent = ServiceTypeID;
		cell4.textContent = Quantity;
		cell5.textContent = CompleteQty;
		cell6.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" onclick="deleteClothOrder(this.parentNode.parentNode)" style="color:red;" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle table-cancel"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>';

		//  var TotalQuantity =parseInt($("#TotalQuantity").val());
		//   $("#TotalQuantity").val(TotalQuantity + Quantity);
		var totalCompleteQty = parseInt($("#totalCompleteQty").val());
		$("#totalCompleteQty").val(totalCompleteQty + CompleteQty);

		onClothTypeClick();
		document.getElementById("ReturnOrderDate").disabled = false;
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
    <script src="assets/js/forms/bootstrap_validation/bs_validation_script.js"></script>
    <!--  END CUSTOM SCRIPTS FILE  -->

    <script src="plugins/table/datatable/datatables.js"></script>
    <script src="plugins/flatpickr/custom-flatpickr.js"></script>
    <script src="plugins/flatpickr/flatpickr.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script>
        var f2 = flatpickr(document.getElementById('dateTimeFlatpickr'), {
            enableTime: true,
            dateFormat: "d-m-y H:i",
        });
    </script>
    <script>
        $(document).ready(function() {
        $('#example').DataTable( {
        "scrollY": "200px" ,
        "scrollCollapse": true,
        "scrollX": true
        } );
        } );
    </script>
</body>
</html>