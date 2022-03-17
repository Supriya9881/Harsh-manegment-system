<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>  
 <!--    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>   
   <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>  -->
   <script type="text/javascript">
       $(function() {
    	   $("#orderDate").datepicker({ dateFormat: "dd-mm-yy" }).val()
    	   $("#DueDate").datepicker({ dateFormat: "dd-mm-yy" }).val()
       });
   </script>

<script type="text/javascript">
	function getCurrDate(){
		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();
		//var output = d.getFullYear() + '-' + (month<10 ? '0' : '') + month + '-' + (day<10 ? '0' : '') + day;
		var output = (day<10 ? '0' : '') + day + '-' + (month<10 ? '0' : '') + month + '-' + d.getFullYear();
		document.getElementById("orderDate").value = output;
		 if($('#orderDate').val()!=''){
				$('#orderDate').trigger('change');
			}
	}
</script>

<script type="text/javascript">
   function onBodyLoad(){
	   generateRandom();
	   if($('#CustomerId').val()!=''){
			$('#CustomerId').trigger('change');
		}
		getCurrDate();
}
</script>
 <script type="text/javascript">
	function onServiceTypeIDChange(){
			var ServiceTypeID = $("#ServiceTypeID").val();
			var ClothTypeID = $("#ClothTypeID").val();
			var CustomerId = $("#CustomerId").val();
		/* ------------------------------------------------------ */
			$.getJSON('getRateDetails.html', {
				ServiceTypeID : ServiceTypeID,
				ClothTypeID : ClothTypeID
			}).done(function(data) {
				 if (data[0].rate == null) {
					 alert("Rate Null");
				} else {
					 $("#MainRate").val(data[0].rate);
					 document.getElementById("nowRate").disabled = false;
					 $('#nowRate').focus();
				} 
			});
			document.getElementById("nowRate").disabled = false;
			 $('#nowRate').focus();
		/* ---------------------------------------------------- */
			$.getJSON('getRateFromCustomerOrder.html', {
				ServiceTypeID : ServiceTypeID,
				ClothTypeID : ClothTypeID,
				CustomerId : CustomerId
			}).done(function(data) {
				 if (data[0].rate == null) {
					 alert("Rate Null"); 
				} else {
					 $("#PreviousRate").val(data[0].rate);
					 $("#nowRate").val(data[0].rate);
					 document.getElementById("Quantity").disabled = false;
					 //$('#nowRate').focus();
				} 
			});
        /* ------------------------------------------------------ */
	 
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
			$("#Wallet").val(data[0].cWallet);
			$("#wallet").val(data[0].cWallet);
			//$('#orderDate').focus();
		});
}
</script>
</head>
<script type="text/javascript">
	/* Click */ 
	function onClothTypeClick(){
		document.getElementById("ClothTypeID").value = "";
		document.getElementById("ServiceTypeID").value = "";
		document.getElementById("ServiceTypeID").disabled = true;
		document.getElementById("MainRate").value = "";
		document.getElementById("PreviousRate").value = "";
		document.getElementById("nowRate").value = "";
		document.getElementById("nowRate").disabled = true;
		document.getElementById("Quantity").value = "";
		document.getElementById("Quantity").disabled = true;
		document.getElementById("Amount").value = "";
		document.getElementById("btnAddCloth").disabled = true;
	}

	function onServiceTypeClick(){
		document.getElementById("ServiceTypeID").value = "";
		document.getElementById("MainRate").value = "";
		document.getElementById("PreviousRate").value = "";
		document.getElementById("nowRate").value = "";
		document.getElementById("nowRate").disabled = true;
		document.getElementById("Quantity").value = "";
		document.getElementById("Quantity").disabled = true;
		document.getElementById("Amount").value = "";
		document.getElementById("btnAddCloth").disabled = true;
		
	}
	
	/* Change */
	function onOrderDateChange(){
		$("#OrderDate").val($("#orderDate").val());
		document.getElementById("ClothTypeID").disabled = false;
		$('#ClothTypeID').focus();
		
		
		$.getJSON('getInvoiceNoForNew.html').done(function(data) {
			//alert(data[0].inv);
			if(data[0].inv == null){
			//	alert("its null "+data[0].inv);
				$("#invoiceNo").val(18000);
				$("#InvoiceNo").val(18000);
			}else{
				//alert("its Not null "+data[0].inv)
				$("#invoiceNo").val((data[0].inv)+1);
				$("#InvoiceNo").val((data[0].inv)+1);
			}
		});
	}
	
	function onClothTypeChange(){
		document.getElementById("ServiceTypeID").disabled = false;
		$('#ServiceTypeID').focus();
	}

	function onQuantityChange(){
		document.getElementById("Quantity").disabled = false;
		$('#Quantity').focus();
	}

	function onDueDateChange(){
		/* var Wallet = parseFloat(document.getElementById("Wallet").value);
		var wallet = parseFloat(document.getElementById("wallet").value);
		var AmountPaid = parseFloat(document.getElementById("AmountPaid").value);
		document.getElementById("AmountPaid").value = (wallet - Wallet) + AmountPaid ; */
		
		document.getElementById("btnGetOrder").disabled = false;
		$('#btnGetOrder').focus();
	}
	
	function onAmountPaidClick(){
		$('#AmountPaid').focus();
		$('#AmountPaid').select();
	}
	
	function onAmountPaidChange(){
		//$('#addClothTBody :button').prop('disabled', true);
		//let button = document.querySelector(".button");
		//button.disabled = true; 
		//$("button").attr("disabled", "disabled");
		var Balance = parseFloat(document.getElementById("Balance").value);
		//var NewBalance = parseFloat(document.getElementById("NewBalance").value);
		var TotalAmount = parseFloat(document.getElementById("TotalAmount").value);
		var NewBalance = Balance + TotalAmount;
		var AmountPaid = parseFloat(document.getElementById("AmountPaid").value);
		var AmountRemaining = parseFloat(document.getElementById("AmtRem").value);
		var Wallet = parseFloat(document.getElementById("Wallet").value);
		$('#actualPaid').val(AmountPaid);
		if((AmountPaid<=TotalAmount) && (AmountPaid>=0)){
			AmountRemaining = TotalAmount - AmountPaid;
			NewBalance = NewBalance - AmountPaid;
			document.getElementById("AmtRem").value = AmountRemaining;
			document.getElementById("NewBalance").value = NewBalance;
			document.getElementById("DueDate").disabled = false;
		}else{
				if((AmountPaid<=NewBalance) && (AmountPaid>=0)){
					AmountRemaining = NewBalance - NewBalance;
					NewBalance = NewBalance - AmountPaid;
					document.getElementById("AmtRem").value = AmountRemaining;
					document.getElementById("NewBalance").value = NewBalance;
					document.getElementById("DueDate").disabled = false;
				}else{
						//alert("Extra Amount is : "+(AmountPaid-NewBalance));
						document.getElementById("Wallet").value = Wallet+(AmountPaid-NewBalance);
						$('#AmtRem').val(0);
						$('#NewBalance').val(0);
						//document.getElementById("AmountPaid").value = "";
						//$('#AmountPaid').focus();	
						document.getElementById("DueDate").disabled = false;
					}
			}
		
		//document.getElementById("btnCancel").disabled = true;
	}
