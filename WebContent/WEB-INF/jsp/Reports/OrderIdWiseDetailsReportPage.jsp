<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-content widget-content-area">
					  <form action="generateOrderDetailsReport.html" target="_blank">
						<div class="form-row">
							<div class="col-md-6 mb-4">
								<label for="mobileNo">Enter Order Id or Invoice No( Or Scan Barcode...)</label> <input
									type="text" class="form-control" id="orderId" autocomplete="off"
									name="orderId" placeholder="Enter Order Id or Invoice No" value="" required>
								<div class="valid-feedback">Looks good!</div>
								<div class="invalid-feedback">Please Enter!</div>
							</div>
						</div>
							<div class="form-row">
								<div class="col-md-6 mb-4">
									<button onclick="onGetReportClick()"
										class="form-control btn btn-success mb-2 mr-2" type="submit"
										name="btnGetReport">Get Report</button>
								</div>
								<div class="col-md-6 mb-4">
									<button onclick="onDuplicateOrderSlipClick()"
										class="form-control btn btn-primary mb-2 mr-2" type="submit"
										name="btnGetDuplicateSlip">Get Duplicate Order Slip</button>
								</div>
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
				}, 4000);
		}
	 function onDuplicateOrderSlipClick(){
			setTimeout(function(){
				   window.location.reload(1);
				}, 4000);
		}
	</script>
</body>
</html>