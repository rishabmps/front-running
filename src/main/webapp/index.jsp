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

<h2>Trade Book</h2>
<p>Enter the following Trade Details</p>

<form action="./trade" method="get">
	<hr />
	<div class="traderportal">


		<p>Trade ID :</p>
		<input type="text" name="tradeid" value=""> <br>

		<p>Type of Order :</p>
		<input type="radio" name="order" value="firm">Firm Order <input
			type="radio" name="order" value="customer"> Customer Order

		<p>Party ID :</p>
		<input type="text" name="tradepartyid" value=""> <br>


		<p>Security Option :</p>
		<input type="radio" name="security" value="apple">Apple <input
			type="radio" name="security" value="facebook">Facebook <input
			type="radio" name="security" value="walmart">WalMart
		<p>Security Type :</p>
		<input type="radio" name="securitytype" value="stocks">Common Stocks
		<input type="radio" name="securitytype" value="call_options">Call Option <input
			type="radio" name="securitytype" value="put_options">Put Option <input
			type="radio" name="securitytype" value="futures">Futures

		<p>Quantity of the Trade :</p>
		<input type="text" name="tradequantity" value=""> <br>

		<p>Price of one unit :</p>
		<input type="text" name="tradeprice" value=""> <br>

		<p>Time of Trade Execution :</p>
		<input type="text" name="tradetime" value=""> <br>

	</div>
	<input type="Submit" value="Submit">
</form>

</html>

