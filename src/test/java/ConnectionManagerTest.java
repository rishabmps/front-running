

import org.junit.Test;

import com.citi.database_service.ConnectionManager;

import org.junit.Assert;

public class ConnectionManagerTest {
	
	
	@Test
	public void checkConnection(){
		ConnectionManager manager  = new ConnectionManager();
		System.out.println(manager.connect());
		Assert.assertNotNull(manager.connect());
	}
	
}
