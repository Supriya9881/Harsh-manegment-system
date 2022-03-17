<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$('#mobileNo').autocomplete(
			{
				source : 'searchCustomerMobileNo.html'
			});
		});
</script>
</head>
<body>
	<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-content widget-content-area">
					  <form action="getMobileNoWiseReport.html" target="_blank">
						<div class="form-row">
							<div class="col-md-6 mb-4">
								<label for="mobileNo">Contact Number</label> <input
									type="text" class="form-control" id="mobileNo" autocomplete="off"
									name="mobileNo" placeholder="Enter Mobile Number" value="" required>
								<div class="valid-feedback">Looks good!</div>
								<div class="invalid-feedback">Please Enter!</div>
							</div>
						</div>
						<div class="text-right">
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
						</div>
					  </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	 <script>
	 function onGetReportClick(){
			setTimeout(function(){
				   window.location.reload(1);
				}, 5000);
		}
	</script>
</body>
</html>