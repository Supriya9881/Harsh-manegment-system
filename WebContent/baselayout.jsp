<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="icon" type="image/x-icon" href="assets/img/favicon.png" />
<link href="assets/css/loader.css" rel="stylesheet" type="text/css" />
<script src="assets/js/loader.js"></script>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700"
	rel="stylesheet">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM STYLES -->
<link href="plugins/apex/apexcharts.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/dashboard/dash_1.css" rel="stylesheet"
	type="text/css" />
<!-- END PAGE LEVEL PLUGINS/CUSTOM STYLES -->

<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css"
	type="text/css" media="all" />
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.11/jquery.autocomplete.min.js"
	integrity="sha512-uxCwHf1pRwBJvURAMD/Gg0Kz2F2BymQyXDlTqnayuRyBFE7cisFCh2dSb1HIumZCRHuZikgeqXm8ruUoaxk5tA=="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.11/jquery.autocomplete.js"
	integrity="sha512-JwPA+oZ5uRgh1AATPhLKeByWbXcsRnMMSBpvhuAGQp+CWISl/fHecOshbRcPPgKWau9Wy1H5LhiwAa4QFiQKYw=="
	crossorigin="anonymous"></script>

<script>
	var elem = document.documentElement;
	function openFullscreen() {
		var x = document.getElementById("openW");
		var y = document.getElementById("closeW");
		x.style.display = 'none';
		y.style.display = 'block';

		if (elem.requestFullscreen) {
			elem.requestFullscreen();
		} else if (elem.mozRequestFullScreen) { /* Firefox */
			elem.mozRequestFullScreen();
		} else if (elem.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
			elem.webkitRequestFullscreen();
		} else if (elem.msRequestFullscreen) { /* IE/Edge */
			elem.msRequestFullscreen();
		}
	}

	function closeFullscreen() {
		var x = document.getElementById("openW");
		var y = document.getElementById("closeW");
		x.style.display = 'block';
		y.style.display = 'none';
		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		} else if (document.msExitFullscreen) {
			document.msExitFullscreen();
		}
	}
</script>

