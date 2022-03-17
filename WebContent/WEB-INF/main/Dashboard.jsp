<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<a href="getTodaysDelivery.html" target="_blank"><div class="card">
				<div class="card-body">
					<h5 class="card-title">Today's Delivery</h5>
					<h4 class="card-text">${Todays_Deliverys}</h4>
					<!-- <a href="#" class="btn btn-primary">Show</a> -->
				</div>
			</div></a>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<a href="getTodaysOrders.html" target="_blank">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Today's Orders</h5>
					<h4 class="card-text">${Todays_Orders}</h4>
					<!-- <a href="#" class="btn btn-primary">Show</a>  -->
				</div>
			</div>
		</a>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Today's Cloths</h5>
				<h4 class="card-text">${Todays_Cloths}</h4>
				<!-- <a href="#" class="btn btn-primary">Show</a> -->
			</div>
		</div>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<a href="getTodaysRevenue.html" target="_blank">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Today's Revenue</h5>
					<h4 class="card-text">${todays_Amount}</h4>
					<!-- <a href="#" class="btn btn-primary">Show</a> -->
				</div>
			</div>
		</a>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Month Revenue</h5>
				<h4 class="card-text">${months_Amount}</h4>
				<!-- <a href="#" class="btn btn-primary">Show</a> -->
			</div>
		</div>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<a href="getTodaysCollection.html" target="_blank">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Todays Collection</h5>
					<h4 class="card-text">${todays_collection}</h4>
					<!-- <a href="#" class="btn btn-primary">Show</a> -->
				</div>
			</div>
		</a>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Todays Expences</h5>
				<h4 class="card-text">0000</h4>
				<!-- <a href="#" class="btn btn-primary">Show</a> -->
			</div>
		</div>
	</div>
	<div id="custom-styles"
		class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 layout-spacing">
		<div class="card">
			<div class="card-body">
				<h5 class="card-title">Month Expences</h5>
				<h4 class="card-text">0000</h4>
				<!-- <a href="#" class="btn btn-primary">Show</a> -->
			</div>
		</div>
	</div>

</body>
</html>