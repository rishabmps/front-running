package Entities;

import org.joda.time.DateTime;

public class Trade {
	int TradeId;
	int CustomerID;
	String TradeType;
	String SecurityType;
	String SecurityName;
	DateTime time;
	int Price;
	float Quantity;	
}
