

import org.junit.Test;

import com.citi.database_service.ConnectionManager;
import com.citi.database_service.DbManager;

import org.junit.Assert;

public class ConnectionManagerTest {
	
	
	@Test
	public void checkConnection(){
		DbManager manager = new DbManager();
		ConnectionManager m = new ConnectionManager();
		Assert.assertNotNull(m.connect());
		manager.Update("insert into AppleCustomerOrder(TradeID,CustomerID,TradeType,SecurityType,SecurityName,Price,Quantity) values (1,12,'Buy','futures', 'apple',12.25,1)");
		
		
	}
	
}
