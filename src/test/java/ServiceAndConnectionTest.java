
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.citi.database_service.ConnectionManager;
import com.citi.database_service.DbManager;
import com.citi.database_service.TradeExecutionService;
import com.citi.mail_service.SendMailExample;

import java.lang.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Trade;

public class ServiceAndConnectionTest {

	TradeExecutionService service;

	@Before
	public void setup() {
		service = new TradeExecutionService();

	}

	@Test
	public void checkConnection() {

		ConnectionManager m = new ConnectionManager();
		Connection conn = m.connect();
		Assert.assertNotNull(conn);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void serviceExecutionTest() {
		Trade trade = new Trade(199, 199, "test", "test", "facebook", 199, 199);
		int preCount = service.getCount("select count(*) as count from FacebookCustomerOrder");
		service.executeTrade(trade);
		int postCount = service.getCount("select count(*) as count from FacebookCustomerOrder");
		Assert.assertEquals(preCount + 1, postCount, 0);
	}

	@Test
	public void serviceFraudList() {
		DbManager manager = new DbManager();
		Trade trade1 = new Trade(199, 199, "buy", "stocks", "facebook", 199, 199);
		Trade trade2 = new Trade(200, 200, "buy", "stocks", "facebook", 199, 199);
		Trade trade3 = new Trade(201, 199, "sell", "stocks", "facebook", 199, 199);
		ArrayList<Trade> list = new ArrayList<Trade>();
		list.add(trade1);
		list.add(trade2);
		list.add(trade3);
		ArrayList<Trade> frauds = service.isFraud(list, manager);
		Assert.assertNotNull(frauds);
		manager.closeConnection();

	}

	@Test
	public void sendMailTest() {
		try {
			SendMailExample mail = new SendMailExample();
			mail.performTask("<html><body><h1>  " + "JUNIT TEST " + "</h1></body></html>");
		} catch (Exception e) {
			System.out.println("internet is down");
		}

	}

}
