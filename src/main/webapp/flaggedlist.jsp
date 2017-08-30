

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entities.Trade"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://w...content-available-to-author-only...3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Previous Trades</title>
<link rel="stylesheet"
	href="https://m...content-available-to-author-only...n.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
</head>
<body>
	<script
		src="https://c...content-available-to-author-only...y.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://c...content-available-to-author-only...e.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>

	<h1>
		<center>Past Trades</center>
	</h1>
	<%
		try {
			ArrayList<Trade> tradeList = (ArrayList<Trade>) request.getAttribute("Trades");
			for (int x = 0; x < tradeList.size(); x++) {
				//System.out.println(tradeList.get(x));
				System.out.println(tradeList.get(x).getTradeId());
				System.out.println(tradeList.get(x).getCustomerID());
				System.out.println(tradeList.get(x).getTradeType());
				System.out.println("Hi123JSP");

			}
	%>
	<div class="container">
		<table class="table table-inverse">
			<thead>
				<tr>
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
				for (Trade t : tradeList) {
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
			%>
		</table>
	</div>
	<%
		// Forward to JSP page to display them in a HTML table.
		} catch (Exception e) {
			System.out.println(e + "Not working");
		}
	%>
</body>
</html>