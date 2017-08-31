<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<title>Trading Engine</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrapValidator.min.css">

</head>
<body>
	<!-- 	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" -->
	<!-- 		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" -->
	<!-- 		crossorigin="anonymous"></script> -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrapValidator.min.js"></script>



	<%
		int tradeId = (Integer) request.getAttribute("tradeId");
		System.out.println(tradeId);
	%>

	<div class="container">
		<div class="jumbotron">
			<div class="row">
				<div class="col-md-12">
					<h2 class="page-header text-center ">Trading Engine</h2>
				</div>
			</div>
		</div>

		<p class="text-center">
			<%
				Boolean isFraud = (Boolean) request.getAttribute("isFraud");
				System.out.println(isFraud);
				if (isFraud != null && !isFraud) {
			%>
			<b>Sucessfully executed trade. Enter details below to execute
				more trade.</b>

			<%
				} else {
			%>
			<b>Enter the following Trade Details</b>
			<%
				}
			%>
		</p>



		<div class=" col-md-12  ">


			<form action="./trade" id="integerForm" class="form-horizontal">
				<div>
					
					<div class="col-sm-10">
						<input type="hidden" class="form-control" name="tradeid"
							placeholder="Trade ID" value="<%=tradeId%>" readonly>
					</div>
				</div>


				<div class="form-group row">
					<label for="customerid" class="col-sm-2 col-form-label">Customer
						ID</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="customerid"
							placeholder="Customer ID" required>
					</div>
				</div>


				<fieldset class="form-group">
					<div class="row">
						<legend class="col-form-legend col-sm-2">Type of Trade</legend>
						<div class="col-sm-10">
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="tradetype"
									id="gridRadios1" value="Buy" checked> Buy
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="tradetype"
									id="gridRadios2" value="Sell"> Sell
								</label>
							</div>
						</div>
					</div>
				</fieldset>


				<fieldset class="form-group">
					<div class="row">
						<legend class="col-form-legend col-sm-2">Security Name</legend>
						<div class="col-sm-10">
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securityname"
									id="gridRadios1" value="apple" checked> Apple
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securityname"
									id="gridRadios2" value="facebook"> Facebook
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securityname"
									id="gridRadios2" value="walmart"> Wallmart
								</label>
							</div>
						</div>
					</div>
				</fieldset>

				<fieldset class="form-group">
					<div class="row">
						<legend class="col-form-legend col-sm-2">Security Type</legend>
						<div class="col-sm-10">
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securitytype"
									id="gridRadios1" value="stocks" checked> Common Stocks
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securitytype"
									id="gridRadios2" value="call_option"> Call Option
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securitytype"
									id="gridRadios2" value="put_option"> Put Option
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="radio" name="securitytype"
									id="gridRadios2" value="futures"> Futures
								</label>
							</div>
						</div>
					</div>
				</fieldset>


				<div class="form-group row">
					<label for="quantity" class="col-sm-2 col-form-label control-label">Quantity
						of Trade</label>
					<div class="col-sm-10">
						<input type="text" min="1" class="form-control" name="quantity"
							placeholder="Quantity" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="price" class="col-sm-2 col-form-label">Price
						per Unit in dollars($)</label>
					<div class="col-sm-10">
						<input type="text" min="0.0001" class="form-control" name="price"
							placeholder="Price" required>
					</div>
				</div>


				<!-- 			<div class="form-group row"> -->
				<!-- 				<label for="time" class="col-sm-2 col-form-label">Timestamp</label> -->
				<!-- 				<div class="col-sm-10"> -->
				<!-- 					<input type="text" class="form-control" name="time" -->
				<!-- 						placeholder="Time"> -->
				<!-- 				</div> -->
				<!-- 			</div> -->



				<input type="hidden" name="operation" id="operation" value="execute">
				<input type="submit" id="submitbutton"class="btn btn-primary  center-block">


			</form>
		</div>

	</div>
</body>

<script>
	$(document).ready(function() {
		$('#integerForm').bootstrapValidator({
			framework : 'bootstrap',
			icon : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				quantity : {
					validators : {
						integer : {
							message : 'The value is not an integer . ',

						}
					}
				},
				price : {
					validators : {
						numeric : {
							message : 'Not a valid price . ',
							thousandsSeparator : ',',
							decimalSeparator : '.'
						}
					}
				},
				customerid : {
					validators : {
						integer : {
							message : 'The value is not an integer . '

						}
					}
				}
			}
		});
	});
</script>

</html>