</script>
<body onload="onBodyLoad()">
	<div class="row ">
		<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
			<div class="statbox widget box box-shadow">
				<div class="widget-header">
					<div class="row">
						<div class="row col-xl-12 col-md-12 col-sm-12 col-12"><!-- invoiceNo -->
							<span><h4>Invoice No.</h4></span> 
							<div class="pt-1 col-md-3 mb-2">
								<input class="form-control form-control-sm" type="text" id="invoiceNo"
								style="background-color: white;width: 100px;border: none;color: #000;" disabled>
							</div>
							
							<span><h4>Order Date</h4></span>
							<!-- <div class="pt-1 col-md-3 mb-2">
								<input id="orderDate" class="form-control"
									type="date" placeholder="Select Order Date.." style="color: green;" 
									onchange="onOrderDateChange()" required>
							</div> -->
							<div class="pt-1 col-md-3 mb-2">
								<input id="orderDate" class="form-control"
									type="text" placeholder="Select Order Date.." style="color: green;" 
									onchange="onOrderDateChange()" required>
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
							<div class="col-xl-1 col-lg-1 col-md-4 col-sm-6 mb-2">
								<label>Id</label>
								 <input class="form-control form-control-sm" type="text" onchange="onIdLoad()" id="CustomerId" 
								style="background-color: white;width: 70px;border: none;color: #000;" disabled
								 value="${customerId}"> 
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Name</label>
								<input class="form-control form-control-sm" type="text" id="CustomerName" 
								style="border: none;color: #000;" disabled>
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Contact Number</label>
								<input class="form-control form-control-sm" type="text" id="CustomerContact" 
								style="border: none;color: #000;" disabled>
							</div>
							<div class="col-xl-2  col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Wallet</label>
								<input class="form-control form-control-sm" type="hidden" id="CustomerAddress" 
								style="border: none;color: #000;" disabled>
								<input class="form-control form-control-sm" type="text" id="wallet" 
								style="border: none;color: #000;" disabled>
							</div>
							<div class="col-xl-1 col-lg-1 col-md-4 col-sm-6 mb-2">
								<label>Balance</label>
								<input class="form-control form-control-sm" type="text" id="Balance" 
								style="border: none;color: #000;" disabled>
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label for="validationCustom02">Cloth Type</label>
								<input type="text" value="" id="ClothTypeID" class="form-control form-control-sm" 
								placeholder="Cloth Type" autocomplete="off" list="clothTypeNames" disabled
								 onchange="onClothTypeChange()" onclick="onClothTypeClick()">
									<datalist id="clothTypeNames">
										<c:forEach items="${ClothList}" var="trl">
											<option value="${trl.value}">${trl.value}</option>
										</c:forEach>
									</datalist>
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Service Type</label> 
								<input type="text" id="ServiceTypeID" class="form-control form-control-sm" 
								placeholder="Service Type" autocomplete="off" list="serviceTypeNames" disabled
								 onchange="onServiceTypeIDChange()" onclick="onServiceTypeClick()">
								<datalist id="serviceTypeNames">
										<c:forEach items="${ServiceList}" var="trl">
											<option value="${trl.value}">${trl.value}</option>
										</c:forEach>
								</datalist>
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Prefereces</label> <select id="PreferecesName"
									class="form-control form-control-sm" placeholder="select">
									<option id="" value="NA">NA</option>
									 <c:forEach items="${preferanceList}" var="trl">
										<option value="${trl.preferanceName}">${trl.preferanceName}</option>
									 </c:forEach>
								</select>
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Main Rate</label> <input type="text" value="" id="MainRate" placeholder="Main Rate"
									class="form-control form-control-sm" readonly style="color: #000;">
							</div>
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Previus Rate</label> <input type="text" id="PreviousRate" placeholder="Previous Rate"
									class="form-control form-control-sm" readonly style="color: #000;">
							</div>
							
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>New Rate</label> <input type="text" id="nowRate" onchange="onQuantityChange()"
								placeholder="New Rate" class="form-control form-control-sm" disabled style="color: #000;"
								onkeypress="return restrictAlphabets(event)">
							</div>
							
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Quantity</label> <input type="text" id="Quantity" onkeyup="onNewRateChange()"
								placeholder="Enter Qty" class="form-control form-control-sm" disabled style="color: #000;"
								onkeypress="return restrictAlphabets(event)">
							</div>
							
							<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Amount</label> <input type="text" id="Amount" placeholder="Amount"
									class="form-control form-control-sm" readonly style="color: #000;">
							</div>
						</div>
						<div class="text-center">
							<label></label>
							<button for="validationCustom05" onclick="onAddClothClick()" id="btnAddCloth" disabled 
								class="form-control  btn btn-primary button-block mb-4 mr-2 message"
								type="button" required>Add Cloth</button>

							<div class="valid-feedback">Cloth Added Sucessfully</div>
							<div class="invalid-feedback">Please Fill All Data!</div>
						</div>

					<!--  BEGIN CONTENT AREA  -->

					<div class="table">
						<table id="example1" class="display nowrap" style="width: 100%">
							<thead>
								<tr>
									<th>SrNo</th>
									<th>Particular</th>
									<th>Service Type</th>
									<th>Prefereces</th>
									<th>Quantity</th>
									<th>Rate</th>
									<th>Amount</th>
									<!-- <th>Edit</th> -->
									<th class="no-content">Cancel</th>
								</tr>
							</thead>
							<tbody id="addClothTBody">

							</tbody>

							<tfoot>
								<tr>
									<th>SrNo</th>
									<th>Particular</th>
									<th>Service Type</th>
									<th>Prefereces</th>
									<th>Quantity</th>
									<th>Rate</th>
									<th>Amount</th>
									<!-- <th>Edit</th> -->
									<th class="no-content">Cancel</th>
								</tr>
							</tfoot>
						</table>
					</div>
				<form action="saveCompleteOrder.html" method="post" target="_blank">
					<div class="row">
						<div class="col-xl-2 col-lg-1 col-md-4 col-sm-6 mb-2">
							<label>Quantity</label>
							<input type="text" id="TotalQuantity" class="form-control form-control-sm" 
							 value="0" style="border: none;" name="totalQuantity" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Total Amount</label>
							<input type="text" id="TotalAmount" class="form-control form-control-sm" 
							 value="0" style="border: none;" name="totalAmount" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Amount Paid</label>
							<input type="text" id="AmountPaid" class="form-control form-control-sm" autocomplete="off"
							 value="0" name="amountPaid" style="background-color: #fff;"  disabled onkeypress="return restrictAlphabets(event)"
							  onchange="onAmountPaidChange()" onclick="onAmountPaidClick()">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Remaining Amt</label>
							<input type="text" id="AmtRem" class="form-control form-control-sm"
							 value="0" style="border: none;" name="amountRemaining" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Balance</label>
							<input type="text" id="NewBalance" class="form-control form-control-sm"
							 value="0" style="border: none;" name="NewBalance" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Wallet</label>
							<input type="text" id="Wallet" class="form-control form-control-sm"
							 value="0" style="border: none;" name="Wallet" readonly>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
								<label>Payment Mode</label> <select id="PreferecesName"
									class="form-control form-control-sm" name="Payment Mode">
									<option id="Cash" value="Cash">Cash</option>
									<option id="Cash" value="Wallet">Wallet</option>
									<option id="" value="Credit Card">Credit Card</option>
									<option id="" value="UPI Payment">UPI Payment</option>
									
								</select>
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Description</label>
							<input type="text" id="Description" class="form-control form-control-sm"
							 placeholder="Description"	 name="Description">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-4 col-sm-6 mb-2">
							<label>Due Date</label>
							<input type="hidden" id="cId" name="cId">
							<input type="hidden" id="actualPaid" name="actualPaid" value="0">
							<input type="hidden" id="orderId" name="orderId">
							<input type="hidden" id="InvoiceNo" name="invoiceNo" readonly> 
							<input type="hidden" id="OrderDate" name="orderDate">
							<input type="text" id="DueDate" name="dueDate" class="form-control" placeholder="Click to Select Due Date"
							 value="" style="color: green;" onchange="onDueDateChange()" disabled autocomplete="off"><!-- flatpickr flatpickr-input active -->
						</div>
						<div class="col-md-6 mb-4" style="padding-top: 30px;">
							<button class="form-control  btn btn-success button-block mb-4 mr-2" name="GetOrder"
								type="submit" disabled id="btnGetOrder" onclick="onBtnOrderSlipClick()">Get Order & Generate Order Slip</button>
						</div>
					  </div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function onBtnGetOrderClick(){
	document.getElementById("btnOrderSlip").disabled = false;
	//document.getElementById("btnGetOrder").disabled = true;
}
function onBtnOrderSlipClick(){
	//location.reload();
	//document.getElementById("btnOrderSlip").disabled = true;
	//document.getElementById("btnGetOrder").disabled = true;
	setTimeout(function(){
		   window.location.reload(1);
		}, 5000);
}
</script>
<script type="text/javascript">
			function cancelClothOrder(a){
				//alert("Okkk")
				var invoiceNo = parseInt($("#invoiceNo").val());
				var orderDate = $("#orderDate").val();
				var orderId = $("#orderId").val();
				var cId = $("#CustomerId").val(); 
				var ClothTypeID = a.children[1].textContent;
				var ServiceTypeID = a.children[2].textContent;
				var prefereces = a.children[3].textContent;
				var Quantity = parseInt(a.children[4].textContent);
				var Rate = parseInt(a.children[5].textContent);
				var Amount = parseInt(a.children[6].textContent);
				   
				   $.getJSON('CancelCurrentOrder.html', {	
						cId : cId,
						orderId : orderId,
						ClothTypeID : ClothTypeID,
						ServiceTypeID : ServiceTypeID,
						invoiceNo : invoiceNo
					});
				  
				   var TotalQuantity =parseInt($("#TotalQuantity").val());
				   var TotalAmount =parseInt($("#TotalAmount").val());
				   var AmountPaid =parseInt($("#AmountPaid").val());
				   var NewBalance =parseInt($("#NewBalance").val());
				   var NewAmt = TotalAmount - Amount;
				   $("#TotalQuantity").val(TotalQuantity - Quantity);
				   $("#TotalAmount").val(NewAmt);
				   $("#AmtRem").val(NewAmt);
				   $("#NewBalance").val(NewBalance - Amount);
				   
				   $("#AmountPaid").val(0);
				   
				   for(let i=7; i>=0; i--){
					   a.removeChild(a.childNodes[i]);
				   }
				   onAmountPaidChange();
			}
	</script>
