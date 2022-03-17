<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"> 
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(function() {
		$("#fromDate").datepicker({
			dateFormat : "dd-mm-yy"
		}).val()
		$("#toDate").datepicker({
			dateFormat : "dd-mm-yy"
		}).val()
	});

	function onEndDateChange() {
		var startDate = document.getElementById("fromDate").value;
		var endDate = document.getElementById("toDate").value;

		if (startDate > endDate) {
			alert("End date should be greater than Start date");
			document.getElementById("toDate").value = "";
		}
	}
</script>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/datatables.css">
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/dt-global_style.css">
    <!-- END PAGE LEVEL STYLES -->

    <style>
        .table-responsive > .table {
            background: transparent;
        }
    </style>
</head>
<body>
	<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-content widget-content-area">
						<form action="getFromToDateReport.html" target="_blank"
							method="post">
							<div class="form-row">
								<div class="col-md-6 mb-4">
									<label for="mobileNo">Select From Date</label> <input
										id="fromDate"
										class="form-control flatpickr flatpickr-input active"
										type="text" placeholder="Select From Date.."
										style="color: green;" required name="fromDate"
										autocomplete="off">
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Enter!</div>
								</div>
								<div class="col-md-6 mb-4">
									<label for="mobileNo">Select To Date</label> <input id="toDate"
										class="form-control flatpickr flatpickr-input active"
										type="text" placeholder="Select To Date.."
										style="color: green;" required name="toDate"
										autocomplete="off" onchange="onEndDateChange()">
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Enter!</div>
								</div>
							</div>
							<!-- <div class="text-right">
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
							<div class="valid-feedback">Customer Added Sucessfully</div>
							<div class="invalid-feedback">Please Fill All Data!</div>
						</div> -->
						</form>

						<div class="table">
							<table id="range-search" class="display" style="width: 100%">
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

	<script>
		function onGetReportClick() {
			setTimeout(function() {
				window.location.reload(1);
			}, 5000);
		}

		var f2 = flatpickr(document.getElementById('dateTimeFlatpickr'), {
			enableTime : true,
			dateFormat : "d-m-y H:i",
		});
	</script>
	
 <!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
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

    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="plugins/table/datatable/datatables.js"></script>
    <script>
        /* Custom filtering function which will search data in column four between two values */
        $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                var min = parseInt( $('#fromDate').val(), 10 );
                var max = parseInt( $('#toDate').val(), 10 );
                var age = ( data[4] ) || 0; // use data for the age column
         
                if ( ( isNaN( min ) && isNaN( max ) ) ||
                     ( isNaN( min ) && age <= max ) ||
                     ( min <= age   && isNaN( max ) ) ||
                     ( min <= age   && age <= max ) )
                {
                    return true;
                }
                return false;
            }
        );         
        $(document).ready(function() {
            var table = $('#range-search').DataTable({
                "oLanguage": {
                    "oPaginate": { "sPrevious": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>', "sNext": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>' },
                    "sInfo": "Showing page _PAGE_ of _PAGES_",
                    "sSearch": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
                    "sSearchPlaceholder": "Search...",
                   "sLengthMenu": "Results :  _MENU_",
                },
                "stripeClasses": [],
                "lengthMenu": [7, 10, 20, 50],
                "pageLength": 7 
            });             
            // Event listener to the two range filtering inputs to redraw on input
            $('#fromDate, #toDate').keyup( function() { table.draw(); } );
        } );
    </script>
    <!-- END PAGE LEVEL SCRIPTS -->
</body>
</html>