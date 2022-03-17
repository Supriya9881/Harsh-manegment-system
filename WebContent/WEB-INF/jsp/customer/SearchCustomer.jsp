<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="assets/css/scrollspyNav.css" rel="stylesheet"
	type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!--  BEGIN CUSTOM STYLE FILE  -->
<link href="assets/css/elements/search.css" rel="stylesheet"
	type="text/css" />

    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/datatables.css">
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/dt-global_style.css">
    <!-- END PAGE LEVEL STYLES -->

<script type="text/javascript">
$(document).ready(function() {
	$('#CustomerContact').autocomplete(
		{
			source : 'searchCustomerContactAutoComplete.html'
		});
	});
</script>
</head>
<body>
	<div id="searchLive" class="col-xl-12 col-md-12 col-sm-12 col-12">
		<div class="statbox widget box box-shadow">
			<div
				class="widget-content widget-content-area text-center tags-content">

				<div class="row">
					<div
						class="col-lg-8 col-md-8 col-sm-9 filtered-list-search mx-auto">
						<div id="radio" class="container">
							<h5>By Name,Contact,Invoice Number or Barcode Scanner</h5>

							<!-- <div class="custom-control custom-checkbox checkbox-info">
								<input type="checkbox" class="custom-control-input" id="rChkbox">
								<label class="custom-control-label" for="rChkbox"><h5></h5></label>
							</div> -->
						</div>
						<form class="form-inline my-2 my-lg-0 justify-content-center"
							action="CustomerDetailsPage.html">
							<div class="w-100">
								<input type="text" required autofocus
									class="w-100 form-control product-search br-30 p-4"
									id="CustomerContact" autocomplete="off" name="CustomerContact"
									style="border-bottom: 4px solid #000; border-top: 1px solid #000;"
									placeholder="Search... by Customer Name, Customer Mobile No, Invoice No, Order Id or Scan By Barcode"
									onkeyup="onCustomerContactKeyUp()">
								<button type="submit" class="btn btn-primary">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round" class="feather feather-search">
										<circle cx="11" cy="11" r="8"></circle>
										<line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
								</button>
							</div>
						</form>
						<h5 class="text-danger mt-2">${mgsNotFound}</h5>
						<!-- Customer Not Found Plz Add New Customer -->
					</div>
					<div class="col-lg-12 col-md-12">
						<div class="searchable-container">
							<a href="AddCustomerPage.html">
								<button class="btn btn-success mb-2 mt-0" type="submit">
									<svg width="1em" height="1em" viewBox="0 0 19 19"
										class="bi bi-person-plus-fill" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                            <path
											fill-rule="evenodd"
											d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z" />
                                                        </svg>
									Add Customer
								</button>
							</a>
						</div>
					</div>
				</div>

				<div class="table pt-4">
					<table id="example" class="display" style="width: 100%">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Contact</th>
								<th>Address</th>
								<th>Balance Rs</th>
								<th>Wallet Balance</th>
								<th>All Orders</th>
								<th>Get Order</th>
							</tr>
						</thead>
						<!-- CustomerList -->
						<tbody id="customerDetails">
							<c:forEach var="v" items="${CustomerList}">
								<tr onclick="onCustomerTrClick(this)">
									<td class="text-center"><c:out value="${v.cId}"></c:out></td>
									<td><c:out value="${v.cName}"></c:out></td>
									<td><c:out value="${v.cMobile}"></c:out></td>
									<td><c:out value="${v.cAddress}"></c:out></td>
									<td class="text-center"><c:out value="${v.cAmount }"></c:out></td>
									<td class="text-center"><c:out value="${v.cWallet }"></c:out></td>
									<td><a href="CustomerDetailsPage.html?CustomerContact=${v.cMobile}"><button
												class="btn btn-secondary btn-sm mb-2 mr-2" type="submit">All
												Orders</button></a></td>
									<td><a href="GetOrderPage.html?customerId=${v.cId}"><button
												class="btn btn-primary btn-sm mb-2 mr-2" type="submit">Get
												Order</button></a></td>
									
								</tr>
							</c:forEach>
						</tbody>

						<tfoot>
							<tr>
								<th class="checkbox-column">ID</th>
								<th>Name</th>
								<th>Contact</th>
								<th>Address</th>
								<th>Balance Rs</th>
								<th>Wallet Balance</th>
								<th>All Orders</th>
								<th>Order</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
        $(document).ready(function() {
            App.init();
        });
    </script>
	<script src="plugins/highlight/highlight.pack.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY STYLES -->

	<script src="assets/js/elements/custom-search.js"></script>

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/js/scrollspyNav.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	    <script src="assets/js/libs/jquery-3.1.1.min.js"></script>
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
        $('#default-ordering').DataTable( {
            "oLanguage": {
                "oPaginate": { "sPrevious": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>', "sNext": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>' },
                "sInfo": "Showing page _PAGE_ of _PAGES_",
                "sSearch": '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>',
                "sSearchPlaceholder": "Search...",
               "sLengthMenu": "Results :  _MENU_",
            },
            "order": [[ 3, "desc" ]],
            "stripeClasses": [],
            "lengthMenu": [7, 10, 20, 50],
            "pageLength": 7,
            drawCallback: function () { $('.dataTables_paginate > .pagination').addClass(' pagination-style-13 pagination-bordered mb-5'); }
	    } );
    </script>
</body>
</html>