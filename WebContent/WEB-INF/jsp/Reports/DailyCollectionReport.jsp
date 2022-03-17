<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	   //$("#DueDate").datepicker({ dateFormat: "dd-mm-yy" }).val()
       });
   </script>

<script type="text/javascript">
	function getCurrDate(){
		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();
		var output = (day<10 ? '0' : '') + day + '-' + (month<10 ? '0' : '') + month + '-' + d.getFullYear();
		document.getElementById("orderDate").value = output;

	}
</script>
</head>
<body onload="getCurrDate()">
<div class="container pt-4">
		<div class="row">
			<div id="custom_styles" class="col-md-12 layout-spacing col-md-12">
				<div class="statbox widget box box-shadow">
					<div class="widget-content widget-content-area">
					  <form action="getDailyCollectionReport.html" target="_blank" method="get">
						<div class="form-row">
							<div class="col-md-4 mb-4">
								<label for="mobileNo">Select Date</label> 
									<input id="orderDate" class="form-control flatpickr flatpickr-input active"
									type="text" placeholder="Select Date.." style="color: green;" autocomplete="off"
									required name="dateForDailyCollection">
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
	 
        var f2 = flatpickr(document.getElementById('dateTimeFlatpickr'), {
            enableTime: true,
            dateFormat: "d-m-y H:i",
        });
    </script>
</body>
</html>