<script type="text/javascript"> 
	   function onAddClothClick() { 
		   var cId = $("#CustomerId").val();  
		   var ClothTypeID = $("#ClothTypeID").val();  
		   var ServiceTypeID = $("#ServiceTypeID").val(); 
		   var PreferecesName = $("#PreferecesName").val(); 
		   var Rate = parseInt($("#nowRate").val());  
		   var Quantity = parseInt($("#Quantity").val()); 
		   var Amount = parseInt($("#Amount").val());
		   var invoiceNo = parseInt($("#invoiceNo").val());
		   var orderDate = $("#orderDate").val();
		   var orderId = $("#orderId").val();
		   
		   $.getJSON('saveClothOrder.html', {
			   cId : cId,
			   ClothTypeID : ClothTypeID,
			   ServiceTypeID : ServiceTypeID,
			   PreferecesName : PreferecesName,
			   Rate : Rate,
			   Quantity : Quantity,
			   Amount : Amount,
			   orderDate : orderDate,
			   orderId : orderId,
			   invoiceNo : invoiceNo
			});
			
		   var tBody = document.getElementById("addClothTBody");
		   var row = tBody.insertRow(0);
		   
		   var cell1 = row.insertCell(0); 
		   var cell2 = row.insertCell(1); 
		   var cell3 = row.insertCell(2);
		   var cell4 = row.insertCell(3); 
		   var cell5 = row.insertCell(4); 
		   var cell6 = row.insertCell(5);
		   var cell7 = row.insertCell(6);
		   var cell8 = row.insertCell(7);
		 //  var cell9 = row.insertCell(8);
		   
		   cell1.textContent = '#';
		   cell2.textContent = ClothTypeID;  
		   cell3.textContent = ServiceTypeID;
		   cell4.textContent = PreferecesName;
		   cell5.textContent = Quantity;
		   cell6.textContent = Rate;  
		   cell7.textContent = Amount;
		   
		   cell8.innerHTML = '<button id="btnCancel" class="btn bg-transparent btn-sm mb-2 mr-2" onclick="cancelClothOrder(this.parentNode.parentNode)" type="submit"><svg xmlns="http://www.w3.org/2000/svg" id="btnCancel" onclick="cancelClothOrder(this.parentNode.parentNode)" style="color:red;" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle table-cancel"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg></button>'; 
		   //cell8.innerHTML = '<a href="#custom_styles"><button class="btn btn-warning btn-sm mb-2 mr-2" type="submit"><svg xmlns="http://www.w3.org/2000/svg" id="btnCancel" onclick="cancelClothOrder(this.parentNode.parentNode)" style="color:red;" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle table-cancel"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg></button></a>';
		  // cell8.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg"  onclick="cancelClothOrder(this.parentNode.parentNode)" style="color:red;" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle table-cancel"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>';

		   var TotalQuantity =parseInt($("#TotalQuantity").val());
		   var TotalAmount =parseInt($("#TotalAmount").val());
		   var AmountPaid =parseInt($("#AmountPaid").val());
		   var NewBalance =parseInt($("#NewBalance").val());
		   var Wallet =parseInt($("#Wallet").val());
		   
		   var NewAmt = TotalAmount + Amount;
		   $("#TotalQuantity").val(TotalQuantity + Quantity);
		   $("#TotalAmount").val(NewAmt);
		   $("#AmtRem").val(NewAmt);
		   $("#NewBalance").val(Amount + NewBalance);

		  /*  var NewBalance =parseInt($("#NewBalance").val());
		   var Wallet =parseInt($("#Wallet").val());

		   if(Wallet >= NewBalance){
			   $("#Wallet").val(Wallet - NewBalance);
			   $("#AmtRem").val(0);
			   $("#NewBalance").val(0);
		   }
		   
		   if(Wallet < NewBalance){
			   $("#NewBalance").val(NewBalance - Wallet);
			   $("#AmtRem").val(NewBalance - Wallet);
			   $("#Wallet").val(0);
		   } */
		   
		   onClothTypeClick();

		   document.getElementById("AmountPaid").disabled = false;
		   document.getElementById("DueDate").disabled = false;
		   $('#ClothTypeID').focus();
		  // document.getElementById("ClothTypeID").select();
		}

	   function onNewRateChange(){
		   var Rate = parseInt($("#nowRate").val());  
		   var Quantity = parseInt($("#Quantity").val()); 
		   $("#Amount").val(Rate * Quantity);
		   
		   document.getElementById("btnAddCloth").disabled = false;
		   
		   if($("#Amount").val() == 'NaN'){
			   $("#Amount").val(0);
			   document.getElementById("btnAddCloth").disabled = true;
		   }
		   //$('#btnAddCloth').focus();
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
 <script>
 function getDate(){
	var d = new Date();
	document.getElementById("ddd").innerHTML = d.toDateString();
	/*  $("#dd").val(d); */
	 
	 var n =  new Date();
	 var y = n.getFullYear();
	 var m = n.getMonth() + 1;
	 var d = n.getDate(); 
	 
	 document.getElementById("dd").value = d + "-" + m + "-" + y;
	 document.getElementById("mdd").value = d + "-" + m + "-" + y;
	 generateRandom();
 }
</script>
<script type="text/javascript"> 
	   function generateRandom() { 
		   var num1 = Math.floor(Math.random() * (999 - 100 + 1) ) + (100);
		   var num2 = Math.floor(Math.random() * (999 - 100 + 1) ) + (100);
		   var r=num1;
		   r = r + "";
		   var x = r.split("").reverse().join("");
		   $("#orderId").val("PC"+(num1)+(num2));
	}
</script>         
</body>
</html><!-- private String pattern = "dd-MM-yyyy";
String dateInString =new SimpleDateFormat(pattern).format(new Date()); -->