</head>
<body id="bdy">
	<!-- BEGIN LOADER -->
	<div id="load_screen">
		<div class="loader">
			<div class="loader-content">
				<div class="spinner-grow align-self-center"></div>
			</div>
		</div>
	</div>
	<!--  END LOADER -->

	<!--  BEGIN NAVBAR  -->
	<div class="header-container fixed-top">
		<header class="header navbar navbar-expand-sm">
			<ul class="navbar-item theme-brand flex-row  text-center">
				<li class="nav-item theme-logo"><a href="Dashboard.html"> <img
						src="assets/img/favicon.png" class="navbar-logo" alt="logo">
				</a></li>
				<%
				HttpSession httpSession1;
				httpSession1 = request.getSession();
				String ShopName = (String) httpSession1.getAttribute("ShopName");
				%>

				<li class="nav-item theme-text"><a href="Dashboard.html"
					class="nav-link"> <%=ShopName%></a></li>
			</ul>

			<!--  <ul class="navbar-item flex-row ml-md-0 ml-auto">
                <li class="nav-item align-self-center search-animated">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search toggle-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
                    <form class="form-inline search-full form-inline search" role="search">
                        <div class="search-bar">
                            <input type="text" class="form-control search-form-control  ml-lg-auto" placeholder="Search...">
                        </div>
                    </form>
                </li>
            </ul> -->
			<!-- <ul class="navbar-item flex-row ml-md-auto">
				<li class="nav-item dropdown user-profile-dropdown">
				<i class="fa fa-expand" style="color: #fff;"></i>
				<img id="openW" onclick="openFullscreen()" src="assets/img/white_full_screen.png" style="height: 25px;" class="navbar-logo">
				<img id="closeW" onclick="closeFullscreen()" src="assets/img/exit_full_screen.png" style="height: 25px;display: none;" class="navbar-logo">
				</li>
			</ul> -->
			<ul class="navbar-item flex-row ml-md-auto">

				<li class="nav-item dropdown user-profile-dropdown"><a
					href="javascript:void(0);" class="nav-link dropdown-toggle user"
					id="userProfileDropdown" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true"> <img
						src="assets/img/logout.png" alt="avatar" width="100%">
						<div class="dropdown-menu position-absolute"
							aria-labelledby="userProfileDropdown">
							<div class="">
								<div class="dropdown-item">
									<a class="" href="LockScreen.html"><svg
											xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round" class="feather feather-lock">
											<rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
											<path d="M7 11V7a5 5 0 0 1 10 0v4"></path></svg> Lock Screen</a>
								</div>
								<div class="dropdown-item">
									<a class="" href="LogoutAdmin.html"><svg
											xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round" class="feather feather-log-out">
											<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
											<polyline points="16 17 21 12 16 7"></polyline>
											<line x1="21" y1="12" x2="9" y2="12"></line></svg> Sign Out</a>
								</div>
							</div>
						</div>
				</a></li>
			</ul>


		</header>
	</div>
	<!--  END NAVBAR  -->

	<!--  BEGIN NAVBAR  -->
	<div class="sub-header-container">
		<header class="header navbar navbar-expand-sm">
			<a href="javascript:void(0);" class="sidebarCollapse"
				data-placement="bottom"><svg xmlns="http://www.w3.org/2000/svg"
					width="24" height="24" viewBox="0 0 24 24" fill="none"
					stroke="currentColor" stroke-width="2" stroke-linecap="round"
					stroke-linejoin="round" class="feather feather-menu">
					<line x1="3" y1="12" x2="21" y2="12"></line>
					<line x1="3" y1="6" x2="21" y2="6"></line>
					<line x1="3" y1="18" x2="21" y2="18"></line></svg></a>

			<ul class="navbar-nav flex-row">
				<li>
					<div class="page-header">
						<nav class="breadcrumb-one" aria-label="breadcrumb">
							<ol class="breadcrumb">
								<!-- <li class="breadcrumb-item"><a href="#">Dashboard</a></li> -->
								<li class="breadcrumb-item" style="color: red;">${msgError}</li>
							</ol>
						</nav>
					</div>
				</li>
			</ul>
		</header>
	</div>
	<!--  END NAVBAR  -->

	<!--  BEGIN MAIN CONTAINER  -->
	<div class="main-container" id="container">

		<div class="overlay"></div>
		<div class="search-overlay"></div>

		<!--  BEGIN SIDEBAR  -->
		<div class="sidebar-wrapper sidebar-theme">

			<nav id="sidebar">
				<div class="shadow-bottom"></div>
				<ul class="list-unstyled menu-categories" id="accordionExample">
					<li class="menu"><a href="Dashboard.html" data-active="true"
						aria-expanded="true" class="dropdown-toggle">
							<div class="">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-home">
									<path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
									<polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
								<span>Dashboard</span>
							</div>
					</a></li>

					<li class="menu">
						<!-- "AddEmployeePage.html" --> <a href="AddCustomerPage.html"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg version="1.1" id="Capa_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" fill="none"
									stroke="currentColor" stroke-width="30" stroke-linecap="round"
									stroke-linejoin="round" x="0px" y="0px" viewBox="0 0 512 512"
									style="enable-background: new 0 0 512 512;"
									xml:space="preserve">

                                    <path
										d="M421.054,414.843c-4.142,0-7.5,3.358-7.5,7.5v70.514c0,2.283-1.858,4.141-4.141,4.141h-40.317V349.301 c0-4.142-3.358-7.5-7.5-7.5c-4.142,0-7.5,3.358-7.5,7.5v147.698h-81.185l23.543-25.9c2.572-2.83,3.785-6.861,3.244-10.787
                                    c-0.01-0.076-0.022-0.152-0.035-0.228L277.24,327.617l6.041-9.094c3.34,2.372,5.913,4.656,10.738,4.656
                                    c4.908,0,9.497-2.747,11.755-7.269v-0.001l23.65-47.4l53.876,20.865c1.949,0.836,30.252,13.582,30.252,47.238v50.73
                                    c-0.001,4.141,3.357,7.5,7.5,7.5c4.142,0,7.5-3.358,7.5-7.5v-50.73c0-44.344-37.969-60.463-39.585-61.128
                                    c-0.047-0.02-0.095-0.039-0.143-0.057l-89.668-34.726v-21.03c14.242-11.076,24.117-27.495,26.596-46.227
                                    c7.101-0.5,13.69-3.152,19.071-7.779c7.027-6.043,11.059-14.838,11.059-24.126c0-7.708-2.781-15.068-7.737-20.803V92.953
                                    C348.144,41.699,306.446,0,255.192,0c-51.254,0-92.952,41.699-92.952,92.953v28.511c-5.009,5.677-7.733,12.665-7.733,20.074
                                    c0,9.291,4.03,18.085,11.059,24.129c5.377,4.625,11.962,7.274,19.061,7.775c2.499,19.083,12.662,36.114,28.117,47.339v19.92
                                    l-89.571,34.725c-0.047,0.018-0.094,0.037-0.141,0.056c-1.617,0.665-39.585,16.784-39.585,61.128v156.245
                                    c0,10.555,8.587,19.142,19.142,19.142h71.457c4.142,0,7.5-3.358,7.5-7.5c0-4.142-3.358-7.5-7.5-7.5h-16.137V349.301
                                    c0-4.142-3.358-7.5-7.5-7.5c-4.142,0-7.5,3.358-7.5,7.5v147.698h-40.319c-2.283,0-4.141-1.858-4.141-4.141V336.611
                                    c0-33.769,28.493-46.486,30.243-47.234l53.834-20.87l23.652,47.402c2.263,4.533,6.858,7.27,11.756,7.27
                                    c4.801,0,7.349-2.249,10.738-4.656l6.041,9.094l-22.421,132.468c-0.013,0.075-0.024,0.15-0.035,0.226
                                    c-0.542,3.924,0.671,7.957,3.244,10.789l23.543,25.9h-29.995c-4.142,0-7.5,3.358-7.5,7.5s3.358,7.5,7.5,7.5h200.365
                                    c10.555,0,19.142-8.588,19.142-19.142v-70.514C428.554,418.201,425.196,414.843,421.054,414.843z M315.375,263.069l-22.049,44.19
                                    c-0.548-0.389-12.233-8.691-26.517-18.834c6.198-7.651-1.053,1.299,27.235-33.617L315.375,263.069z M271.043,309.833l-5.718,8.607
                                    h-18.703l-5.718-8.607l15.07-10.703L271.043,309.833z M227.743,243.121v-14.036c9.112,3.673,18.85,5.376,28.36,5.376
                                    c9.833,0,19.476-2.096,28.052-5.846v14.567l-28.181,34.785L227.743,243.121z M340.881,141.539
                                    c-0.001,4.913-2.129,9.562-5.839,12.753c-2.453,2.11-5.416,3.459-8.661,3.987v-33.477
                                    C335.001,126.202,340.881,133.352,340.881,141.539z M184.007,158.279c-8.718-1.415-14.5-8.623-14.5-16.741
                                    c0-8.018,6.647-14.544,14.5-16.359V158.279z M184.41,109.896c-2.389,0.274-5.127,0.921-7.168,1.615V92.953
                                    c0-42.983,34.968-77.952,77.951-77.952c42.983,0,77.951,34.969,77.951,77.952v18.043c-2.18-0.663-4.441-1.101-6.762-1.307
                                    c0-7.237,0.063-5.841-23.612-31.294c-4.354-4.678-11.556-5.658-17.037-2.077c-26.13,17.069-58.005,25.644-87.415,23.532
                                    C191.867,99.367,185.991,103.616,184.41,109.896z M199.008,164.184v-46.792v-2.465c32.375,1.896,66.318-7.722,93.739-25.283
                                    c10.858,11.658,16.738,17.773,18.634,20.099c0,5.884,0,47.705,0,54.44c0,30.447-24.826,55.276-55.277,55.276
                                    C221.91,219.46,199.008,192.934,199.008,164.184z M218.623,307.259l-22.049-44.19l21.293-8.247l27.241,33.625
                                    C231.255,298.284,219.88,306.366,218.623,307.259z M227.228,461.702l21.709-128.263h14.071l21.709,128.263l-28.744,31.623
                                    L227.228,461.702z" />
                                </svg>
								<span>Add Customer</span>
							</div>
					</a>
					</li>

					<li class="menu"><a href="SearchCustomer.html"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg aria-hidden="true" focusable="false" data-prefix="fas"
									data-icon="search" class="svg-inline--fa fa-search fa-w-16"
									role="img" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
									<path fill="currentColor"
										d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path></svg>
								<span>Search Customer</span>
							</div>
					</a></li>
					<li class="menu"><a href="#pages" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg version="1.1" id="Capa_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" fill="none"
									stroke="currentColor" stroke-width="30" stroke-linecap="round"
									stroke-linejoin="round" x="0px" y="0px"
									viewBox="0 0 497.417 497.417"
									style="enable-background: new 0 0 497.417 497.417;"
									xml:space="preserve">
                                    <path
										d="M387.205,0H110.213C82.982,0,60.83,22.148,60.83,49.384v398.649c0,27.235,22.152,49.384,49.384,49.384h276.991
                                        c27.225,0,49.383-22.148,49.383-49.384V49.384C436.587,22.148,414.429,0,387.205,0z M402.312,448.033
                                        c0,8.329-6.785,15.106-15.107,15.106H110.213c-8.328,0-15.103-6.777-15.103-15.106V49.384c0-8.329,6.774-15.107,15.103-15.107
                                        h276.991c8.322,0,15.107,6.778,15.107,15.107V448.033z" />
                                    <path
										d="M175.877,146.138h111.403c9.457,0,17.129-7.671,17.129-17.14c0-9.469-7.672-17.141-17.129-17.141H175.877
                                        c-9.461,0-17.14,7.672-17.14,17.141C158.738,138.466,166.416,146.138,175.877,146.138z" />
                                    <path
										d="M321.55,197.548H175.877c-9.461,0-17.14,7.679-17.14,17.137c0,9.462,7.679,17.141,17.14,17.141H321.55
                                        c9.469,0,17.147-7.679,17.147-17.141C338.697,205.227,331.019,197.548,321.55,197.548z" />
                                    <path
										d="M321.55,283.245H175.877c-9.461,0-17.14,7.662-17.14,17.13c0,9.469,7.679,17.147,17.14,17.147H321.55
                                        c9.469,0,17.147-7.679,17.147-17.147C338.697,290.907,331.019,283.245,321.55,283.245z" />
                                    <path
										d="M321.55,368.933H175.877c-9.461,0-17.14,7.671-17.14,17.141c0,9.469,7.679,17.129,17.14,17.129H321.55
                                        c9.469,0,17.147-7.66,17.147-17.129C338.697,376.603,331.019,368.933,321.55,368.933z" />
                                </svg>
								<span>Master Data</span>
							</div>
							<div>
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-chevron-right">
									<polyline points="9 18 15 12 9 6"></polyline></svg>
							</div>
					</a>
						<ul class="collapse submenu list-unstyled" id="pages"
							data-parent="#accordionExample">
							<li><a href="ClothTypeMasterPage.html"> Cloth Master </a></li>
							<li><a href="PreferencesMasterPage.html"> Preferences </a></li>
						</ul></li>
					<li class="menu"><a href="AllOrdersPage.html"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg version="1.1" id="Capa_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" fill="none"
									stroke="currentColor" stroke-width="30" stroke-linecap="round"
									stroke-linejoin="round" x="0px" y="0px" viewBox="0 0 490 490"
									style="enable-background: new 0 0 490 490;"
									xml:space="preserve">
                                    <path
										d="M487.491,81.312L420.967,3.645C418.987,1.328,416.095,0,413.051,0H168.763c-4.963,0-9.234,3.496-10.217,8.358
                                    L90.118,346.862H48.88C21.472,346.862,0,378.301,0,418.436C0,458.56,21.472,490,48.88,490h273.615
                                    c40.821,0,77.046-31.491,88.098-76.583l79.108-322.845C490.499,87.308,489.68,83.862,487.491,81.312z M456.916,77.667h-49.608
                                    l11.036-45.036L456.916,77.667z M282.606,469.149H48.88c-13.256,0-28.029-20.831-28.029-50.713
                                    c0-29.892,14.773-50.722,28.029-50.722h49.766h183.961c-8.344,12.868-13.436,30.714-13.436,50.722
                                    C269.17,438.439,274.262,456.281,282.606,469.149z M390.342,408.448c-8.755,35.736-36.657,60.701-67.847,60.701h-6.705
                                    c-10.715,0-22.506-16.097-25.198-40.287h61.388c5.758,0,10.426-4.663,10.426-10.426c0-40.134-20.475-71.574-46.615-71.574H111.387
                                    L177.29,20.851h222.474l-15.867,64.762c-1.917,6.566,3.543,12.905,10.125,12.905h72.266L390.342,408.448z M290.591,408.01
                                    c2.693-24.201,14.483-40.297,25.198-40.297c10.711,0,22.501,16.096,25.193,40.297H290.591z" />
                                </svg>
								<span>All Orders</span>
							</div>
					</a></li>
					<li class="menu"><a href="#orderStatus" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg aria-hidden="true" focusable="false" data-prefix="fas"
									data-icon="store" class="svg-inline--fa fa-store fa-w-20"
									role="img" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 616 512">
                                    <path fill="currentColor"
										d="M602 118.6L537.1 15C531.3 5.7 521 0 510 0H106C95 0 84.7 5.7 78.9 15L14 118.6c-33.5 53.5-3.8 127.9 58.8 136.4 4.5.6 9.1.9 13.7.9 29.6 0 55.8-13 73.8-33.1 18 20.1 44.3 33.1 73.8 33.1 29.6 0 55.8-13 73.8-33.1 18 20.1 44.3 33.1 73.8 33.1 29.6 0 55.8-13 73.8-33.1 18.1 20.1 44.3 33.1 73.8 33.1 4.7 0 9.2-.3 13.7-.9 62.8-8.4 92.6-82.8 59-136.4zM529.5 288c-10 0-19.9-1.5-29.5-3.8V384H116v-99.8c-9.6 2.2-19.5 3.8-29.5 3.8-6 0-12.1-.4-18-1.2-5.6-.8-11.1-2.1-16.4-3.6V480c0 17.7 14.3 32 32 32h448c17.7 0 32-14.3 32-32V283.2c-5.4 1.6-10.8 2.9-16.4 3.6-6.1.8-12.1 1.2-18.2 1.2z"></path>
                                </svg>
								<span>Order Status</span>
							</div>
							<div>
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-chevron-right">
									<polyline points="9 18 15 12 9 6"></polyline></svg>
							</div>
					</a>
						<ul class="collapse submenu list-unstyled" id="orderStatus"
							data-parent="#accordionExample">
							<li><a href="NotReadyOrdersAll.html"> Not Ready </a></li>
							<li><a href="ReadyOrdersAll.html"> Ready </a></li>
							<li><a href="IncompleteOrdersAll.html"> Incomplete </a></li>
							<li><a href="DeliveredOrdersAll.html"> Delivered </a></li>
						</ul></li>
					<!--  <li class="menu">
                        <a href="PrintBarcode.html" aria-expanded="false" class="dropdown-toggle">
                            <div class="">                               
                            <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="barcode" class="svg-inline--fa fa-barcode fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                <path fill="currentColor" d="M0 448V64h18v384H0zm26.857-.273V64H36v383.727h-9.143zm27.143 0V64h8.857v383.727H54zm44.857 0V64h8.857v383.727h-8.857zm36 0V64h17.714v383.727h-17.714zm44.857 0V64h8.857v383.727h-8.857zm18 0V64h8.857v383.727h-8.857zm18 0V64h8.857v383.727h-8.857zm35.715 0V64h18v383.727h-18zm44.857 0V64h18v383.727h-18zm35.999 0V64h18.001v383.727h-18.001zm36.001 0V64h18.001v383.727h-18.001zm26.857 0V64h18v383.727h-18zm45.143 0V64h26.857v383.727h-26.857zm35.714 0V64h9.143v383.727H476zm18 .273V64h18v384h-18z">
                                </path>
                            </svg>                                
                            <span>Print Barcode</span>
                            </div>                           
                        </a>                        
                    </li>  -->

					<li class="menu"><a href="#barcode" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg aria-hidden="true" focusable="false" data-prefix="fas"
									data-icon="barcode" class="svg-inline--fa fa-barcode fa-w-16"
									role="img" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                                <path fill="currentColor"
										d="M0 448V64h18v384H0zm26.857-.273V64H36v383.727h-9.143zm27.143 0V64h8.857v383.727H54zm44.857 0V64h8.857v383.727h-8.857zm36 0V64h17.714v383.727h-17.714zm44.857 0V64h8.857v383.727h-8.857zm18 0V64h8.857v383.727h-8.857zm18 0V64h8.857v383.727h-8.857zm35.715 0V64h18v383.727h-18zm44.857 0V64h18v383.727h-18zm35.999 0V64h18.001v383.727h-18.001zm36.001 0V64h18.001v383.727h-18.001zm26.857 0V64h18v383.727h-18zm45.143 0V64h26.857v383.727h-26.857zm35.714 0V64h9.143v383.727H476zm18 .273V64h18v384h-18z">
                                </path>
                            </svg>
								<span>Print Barcode</span>
							</div>
							<div>
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-chevron-right">
									<polyline points="9 18 15 12 9 6"></polyline></svg>
							</div>
					</a>
						<ul class="collapse submenu list-unstyled" id="barcode"
							data-parent="#accordionExample">
							<li><a href="PrintBarcode.html"> All Services </a></li>
							<li><a href="PrintOutsideBarcode.html"> Outside Services
							</a></li>
						</ul></li>

					<li class="menu"><a href="#starter-kit" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg version="1.1" id="Capa_1"
									xmlns="http://www.w3.org/2000/svg"
									xmlns:xlink="http://www.w3.org/1999/xlink" fill="none"
									stroke="currentColor" stroke-width="30" stroke-linecap="round"
									stroke-linejoin="round" x="0px" y="0px" viewBox="0 0 490 490"
									style="enable-background: new 0 0 490 490;"
									xml:space="preserve">
                                    <path
										d="M487.491,81.312L420.967,3.645C418.987,1.328,416.095,0,413.051,0H168.763c-4.963,0-9.234,3.496-10.217,8.358
                                    L90.118,346.862H48.88C21.472,346.862,0,378.301,0,418.436C0,458.56,21.472,490,48.88,490h273.615
                                    c40.821,0,77.046-31.491,88.098-76.583l79.108-322.845C490.499,87.308,489.68,83.862,487.491,81.312z M456.916,77.667h-49.608
                                    l11.036-45.036L456.916,77.667z M282.606,469.149H48.88c-13.256,0-28.029-20.831-28.029-50.713
                                    c0-29.892,14.773-50.722,28.029-50.722h49.766h183.961c-8.344,12.868-13.436,30.714-13.436,50.722
                                    C269.17,438.439,274.262,456.281,282.606,469.149z M390.342,408.448c-8.755,35.736-36.657,60.701-67.847,60.701h-6.705
                                    c-10.715,0-22.506-16.097-25.198-40.287h61.388c5.758,0,10.426-4.663,10.426-10.426c0-40.134-20.475-71.574-46.615-71.574H111.387
                                    L177.29,20.851h222.474l-15.867,64.762c-1.917,6.566,3.543,12.905,10.125,12.905h72.266L390.342,408.448z M290.591,408.01
                                    c2.693-24.201,14.483-40.297,25.198-40.297c10.711,0,22.501,16.096,25.193,40.297H290.591z" />
                                </svg>
								<span>Reports</span>
							</div>
							<div>
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-chevron-right">
									<polyline points="9 18 15 12 9 6"></polyline></svg>
							</div>
					</a>
						<ul class="collapse submenu list-unstyled" id="starter-kit"
							data-parent="#accordionExample">
							<li><a href="AllOrderReportPage.html"> All Orders </a></li>
							<li><a href="AllCustomersReportPage.html">All Customer
									Report </a></li>
							<!-- <li><a href="OrderIdWiseDetailsReportPage.html"> Order
									Details <br>Duplicate Recipts
							</a></li> -->
							<!-- <li>
                                <a href="MobileNoWiseReportPage.html"> Mobile No Wise Report </a>
                            </li> -->
							<li><a href="paidReportPage.html"> Paid Reports </a></li>
							<!--  -->
							<li><a href="PendingReportPage.html"> Pending Reports </a></li>
							<!-- <li><a href="dailyCollectionReportPage.html"> Date Range Report
									</a></li>  -->
							<li><a href="dateRangeReport.html"> Date Range Report
									</a></li> 
							<!-- <li><a href="fromDateToDateReportPage.html"> From Date
									To Date </a></li> -->
							<!-- <li><a href="fromDateToDateReportPage.html"> Total
									Revenue </a></li> -->
							<li><a href="devloperMasterPage.html"> ${developerPage}
							</a></li>
						</ul></li>
					<li class="menu"><a href="greetingMessagePage.html"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<!-- com.promotion - PromotionController -->
								<svg id="Layer_1" data-name="Layer 1"
									xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
									<defs>
									<style>
