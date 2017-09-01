<%@page import="Entities.Trade"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALl Fraudulent Trades</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
</head>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/js/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<body>
	<div class="container">
		<div class="jumbotron page-header">
			<h1>
				<center>All Front Running Trades</center>
			</h1>
		</div>
		<%
			ArrayList<Trade> trades = (ArrayList<Trade>) request.getAttribute("trades");
		%>
		<table class="table ">
			<thead>
				<tr class="thead-inverse">
					<th>Trade ID</th>
					<th>Customer ID</th>
					<th>Trade Type</th>
					<th>Security Type</th>
					<th>Security Name</th>
					<th>Time</th>
					<th>Price</th>
					<th>Quantity</th>
				</tr>
			</thead>
			<%
				for (Trade t : trades) {
					if (t.getQuantity() == 0) {
			%>
			<tr class="table-warning">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td><%=t.getTradeId()%></td>
				<td><%=t.getCustomerID()%></td>
				<td><%=t.getTradeType()%></td>
				<td><%=t.getSecurityType()%></td>
				<td><%=t.getSecurityName()%></td>
				<td><%=t.getTime()%></td>
				<td><%=t.getPrice()%></td>
				<td><%=t.getQuantity()%></td>
			</tr>

			<%
				}
				}
			%>
		</table>
		<form action="./trade"  method="post">
			<input type="hidden" name="operation" id="operation" value="home">
			<center>
				<input type="submit" id="back_button" class="btn btn-primary" value="Home">
			</center>
		</form>
		
	</div>
</body>
</html>