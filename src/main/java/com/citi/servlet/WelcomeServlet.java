package com.citi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	public static ThreadPoolExecutor executor;

	public WelcomeServlet() {
		super();
		tradeId = 0;
		executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
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
			sendToHome(request, response);
		}

		else if (operation != null && operation.equalsIgnoreCase("execute")) {

			Trade trade = new Trade(Integer.parseInt(request.getParameter("tradeid")),
					Integer.parseInt(request.getParameter("customerid")), request.getParameter("tradetype"),
					request.getParameter("securitytype"), request.getParameter("securityname"),
					Float.parseFloat(request.getParameter("price")),
					Integer.parseInt(request.getParameter("quantity")));

			TradeExecutionService service = new TradeExecutionService();
			ArrayList<Trade> frauds = service.executeTrade(trade);
			if (frauds != null) {
				System.out.println("Fraud Detected");
				request.setAttribute("Trades", frauds);
				request.getRequestDispatcher("/flaggedlist.jsp").forward(request, response);

			} else {
				System.out.println("No Fraud Detected");
				request.setAttribute("isFraud", new Boolean(false));
				sendToHome(request, response);
			}
		} else if (operation != null && operation.equalsIgnoreCase("home")) {
			sendToHome(request, response);
		} else if (operation != null && operation.equalsIgnoreCase("listDisplay")) {
			TradeExecutionService service = new TradeExecutionService();
			ArrayList<Trade> allTrades = service.AllFrauds();
			request.setAttribute("trades", allTrades);
			request.getRequestDispatcher("/faultyTrades.jsp").forward(request, response);
			System.out.println("blah");
		}

	}

	private void sendToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("tradeId", ++tradeId);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
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
