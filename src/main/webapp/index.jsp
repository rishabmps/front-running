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

		<p>Type of Trade :</p>
		<input type="radio" name="tradetype" value="Buy">Buy <input
			type="radio" name="tradetype" value="Sell"> Sell

		<p>Customer ID :</p>
		<input type="text" name="customerid" value=""> <br>


		<p>Security Name :</p>
		<input type="radio" name="securityname" value="apple">Apple <input
			type="radio" name="securityname" value="facebook">Facebook <input
			type="radio" name="securityname" value="walmart">WalMart
		<p>Security Type :</p>
		<input type="radio" name="securitytype" value="stocks">Common Stocks
		<input type="radio" name="securitytype" value="call_options">Call Option <input
			type="radio" name="securitytype" value="put_options">Put Option <input
			type="radio" name="securitytype" value="futures">Futures

		<p>Quantity of the Trade :</p>
		<input type="text" name="quantity" value=""> <br>

		<p>Price of one unit :</p>
		<input type="text" name="price" value=""> <br>

		<p>Time of Trade Execution :</p>
		<input type="text" name="time" value=""> <br>

	</div>
	<input type="Submit" value="Submit">
</form>

</html>

