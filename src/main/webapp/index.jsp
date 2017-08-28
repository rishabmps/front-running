<html>
	<head>
    	<title>Front Running Project Detection</title>
      	<style>
			.traderportal {
		    -webkit-column-count: 2; /* Chrome, Safari, Opera */
		    column-count: 2;
			}
		</style>
	</head>
	
	<center>
		<h2>Trade Book</h2>
		<p>Enter the following Trade Details</p>
	</center>
	
	<form action="ParametersServlet2" method="get">
	<hr />
	<div class="traderportal">
	
	
	<p>Trade ID :</p>
	<input type="text" name="tradeid" 
	value=""> <br>
	
	<p>Type of Order :</p>
	<form>
	<input type = "radio" name = "order" value = "firm">Firm Order
	<input type = "radio" name = "order" value = "customer"> Customer Order
	</form>
	
	<p>Party ID :</p>
	<input type="text" name="tradepartyid" 
	value=""> <br>
	
	
	<p>Security Option :</p>
	<form>
	<input type = "radio" name = "security" value = 1>Apple
	<input type = "radio" name = "security" value = 2>Facebook
	<input type = "radio" name = "security" value = 3>WallMart
	</form>
	<p>Security Type :</p>
	<form>
	<input type = "radio" name = "securitytype" value = 1>Common Stocks
	<input type = "radio" name = "securitytype" value = 2>Call Option
	<input type = "radio" name = "securitytype" value = 3>Put Option
	<input type = "radio" name = "securitytype" value = 3>Futures
	</form>
	
	<p>Quantity of the Trade :</p>
	<input type="text" name="tradequantity" 
	value=""> <br>
	
	<p>Price of one unit :</p>
	<input type="text" name="tradeprice" 
	value=""> <br>
	
	<p>Time of Trade Execution :</p>
	<input type="text" name="tradetime" 
	value=""> <br>
	
	</div>
	<center>
	<input type="Submit" value="Submit">
	</center>
	</form>

</html>

