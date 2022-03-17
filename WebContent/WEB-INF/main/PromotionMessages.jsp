<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
#the-count {
	float: right;
	padding: 0.1rem 0 0 0;
	font-size: 0.875rem;
}
</style>
<script type="text/javascript">
	function onTextKeyUp() {
		var characterCount = $('#the-textarea').val().length, current = $('#current'), maximum = $('#maximum'), theCount = $('#the-count');
		current.text(characterCount);

		/*This isn't entirely necessary, just playin around*/
		if (characterCount < 70) {
			current.css('color', '#666');
		}
		if (characterCount > 70 && characterCount < 90) {
			current.css('color', '#6d5555');
		}
		if (characterCount > 90 && characterCount < 100) {
			current.css('color', '#793535');
		}
		if (characterCount > 100 && characterCount < 120) {
			current.css('color', '#841c1c');
		}
		if (characterCount > 120 && characterCount < 139) {
			current.css('color', '#8f0001');
		}

		if (characterCount >= 140) {
			maximum.css('color', '#8f0001');
			current.css('color', '#8f0001');
			theCount.css('font-weight', 'bold');
		} else {
			maximum.css('color', '#666');
			theCount.css('font-weight', 'normal');
		}
	}
</script>
<script type="text/javascript">
	function onSelctClick() {
		var checkBox1 = document.getElementById("cbAll");
		if (checkBox1.checked == true) {
			//alert("checked");
			var items = document.getElementsByName('SelectedId');
			for (var i = 0; i < items.length; i++) {
				if (items[i].type == 'checkbox')
					items[i].checked = true;
			}
		} else {
			//alert("unchecked");
			var items = document.getElementsByName('SelectedId');
			for (var i = 0; i < items.length; i++) {
				if (items[i].type == 'checkbox')
					items[i].checked = false;
			}
		}
	}
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
								<h4>Promotional</h4>
							</div>
						</div>
					</div>

					<div class="widget-content widget-content-area">
						<form action="sendPromotionMessage.html" class="needs-validation" novalidate method="post">
							<div class="form-row">
								<div class="col-md-8 mb-4">
									<label for="validationCustom01">Enter Greeting Message</label>
									<div class="wrapper">
										<textarea name="msg" id="the-textarea" required
											class="form-control" rows="4" maxlength="300"
											placeholder="Start Typing..." onkeyup="onTextKeyUp()"
											autofocus></textarea>
										<div id="the-count">
											<span id="current">0</span> <span id="maximum">/ 300</span>
										</div>
									</div>
								</div>
							</div>

							<!--  BEGIN CONTENT AREA  -->
							<div class="table pt-0">
								<table id="example" class="display" style="width: 100%">
									<thead>
										<tr>
											<th class="text-center"><input type="checkbox"
												onclick="onSelctClick()" id="cbAll" value="Select All">
												Select All</th>
											<th style="display: none;">ID</th>
											<th>Name</th>
											<th>Contact</th>
											<th>Address</th>

										</tr>
									</thead>
									<!-- CustomerList -->
									<tbody id="customerDetails">
										<c:forEach var="v" items="${CustomerList}">
											<tr onclick="onCustomerTrClick(this)">
												<td class="text-center"><input type='checkbox'
													onclick='onCheckBoxClick(this.parentNode.parentNode)'
													value='${v.cMobile}' name='SelectedId' id='CheckBox"+i+"'></td>
												<td style="display: none;"><c:out value="${v.cId}"></c:out></td>
												<td><c:out value="${v.cName}"></c:out></td>
												<td><c:out value="${v.cMobile}"></c:out></td>
												<td><c:out value="${v.cAddress}"></c:out></td>
											</tr>
										</c:forEach>
									</tbody>

									<tfoot>
										<tr>
											<th class="text-center">Select ALl</th>
											<th style="display: none;">ID</th>
											<th>Name</th>
											<th>Contact</th>
											<th>Address</th>
										</tr>
									</tfoot>
								</table>
							</div>
							<div class="form-row">
								<div class="col-sm-12 mb-4">
									<button class="form-control  btn btn-primary mb-4 mr-2"
										type="submit" id="btnSendToSelectedCustomer" required
										name="btnSendToSelectedCustomer">
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
										<b> Send Message</b>
									</button>
								</div>
								<!-- <div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-success mb-4 mr-2"
										id="btnSendToAllCustomer" type="submit" required
										name="btnSendToAllCustomer">
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
										<b> Send Message to All Customer</b>
									</button>
								</div> -->
							</div>
						</form>
					</div>
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
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!--  BEGIN CUSTOM SCRIPTS FILE  -->
	<script src="assets/js/scrollspyNav.js"></script>
	<script
		src="assets/js/forms/bootstrap_validation/bs_validation_script.js"></script>
	<!--  END CUSTOM SCRIPTS FILE  -->

	<script src="assets/js/jquery.dataTables.min.js"></script>
</body>
</html>