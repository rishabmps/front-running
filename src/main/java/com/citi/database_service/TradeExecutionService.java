package com.citi.database_service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.joda.time.DateTime;

import com.citi.mail_service.SendMailExample;

import Entities.Trade;

public class TradeExecutionService {

	public Integer getCount(String query) {
		// TODO Auto-generated method stub
		DbManager manager = new DbManager();
		Integer count = 0;

		ResultSet executeQuery = manager.findAll(query);
		try {
			while (executeQuery.next()) {
				count = executeQuery.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();

		return count;

	}

	private void insertTrade(DbManager manager, Trade trade, String tableName) {
		System.out.println(manager.connection);
		try {
			String query = "INSERT into " + tableName + " values (" + trade.getTradeId() + ", " + trade.getCustomerID()
					+ " , '" + trade.getTradeType() + "' ,'" + trade.getSecurityType() + "' , '"
					+ trade.getSecurityName() + "', " + trade.getPrice() + ", " + trade.getQuantity() + ",'"
					+ new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(trade.getTime()) + "')";
			System.out.println(query);
			manager.Update(query);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public ArrayList<Trade> executeTrade(Trade trade) {
		// TODO Auto-generated method stub

		DbManager manager = new DbManager();
		System.out.println(trade);
		DateTime date = new DateTime(trade.getTime());
		DateTime prevDate = date.minusMinutes(10);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// if (trade.getCustomerID() == 1) // citi ID
		// {
		// System.out.println("inside loop");
		// if (trade.getSecurityName().equalsIgnoreCase("apple")) {
		// insertTrade(manager, trade, "AppleFirmOrder");
		// return checkFraud(trade, date.toDate(), prevDate.toDate(),
		// manager,"AppleFirmOrder");
		// } else if (trade.getSecurityName().equalsIgnoreCase("facebook")) {
		// insertTrade(manager, trade, "FacebookFirmOrder");
		// return checkFraud(trade, date.toDate(), prevDate.toDate(),
		// manager,"FacebookFirmOrder");
		// } else if (trade.getSecurityName().equalsIgnoreCase("walmart")) {
		// insertTrade(manager, trade, "WalmartFirmOrder");
		// return checkFraud(trade, date.toDate(), prevDate.toDate(),
		// manager,"WalmartFirmOrder");
		//
		// }
		// } else {
		if (trade.getSecurityName().equalsIgnoreCase("apple")) {
			insertTrade(manager, trade, "AppleCustomerOrder");
			return checkFraud(trade, date.toDate(), prevDate.toDate(), manager, "AppleCustomerOrder");
		} else if (trade.getSecurityName().equalsIgnoreCase("facebook")) {
			insertTrade(manager, trade, "FacebookCustomerOrder");
			return checkFraud(trade, date.toDate(), prevDate.toDate(), manager, "FacebookCustomerOrder");
		} else if (trade.getSecurityName().equalsIgnoreCase("walmart")) {
			insertTrade(manager, trade, "WalmartCustomerOrder");
			return checkFraud(trade, date.toDate(), prevDate.toDate(), manager, "WalmartCustomerOrder");
		}
		// }

		manager.closeConnection();
		return null;

	}

	private ArrayList<Trade> checkFraud(Trade trade, Date date, Date prevDate, DbManager manager, String tableName) {
		// TODO Auto-generated method stub
		String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String startingDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prevDate);
		String query = "select * from " + tableName + " where Time between '" + startingDate + "' and '" + endDate
				+ "'";
		System.out.println(query);
		ResultSet result = manager.findAll(query);

		ArrayList<Trade> list = convertToArrayList(result);
		display(list);
		return isFraud(list, manager);
	}

	private ArrayList<Trade> isFraud(ArrayList<Trade> list, DbManager manager) {
		// TODO Auto-generated method stub
		ArrayList<Trade> frauds = detection(list);
		if (frauds == null) {
			return null;
		} else if (frauds.size() < 3) {
			return null;
		}
		System.out.println("Frad wali Trades");
		System.out.println(frauds.size());
		display(frauds);
		saveFraudsToBB(frauds, manager);
		SendMailExample mail = new SendMailExample();
		
		try {
			mail.performTask("<html><body><h1>  " + "Front Running Detected ... if recieved please consult Aman shrivastava asap" + "</h1></body></html>");
		} catch (Exception e) {

			e.printStackTrace();
			return frauds;
		}
		return frauds;
	}

	private void saveFraudsToBB(ArrayList<Trade> frauds, DbManager manager) {
		// TODO Auto-generated method stub

		for (Iterator<Trade> iterator = frauds.iterator(); iterator.hasNext();) {
			Trade trade = (Trade) iterator.next();
			insertTrade(manager, trade, "AlertTable");
		}
		Trade diffrentiator = new Trade(1, 1, "", "", "", 0, 0);
		insertTrade(manager, diffrentiator, "AlertTable");
		System.out.println("frauds inserted in table");
	}

	private ArrayList<Trade> detection(ArrayList<Trade> list) {
		if (list.size() < 3) {
			return null;
		}
		ArrayList<Trade> fraudlist = new ArrayList<Trade>();

		ArrayList<Trade> FraudlistTempZ = new ArrayList<Trade>();
		ArrayList<Trade> FraudlistTempX = new ArrayList<Trade>();
		Trade X = list.get(list.size() - 1);
		FraudlistTempX.add(X);
		boolean alert = false;
		if (!X.getSecurityType().equalsIgnoreCase("put_option")) {
			for (int i = (list.size() - 2); i >= 0; i--) {
				Trade Y = list.get(i);

				if (Y.getCustomerID() == X.getCustomerID() && Y.getTradeType().equalsIgnoreCase(X.getTradeType())
						&& Y.getSecurityType().equalsIgnoreCase(X.getSecurityType())) {
					FraudlistTempX.add(Y);

				}

				if (Y.getCustomerID() != X.getCustomerID() && Y.getCustomerID() != 1
						&& ((!Y.getTradeType().equalsIgnoreCase(X.getTradeType())
								&& !Y.getSecurityType().equalsIgnoreCase("put_option"))
								|| (Y.getTradeType().equalsIgnoreCase(X.getTradeType())
										&& Y.getSecurityType().equalsIgnoreCase("put_option")))) {
					alert = true;
					FraudlistTempZ.addAll(FraudlistTempX);
					FraudlistTempZ.add(Y);
					FraudlistTempX.clear();
				}
				if (Y.getCustomerID() == X.getCustomerID() && !Y.getTradeType().equalsIgnoreCase(X.getTradeType())
						&& alert == true && Y.getSecurityType().equalsIgnoreCase(X.getSecurityType())) {
					fraudlist.addAll(FraudlistTempZ);
					fraudlist.add(Y);
					FraudlistTempZ.clear();
				}

			}

		}

		if (X.getSecurityType().equalsIgnoreCase("put_option")) {
			for (int i = (list.size() - 2); i >= 0; i--) {
				Trade Y = list.get(i);

				if (Y.getCustomerID() == X.getCustomerID() && Y.getTradeType().equalsIgnoreCase(X.getTradeType())
						&& Y.getSecurityType().equalsIgnoreCase(X.getSecurityType())) {
					FraudlistTempX.add(Y);

				}

				if (Y.getCustomerID() != X.getCustomerID() && Y.getCustomerID() != 1
						&& ((!Y.getTradeType().equalsIgnoreCase(X.getTradeType())
								&& Y.getSecurityType().equalsIgnoreCase(X.getSecurityType()))
								|| (Y.getTradeType().equalsIgnoreCase(X.getTradeType())
										&& !Y.getSecurityType().equalsIgnoreCase(X.getSecurityType())))) {
					alert = true;
					FraudlistTempZ.addAll(FraudlistTempX);
					FraudlistTempZ.add(Y);
					FraudlistTempX.clear();
				}
				if (Y.getCustomerID() == X.getCustomerID() && !Y.getTradeType().equalsIgnoreCase(X.getTradeType())
						&& alert == true && Y.getSecurityType().equalsIgnoreCase(X.getSecurityType())) {
					fraudlist.addAll(FraudlistTempZ);
					fraudlist.add(Y);
					FraudlistTempZ.clear();
				}
			}
		}
		Collections.reverse(fraudlist);

		return fraudlist;

	}

	private void display(ArrayList<Trade> list) {
		// TODO Auto-generated method stub
		if (list != null) {
			for (Iterator<Trade> iterator = list.iterator(); iterator.hasNext();) {
				Trade trade = (Trade) iterator.next();
				System.out.println(trade);
			}
		} else {
			System.out.println("List is Null");
		}

	}

	private ArrayList<Trade> convertToArrayList(ResultSet result) {
		// TODO Auto-generated method stub
		ArrayList<Trade> list = new ArrayList<Trade>();

		try {

			while (result.next()) {
				Trade t;

				int tradeId = Integer.parseInt(result.getString(1));
				int customerID = Integer.parseInt(result.getString(2));
				String tradeType = result.getString(3);
				String securityType = result.getString(4);
				String securityName = result.getString(5);
				Float price = Float.parseFloat(result.getString(6));
				int quantity = Integer.parseInt(result.getString(7));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time = df.parse(result.getString(8));
				t = new Trade(tradeId, customerID, tradeType, securityType, securityName, price, quantity, time);
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// public void saveAccount(Account account) {
	// // TODO Auto-generated method stub
	// DbManager manager = new DbManager();
	// manager.Update("INSERT INTO
	// account(account_name,birthdate,account_type,mobile_no,pan_card) "
	// +
	// "VALUES('"+account.getName2()+"','"+account.getBirtDate2()+"','"+account.getType2()+"','"+account.getMobile2()+"','"+account.getPan_no2()+"');");
	// manager.closeConnection();
	//
	// }
	//
	// public ArrayList<Account> selectAll(){
	// DbManager manager = new DbManager();
	// ArrayList<Account> accounts = new ArrayList<Account>();
	// ResultSet executeQuery = manager.findAll("select
	// account_name,birthdate,account_type,mobile_no,pan_card from account");
	// try {
	// while(executeQuery.next()){
	// String name = executeQuery.getString("account_name");
	// String birthDate = executeQuery.getString("birthdate");
	// String type = executeQuery.getString("account_type");
	// String mobile = executeQuery.getString("mobile_no");
	// String pan_no = executeQuery.getString("pan_card");
	// accounts.add(new Account(name, birthDate, type, mobile, pan_no));
	//
	// }
	// return accounts;
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// manager.closeConnection();
	// return accounts;
	//
	// }
	//
	// public Account getByPan(String query) {
	// // TODO Auto-generated method stub
	// DbManager manager = new DbManager();
	// ResultSet executeQuery = manager.findAll(query);
	//
	// String birthDate;
	// Account account = null;
	// try {
	// String name = executeQuery.getString("account_name");
	// birthDate = executeQuery.getString("birthdate");
	// String type = executeQuery.getString("account_type");
	// String mobile = executeQuery.getString("mobile_no");
	// String pan_no = executeQuery.getString("pan_card");
	// account = new Account(name, birthDate, type, mobile, pan_no);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// manager.closeConnection();
	// return account;
	//
	// }
	//
	// public Account getAccountById(int id) {
	// // TODO Auto-generated method stub
	// DbManager manager = new DbManager();
	// ResultSet executeQuery = manager.findAll("select
	// account_name,birthdate,account_type,mobile_no,pan_card from account where
	// account_id="+id);
	// Account account = null;
	// try {
	// while(executeQuery.next()){
	// String name = executeQuery.getString("account_name");
	// String birthDate = executeQuery.getString("birthdate");
	// String type = executeQuery.getString("account_type");
	// String mobile = executeQuery.getString("mobile_no");
	// String pan_no = executeQuery.getString("pan_card");
	// account = new Account(name, birthDate, type, mobile, pan_no);
	//
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// manager.closeConnection();
	// return account;
	//
	// }
	//
	// public void updateAccount(String name, String birtDate, String type,
	// String mobile, String pan_no) {
	// // TODO Auto-generated method stub
	// DbManager manager = new DbManager();
	// String s="UPDATE account SET
	// account_name='"+name+"',birthdate='"+birtDate+"',account_type='"+type+"',mobile_no='"+mobile+"'
	// where pan_card='"+pan_no+"'; ";
	// manager.Update(s);
	// manager.closeConnection();
	// }

}
