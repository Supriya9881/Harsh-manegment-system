<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
        
    <!-- BEGIN PAGE LEVEL CUSTOM STYLES -->
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/datatables.css">
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/custom_dt_html5.css">
    <link rel="stylesheet" type="text/css" href="plugins/table/datatable/dt-global_style.css">
</head>
<body>
	<div class="container-fluid pt-1">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Shop Details Master</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<form action="SaveShopNameDetails.html" class="needs-validation"
							novalidate method="post">
							<div class="form-row">
								<div class="col-md-3 mb-4">
									<label for="validationCustom01">Shop Name</label> <input type="text"
										class="form-control" id="" name="shopName"
										onkeypress="return restrictnumbers(event)" autocomplete="off"
										placeholder="Enter Shop Name" value="" required>
								</div>
								<div class="col-md-6 mb-4">
									<label for="">Address</label> <input
										type="text" autocomplete="off" class="form-control" id=""
										name="shopAddress" maxlength="" placeholder="Enter Shop Address" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">GST No</label> <input type="text"
										class="form-control" id="" autocomplete="off" placeholder="Enter GST No"
										name="gstNo" required>
								</div>
							</div>

							<div class="text-right">
								<button for="validationCustom05"
									class="form-control btn btn-primary mb-2 mr-2" type="submit"
									required id="btnSaveCustomer" name="btnSaveShopDetails">
									<svg width="1em" height="1em" viewBox="0 0 16 16"
										class="bi bi-inbox" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											fill-rule="evenodd"
											d="M4.98 4a.5.5 0 0 0-.39.188L1.54 8H6a.5.5 0 0 1 .5.5 1.5 1.5 0 1 0 3 0A.5.5 0 0 1 10 8h4.46l-3.05-3.812A.5.5 0 0 0 11.02 4H4.98zm9.954 5H10.45a2.5 2.5 0 0 1-4.9 0H1.066l.32 2.562a.5.5 0 0 0 .497.438h12.234a.5.5 0 0 0 .496-.438L14.933 9zM3.809 3.563A1.5 1.5 0 0 1 4.981 3h6.038a1.5 1.5 0 0 1 1.172.563l3.7 4.625a.5.5 0 0 1 .105.374l-.39 3.124A1.5 1.5 0 0 1 14.117 13H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .106-.374l3.7-4.625z" />
                                              </svg>
									Save Shop Details
								</button>

								<div class="valid-feedback">Customer Added Sucessfully</div>
								<div class="invalid-feedback">Please Fill All Data!</div>
							</div>
							<div class="form-row">
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-primary mb-4 mr-2"
										type="submit" id="btnUpdateCustomer" required
										style="display: none;" name="btnUpdateCustomer">
										<svg width="1em" height="1em"
											xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
											viewBox="0 0 550 550" fill="currentColor">
                                                <g> <path
												d="M106.6,149.3V42.7h279.2l62.2,62.2v364.5l-106.7,0V512l149.3,0V87.2L403.5,0H64v149.3H106.6L106.6,149.3z M341.3,21.3v128
                                                        h128v-42.7l-85.3,0V21.3H341.3L341.3,21.3z M192,490.7V512c94.3,0,170.7-76.4,170.7-170.7c0-94.3-76.4-170.7-170.7-170.7
                                                        c-94.3,0-170.7,76.4-170.7,170.7C21.3,435.6,97.7,512,192,512V490.7v-21.3c-35.4,0-67.3-14.3-90.5-37.5
                                                        C78.3,408.6,64,376.8,64,341.3c0-35.4,14.3-67.3,37.5-90.5c23.2-23.2,55.1-37.5,90.5-37.5c35.4,0,67.3,14.3,90.5,37.5
                                                        c23.2,23.2,37.5,55.1,37.5,90.5c0,35.4-14.3,67.3-37.5,90.5c-23.2,23.2-55.1,37.5-90.5,37.5V490.7z M132.4,367.1l59.6-59.6
                                                        l59.6,59.6l30.2-30.2L192,247.2l-89.8,89.8L132.4,367.1L132.4,367.1z M170.7,288v138.7h42.7V288H170.7z" />
                                                </g>
                                            </svg>
										<b> Update</b>
									</button>
								</div>
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-success mb-4 mr-2"
										id="btnDeleteCustomer" type="submit" required
										style="display: none;" name="btnDeleteCustomer">
										<svg width="1em" height="1em" viewBox="0 0 18 18"
											class="bi bi-trash" fill="currentColor"
											xmlns="http://www.w3.org/2000/svg">
                                                <path
												d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                                <path
												fill-rule="evenodd"
												d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                              </svg>
										<b> Delete</b>
									</button>
								</div>
							</div>
						</form>
						<!--  BEGIN CONTENT AREA  -->
						<div class="table pt-4">
							<table id="html5-extension" class="display" style="width: 100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>Shop Name</th>
										<th>Address</th>
										<th>GST No</th>
									</tr>
								</thead>
								<!-- CustomerList -->
								<tbody id="customerDetails">
									<c:forEach var="v" items="${ShopNameList}">
										<tr onclick="onCustomerTrClick(this)">
											<td class="text-center"><c:out value="${v.shopId}"></c:out></td>
											<td><c:out value="${v.shopName}"></c:out></td>
											<td><c:out value="${v.shopAddress}"></c:out></td>
											<td><c:out value="${v.gstNo}"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>

								<tfoot>
									<tr>
										<th>ID</th>
										<th>Shop Name</th>
										<th>Address</th>
										<th>GST No</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="container-fluid pt-1">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Owner Details Master</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<form action="SaveOwnerDetails.html" class="needs-validation"
							novalidate method="post">
							<div class="form-row">
								<div class="col-md-3 mb-4">
									<label for="validationCustom01">Owner Name</label> <input type="text"
										class="form-control" id="" name="ownerName"
										onkeypress="return restrictnumbers(event)" autocomplete="off"
										placeholder="Enter Owner Name" value="" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">Contact Number</label> <input
										onkeypress="return restrictAlphabets(event)" type="text"
										autocomplete="off" class="form-control" id="" pattern=""
										name="ownerContact" maxlength="" placeholder="Enter Contact" value="" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">Email</label> <input
										type="text" autocomplete="off" class="form-control" id="" pattern=""
										name="ownerEmail" maxlength="" placeholder="Enter Email" value="" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">Address</label> <input type="text"
										class="form-control" id="" autocomplete="off" placeholder="Enter Address"
										name="ownerAddress" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">User Name</label> <input type="text"
										class="form-control" id="" autocomplete="off" placeholder="Enter User Name"
										name="ownerUserName" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">Password</label> <input type="text"
										class="form-control" id="" autocomplete="off" placeholder="Enter Password"
										name="ownerPassword" required>
								</div>
								<div class="col-md-3 mb-4">
									<label for="">Select Shop</label> <select id=""
										class="form-control form-control-sm" name="ShopNameId">
										<option id="" value="">---Select Shop---</option>
										<c:forEach items="${ShopNameList}" var="trl">
											<option value="${trl.shopId}">${trl.shopName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="text-right">
								<button for="validationCustom05"
									class="form-control btn btn-primary mb-2 mr-2" type="submit"
									required id="btnSaveCustomer" name="btnSaveOwnerDetails">
									<svg width="1em" height="1em" viewBox="0 0 16 16"
										class="bi bi-inbox" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											fill-rule="evenodd"
											d="M4.98 4a.5.5 0 0 0-.39.188L1.54 8H6a.5.5 0 0 1 .5.5 1.5 1.5 0 1 0 3 0A.5.5 0 0 1 10 8h4.46l-3.05-3.812A.5.5 0 0 0 11.02 4H4.98zm9.954 5H10.45a2.5 2.5 0 0 1-4.9 0H1.066l.32 2.562a.5.5 0 0 0 .497.438h12.234a.5.5 0 0 0 .496-.438L14.933 9zM3.809 3.563A1.5 1.5 0 0 1 4.981 3h6.038a1.5 1.5 0 0 1 1.172.563l3.7 4.625a.5.5 0 0 1 .105.374l-.39 3.124A1.5 1.5 0 0 1 14.117 13H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .106-.374l3.7-4.625z" />
                                              </svg>
									Save Owner Details
								</button>

								<div class="valid-feedback">Customer Added Sucessfully</div>
								<div class="invalid-feedback">Please Fill All Data!</div>
							</div>
							<div class="form-row">
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-primary mb-4 mr-2"
										type="submit" id="btnUpdateCustomer" required
										style="display: none;" name="btnUpdateCustomer">
										<svg width="1em" height="1em"
											xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
											viewBox="0 0 550 550" fill="currentColor">
                                                <g> <path
												d="M106.6,149.3V42.7h279.2l62.2,62.2v364.5l-106.7,0V512l149.3,0V87.2L403.5,0H64v149.3H106.6L106.6,149.3z M341.3,21.3v128
                                                        h128v-42.7l-85.3,0V21.3H341.3L341.3,21.3z M192,490.7V512c94.3,0,170.7-76.4,170.7-170.7c0-94.3-76.4-170.7-170.7-170.7
                                                        c-94.3,0-170.7,76.4-170.7,170.7C21.3,435.6,97.7,512,192,512V490.7v-21.3c-35.4,0-67.3-14.3-90.5-37.5
                                                        C78.3,408.6,64,376.8,64,341.3c0-35.4,14.3-67.3,37.5-90.5c23.2-23.2,55.1-37.5,90.5-37.5c35.4,0,67.3,14.3,90.5,37.5
                                                        c23.2,23.2,37.5,55.1,37.5,90.5c0,35.4-14.3,67.3-37.5,90.5c-23.2,23.2-55.1,37.5-90.5,37.5V490.7z M132.4,367.1l59.6-59.6
                                                        l59.6,59.6l30.2-30.2L192,247.2l-89.8,89.8L132.4,367.1L132.4,367.1z M170.7,288v138.7h42.7V288H170.7z" />
                                                </g>
                                            </svg>
										<b> Update</b>
									</button>
								</div>
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-success mb-4 mr-2"
										id="btnDeleteCustomer" type="submit" required
										style="display: none;" name="btnDeleteCustomer">
										<svg width="1em" height="1em" viewBox="0 0 18 18"
											class="bi bi-trash" fill="currentColor"
											xmlns="http://www.w3.org/2000/svg">
                                                <path
												d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                                <path
												fill-rule="evenodd"
												d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                              </svg>
										<b> Delete</b>
									</button>
								</div>
							</div>
						</form>
						<!--  BEGIN CONTENT AREA  -->
						<div class="table pt-4">
							<table id="example" class="display" style="width: 100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>Owner Name</th>
										<th>Contact</th>
										<th>Email</th>
										<th>Address</th>
										<th>User Name</th>
										<th>Password</th>
										<th>Company Name</th>
									</tr>
								</thead>
								<tbody id="">
									<c:forEach var="v" items="${OwnerNameList}">
										<tr onclick="onCustomerTrClick(this)">
											<td class="text-center"><c:out value="${v.ownerId}"></c:out></td>
											<td><c:out value="${v.ownerName}"></c:out></td>
											<td><c:out value="${v.ownerContact}"></c:out></td>
											<td><c:out value="${v.ownerEmail}"></c:out></td>
											<td><c:out value="${v.ownerAddress }"></c:out></td>
											<td><c:out value="${v.ownerUserName }"></c:out></td>
											<td><c:out value="${v.ownerPassword }"></c:out></td>
											<td><c:out value="${v.ShopName }"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>

								<tfoot>
									<tr>
										<th>ID</th>
										<th>Owner Name</th>
										<th>Contact</th>
										<th>Email</th>
										<th>Address</th>
										<th>User Name</th>
										<th>Password</th>
										<th>Company Name</th>
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

    <!-- BEGIN PAGE LEVEL CUSTOM SCRIPTS -->
    <script src="plugins/table/datatable/datatables.js"></script>
    <!-- NOTE TO Use Copy CSV Excel PDF Print Options You Must Include These Files  -->
    <script src="plugins/table/datatable/button-ext/dataTables.buttons.min.js"></script>
    <script src="plugins/table/datatable/button-ext/jszip.min.js"></script>    
    <script src="plugins/table/datatable/button-ext/buttons.html5.min.js"></script>
    <script src="plugins/table/datatable/button-ext/buttons.print.min.js"></script>
	<script>
        $('#html5-extension').DataTable( {
            dom: '<"row"<"col-md-12"<"row"<"col-md-6"B><"col-md-6"f> > ><"col-md-12"rt> <"col-md-12"<"row"<"col-md-5"i><"col-md-7"p>>> >',
            buttons: {
                buttons: [
                    { extend: 'excel', className: 'btn' },
                    { extend: 'print', className: 'btn' }
                ]
            }
        } );
    </script>
</body>
</html>