package com.citi.database_service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;

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

	public void saveTrade(Trade trade) {
		// TODO Auto-generated method stub

		DbManager manager = new DbManager();
		System.out.println(trade);
		DateTime date = new DateTime(trade.getTime());
		DateTime prevDate = date.minusMinutes(10);
		
		if (trade.getCustomerID() == 1) // citi ID
		{
			System.out.println("inside loop");
			if (trade.getSecurityName().equalsIgnoreCase("apple")) {
				insertTrade(manager, trade, "AppleFirmOrder");
				
			} else if (trade.getSecurityName().equalsIgnoreCase("facebook")) {
				insertTrade(manager, trade, "FacebookFirmOrder");
			} else if (trade.getSecurityName().equalsIgnoreCase("walmart")) {
				insertTrade(manager, trade, "WalmartFirmOrder");
			}
		} else {
			if (trade.getSecurityName().equalsIgnoreCase("apple")) {
				insertTrade(manager, trade, "AppleCustomerOrder");
			} else if (trade.getSecurityName().equalsIgnoreCase("facebook")) {
				insertTrade(manager, trade, "FacebookCustomerOrder");
			} else if (trade.getSecurityName().equalsIgnoreCase("walmart")) {
				insertTrade(manager, trade, "WalmartCustomerOrder");
			}
		}
		
		manager.closeConnection();

	}

	private void checkFraud(Trade trade, Date date, Date prevDate, DbManager manager) {
		// TODO Auto-generated method stub
		String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String startingDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prevDate);
		String query = "select * from AppleFirmOrder where Time between '" + startingDate + "' and '" + endDate + "'";
		System.out.println(query);
		ResultSet result = manager.findAll(query);
		System.out.println(result);
		ArrayList<Trade> list = convertToArrayList(result);
		System.out.println(Arrays.toString(list.toArray()));
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
