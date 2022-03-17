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
<script type="text/javascript">
function check()
{

    var mobile = document.getElementById('mobile');
   
    
    var message = document.getElementById('message');

   var goodColor = "#0C6";
    var badColor = "#FF9B37";
  
    if(mobile.value.length!=10){
       
        mobile.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "required 10 digits, match requested format!"
    }}
</script>
</head>
<body>
	<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Add Customer</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<form action="SaveNewCustomer.html" class="needs-validation"
							novalidate method="post">
							<div class="row">
								<div class="col-sm-2 sm-2">
									<input type="radio" id="validationCustom04" name="gender"
										value="Mr." required checked> <label
										for="validationCustom04">Mr</label><br>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Select!</div>
								</div>
								<div class="col-sm-2 sm-2">
									<input type="radio" id="female" name="gender" value="Mrs.">
									<label for="Mrs.">Mrs</label><br>
								</div>
								<input type="hidden" class="form-control" id="cId2" name="cId2">
								<!-- <div class="col-sm-2 sm-2">
									<input type="radio" id="female" name="gender" value="ufemale">
									<label for="female">Miss</label><br>
								</div> -->
							</div>
							<div class="form-row">
								<div class="col-md-4 mb-4">
									<label for="validationCustom01">Name</label> <input type="text"
										class="form-control" id="cName" name="cName"
										onkeypress="return restrictnumbers(event)" autocomplete="off"
										placeholder="Enter Name" value="" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please enter!</div>
								</div>
								<div class="col-md-4 mb-4">
									<label for="validationCustom02">Contact Number</label> <input
										onkeypress="return restrictAlphabets(event)" type="text"
										autocomplete="off" class="form-control" id="cMobile"
										pattern="[56789][0-9]{9}" name="cMobile" maxlength="10"
										placeholder="Mobile Number" value="" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Enter Correct
										Format!</div>
								</div>
								<div class="col-md-4 mb-4">
									<label for="validationCustom03">Address</label> <input
										type="text" class="form-control" id="cAddress"
										autocomplete="off" placeholder="Area" name="cAddress" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Enter!</div>
								</div>
								
								<!-- <div class="col-md-4 mb-4">
									<label for="validationCustom03">Mobile</label> <input name=""  id="mobile" type="number" required onkeyup="check(); return false;" ><span id="message"></span>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please Enter!</div>
								</div> -->
							</div>

							<div class="text-right">
								<button for="validationCustom05"
									class="form-control btn btn-primary mb-2 mr-2" type="submit"
									required id="btnSaveCustomer" name="btnSaveCustomer">
									<svg width="1em" height="1em" viewBox="0 0 16 16"
										class="bi bi-inbox" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                                <path
											fill-rule="evenodd"
											d="M4.98 4a.5.5 0 0 0-.39.188L1.54 8H6a.5.5 0 0 1 .5.5 1.5 1.5 0 1 0 3 0A.5.5 0 0 1 10 8h4.46l-3.05-3.812A.5.5 0 0 0 11.02 4H4.98zm9.954 5H10.45a2.5 2.5 0 0 1-4.9 0H1.066l.32 2.562a.5.5 0 0 0 .497.438h12.234a.5.5 0 0 0 .496-.438L14.933 9zM3.809 3.563A1.5 1.5 0 0 1 4.981 3h6.038a1.5 1.5 0 0 1 1.172.563l3.7 4.625a.5.5 0 0 1 .105.374l-.39 3.124A1.5 1.5 0 0 1 14.117 13H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .106-.374l3.7-4.625z" />
                                              </svg>
									Save Customer
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
										<th>Name</th>
										<th>Contact</th>
										<th>Address</th>
										<th>Balance Rs</th>
										<th>Wallet Balance</th>
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
										<th>Order</th>
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
		function onCustomerTrClick(a) {
			$("#cId2").val(a.children[0].textContent);
			$("#cName").val(a.children[1].textContent);
			$("#cMobile").val(a.children[2].textContent);
			$("#cAddress").val(a.children[3].textContent);

			document.getElementById('btnSaveCustomer').style.display = 'none';
			document.getElementById('btnUpdateCustomer').style.display = 'block';
			document.getElementById('btnDeleteCustomer').style.display = 'block';

			$('#cName').focus();
		}
	</script>

	<script type="text/javascript">
		
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

	<script src="assets/js/jquery.dataTables.min.js"></script>

</body>
</html>