.cls-1 {
	fill: #231f20;
}
</style></defs>
									<title>love-card-glyph</title><path class="cls-1"
										d="M283.17,259.24h0a18.06,18.06,0,0,0-12.87,5.35L256,278.88,241.73,264.6a18.17,18.17,0,0,0-25.73,0,18.32,18.32,0,0,0,0,25.87l40,38.9,40.1-39a18.28,18.28,0,0,0-12.94-31.12Z" />
									<path class="cls-1"
										d="M411.54,90.74H392.1V19.44a19.45,19.45,0,0,0-23.52-19L96.38,58.76A19.45,19.45,0,0,0,81,77.77V492.56A19.44,19.44,0,0,0,100.46,512H411.54A19.44,19.44,0,0,0,431,492.56V110.18A19.44,19.44,0,0,0,411.54,90.74ZM353.21,43.49V90.74H132.76ZM301.37,171.75a16.2,16.2,0,1,1-16.2-16.2A16.2,16.2,0,0,1,301.37,171.75Zm-58.33,0a16.2,16.2,0,1,1-16.2-16.2A16.2,16.2,0,0,1,243,171.75Zm-58.33,0a16.2,16.2,0,1,1-16.2-16.2A16.2,16.2,0,0,1,184.71,171.75Zm-16.2,275.44a16.2,16.2,0,1,1,16.2-16.2A16.2,16.2,0,0,1,168.51,447.19Zm58.33,0A16.2,16.2,0,1,1,243,431,16.2,16.2,0,0,1,226.84,447.19Zm58.33,0a16.2,16.2,0,1,1,16.2-16.2A16.2,16.2,0,0,1,285.16,447.19Zm38.16-129.07L256,383.57l-67.33-65.44a57.32,57.32,0,0,1-.2-81,57.12,57.12,0,0,1,67.52-9.9,56.74,56.74,0,0,1,27.16-6.86h0a57.17,57.17,0,0,1,40.35,97.57Zm20.16,129.07A16.2,16.2,0,1,1,359.7,431,16.2,16.2,0,0,1,343.49,447.19Zm0-259.24a16.2,16.2,0,1,1,16.2-16.2A16.2,16.2,0,0,1,343.49,188Z" /></svg>
								<span>Promotion</span>
							</div>
					</a></li>
					<li class="menu"><a href="LogoutAdmin.html"
						aria-expanded="false" class="dropdown-toggle">
							<div class="">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
									viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-log-out">
									<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
									<polyline points="16 17 21 12 16 7"></polyline>
									<line x1="21" y1="12" x2="9" y2="12"></line></svg>
								<span>Logout</span>
							</div>
					</a></li>

					<!-- <li class="menu">
                        <a href="#" aria-expanded="false" class="dropdown-toggle">
                            <div class="">                               
                                <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="truck-pickup" class="svg-inline--fa fa-truck-pickup fa-w-20" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path fill="currentColor" d="M624 288h-16v-64c0-17.67-14.33-32-32-32h-48L419.22 56.02A64.025 64.025 0 0 0 369.24 32H256c-17.67 0-32 14.33-32 32v128H64c-17.67 0-32 14.33-32 32v64H16c-8.84 0-16 7.16-16 16v32c0 8.84 7.16 16 16 16h49.61c-.76 5.27-1.61 10.52-1.61 16 0 61.86 50.14 112 112 112s112-50.14 112-112c0-5.48-.85-10.73-1.61-16h67.23c-.76 5.27-1.61 10.52-1.61 16 0 61.86 50.14 112 112 112s112-50.14 112-112c0-5.48-.85-10.73-1.61-16H624c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16zM288 96h81.24l76.8 96H288V96zM176 416c-26.47 0-48-21.53-48-48s21.53-48 48-48 48 21.53 48 48-21.53 48-48 48zm288 0c-26.47 0-48-21.53-48-48s21.53-48 48-48 48 21.53 48 48-21.53 48-48 48z"></path></svg>	                                
                                <span>Pick Up</span>
                            </div>
                        </a>                               
                    </li>
                    <li class="menu">
                        <a href="#" aria-expanded="false" class="dropdown-toggle">
                            <div class="">
                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-wallet2" fill="none" stroke="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M12.136.326A1.5 1.5 0 0 1 14 1.78V3h.5A1.5 1.5 0 0 1 16 4.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 13.5v-9a1.5 1.5 0 0 1 1.432-1.499L12.136.326zM5.562 3H13V1.78a.5.5 0 0 0-.621-.484L5.562 3zM1.5 4a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
                                </svg>
                                <span>Expences</span>
                            </div>
                        </a>                               
                    </li>
                    <li class="menu">
                        <a href="#" aria-expanded="false" class="dropdown-toggle">
                            <div class="">                               
                                <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="store" class="svg-inline--fa fa-store fa-w-20" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 616 512">
                                    <path fill="currentColor" d="M602 118.6L537.1 15C531.3 5.7 521 0 510 0H106C95 0 84.7 5.7 78.9 15L14 118.6c-33.5 53.5-3.8 127.9 58.8 136.4 4.5.6 9.1.9 13.7.9 29.6 0 55.8-13 73.8-33.1 18 20.1 44.3 33.1 73.8 33.1 29.6 0 55.8-13 73.8-33.1 18 20.1 44.3 33.1 73.8 33.1 29.6 0 55.8-13 73.8-33.1 18.1 20.1 44.3 33.1 73.8 33.1 4.7 0 9.2-.3 13.7-.9 62.8-8.4 92.6-82.8 59-136.4zM529.5 288c-10 0-19.9-1.5-29.5-3.8V384H116v-99.8c-9.6 2.2-19.5 3.8-29.5 3.8-6 0-12.1-.4-18-1.2-5.6-.8-11.1-2.1-16.4-3.6V480c0 17.7 14.3 32 32 32h448c17.7 0 32-14.3 32-32V283.2c-5.4 1.6-10.8 2.9-16.4 3.6-6.1.8-12.1 1.2-18.2 1.2z"></path>
                                </svg>
                                <span>Workshop</span>
                            </div>
                        </a>                               
                    </li> -->
				</ul>
				<!-- <div class="shadow-bottom"></div> -->

			</nav>

		</div>
		<!--  END SIDEBAR  -->

		<!--  BEGIN CONTENT AREA  -->
		<div id="content" class="main-content">
			<div class="layout-px-spacing">
				<div class="row layout-top-spacing">
					<tiles:insertAttribute name="body" />
				</div>
			</div>

			<div class="footer-wrapper">
				<div class="footer-section f-section-1">
					<p class="">
						Copyright © 2020 <a target="_blank" href="#">OmVsab IT
							Solution</a>, All rights reserved.
					</p>
				</div>
				<!-- <div class="footer-section f-section-2">
                    <p class="">Coded by <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-heart"><path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path></svg><i> <a href="#"> Raju Pawar</a></i></p>
                </div> -->
			</div>
		</div>
		<!--  END CONTENT AREA  -->

	</div>
	<!-- END MAIN CONTAINER -->

	<!-- BEGIN GLOBAL MANDATORY SCRIPTS -->
	<!-- <script src="assets/js/libs/jquery-3.1.1.min.js"></script>  -->
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

	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->
	<script src="plugins/apex/apexcharts.min.js"></script>
	<script src="assets/js/dashboard/dash_1.js"></script>
	<!-- BEGIN PAGE LEVEL PLUGINS/CUSTOM SCRIPTS -->

	<script src="assets/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable({
				"order" : [ [ 0, "desc" ] ],
				"scrollY" : "300px",
				"scrollCollapse" : true,
				"scrollX" : true
			});
		});
	</script>
	<script>
		/*code: 48-57 Numbers
		  8  - Backspace,
		  35 - home key, 36 - End key
		  37-40: Arrow keys, 46 - Delete key*/
		function restrictAlphabets(e) {
			var x = e.which || e.keycode;
			if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40)
					|| x == 46)
				return true;
			else
				return false;
		}

		function restrictnumbers(e) {
			var x = e.which || e.keycode;
			if ((x >= 65 && x <= 122) || x == 32)
				return true;
			else
				return false;
		}
	</script>
</body>
</html>