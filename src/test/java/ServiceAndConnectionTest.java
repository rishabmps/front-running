
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.citi.database_service.ConnectionManager;
import com.citi.database_service.DbManager;
import com.citi.database_service.TradeExecutionService;

public class ServiceAndConnectionTest {

	TradeExecutionService service;
	DbManager manager;

	@Before
	public void setup() {
		service = new TradeExecutionService();
		manager = new DbManager();
	}

	@Test
	public void checkConnection() {

		ConnectionManager m = new ConnectionManager();
		Assert.assertNotNull(m.connect());

	}

}
