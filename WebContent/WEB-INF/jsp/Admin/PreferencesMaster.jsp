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
>
</head>
<body>
	<div id="" class="main-content">
		<div class="row">
			<div id="custom-styles"
				class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 offset-4">
				<div class="statbox widget box box-shadow">
					<div class="widget-header">
						<div class="row">
							<div class="col-xl-12 col-md-12 col-sm-12 col-12">
								<h4>Preferences</h4>
							</div>
						</div>
					</div>
					<div class="widget-content widget-content-area">
						<form action="SaveUpdateDeletePreferanceType.html" method="post">
							<div class="form-row">
								<div class="col-md-12 mb-4">
									<label>Enter Preferences</label> <input type="text"
										name="preferanceName" id="PreferanceName" class="form-control"
										placeholder="New Item" value="" required> <input
										type="hidden" name="preferanceId" id="preferanceId"
										class="form-control" placeholder="Id">
								</div>
								<div class="col-md-12 mb-4">
									<label>Enter Price</label> <input type="text"
										name="preferancePrice" id="PreferancePrice"
										class="form-control" placeholder="Price" value="" required
										onkeypress="return restrictAlphabets(event)">
								</div>
							</div>
							<p style="color: red;">${errorMsgPreferance}</p>
							<div class="text-right">
								<button for="validationCustom05"
									class="form-control btn btn-primary mb-2 mr-2" type="submit"
									required id="btnAdd" name="btnAdd">
									<svg width="1em" height="1em" viewBox="0 0 19 19"
										class="bi bi-plus-square" fill="currentColor"
										xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd"
											d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
                                            <path fill-rule="evenodd"
											d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                        </svg>
									Add
								</button>
							</div>
							<div class="form-row">
								<div class="col-sm-6 mb-4">
									<button class="form-control  btn btn-primary mb-4 mr-2"
										type="submit" id="btnUpdate" required style="display: none;"
										name="btnUpdate">
										<svg width="1em" height="1em"
											xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
											viewBox="0 0 550 550" fill="currentColor">
                                                <g>
                                                    <path
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
										id="btnDelete" type="submit" required style="display: none;"
										name="btnDelete">
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
						<div class="table pt-4">
							<table id="example" class="display table-hover"
								style="width: 100%;">
								<thead>
									<tr>
										<th>Sr No</th>
										<th>Preferences</th>
										<th>Price</th>
									</tr>
								</thead>
								<!-- preferanceList -->
								<tbody id="pMaster">
									<!-- document.getElementById('Update').style.display='block' -->
									<c:forEach var="v" items="${preferanceList}">
										<tr onclick="onTrClick(this)">
											<td><c:out value="${v.id }"></c:out></td>
											<td><c:out value="${v.preferanceName }"></c:out></td>
											<td><c:out value="${v.preferancePrice }"></c:out></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Sr No</th>
										<th>Preferences</th>
										<th>Price</th>
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
	function onTrClick(a){
		$("#preferanceId").val(a.children[0].textContent);
		$("#PreferanceName").val(a.children[1].textContent);
		$("#PreferancePrice").val(a.children[2].textContent);
		document.getElementById('btnAdd').style.display='none'
		document.getElementById('btnUpdate').style.display='block'
		document.getElementById('btnDelete').style.display='block'
		
		$('#PreferanceName').focus();
	}
</script>
	<script>
        $(document).ready(function() {
            App.init();
        });
    </script>
	<script src="assets/js/custom.js"></script>
	<!-- END GLOBAL MANDATORY SCRIPTS -->

	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="plugins/apex/apexcharts.min.js"></script>
	<script src="assets/js/dashboard/dash_1.js"></script>
	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="assets/js/jquery.dataTables.min.js"></script>
</body>
</html>