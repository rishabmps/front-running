package com.citi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.citi.database_service.TradeExecutionService;

import Entities.Trade;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private int tradeId;

	public WelcomeServlet() {
		super();
		tradeId = 0;

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		// PrintWriter out = response.getWriter();
		// out.println(request.getParameter("tradeid"));
		//
		// System.out.println("kajshdjkasbdjk");
		String operation = request.getParameter("operation");
		if (operation == null) {
			request.setAttribute("tradeId", ++tradeId);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		else if (operation != null && operation.equalsIgnoreCase("execute")) {

			Trade trade = new Trade(Integer.parseInt(request.getParameter("tradeid")),
					Integer.parseInt(request.getParameter("customerid")), request.getParameter("tradetype"),
					request.getParameter("securitytype"), request.getParameter("securityname"),
					Float.parseFloat(request.getParameter("price")),
					Integer.parseInt(request.getParameter("quantity")));

			TradeExecutionService service = new TradeExecutionService();
			boolean isFraud = service.executeTrade(trade);
			if (isFraud) {
				System.out.println("Fraud Detected");
			} else {
				System.out.println("No Fraud Detected");
			}
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
