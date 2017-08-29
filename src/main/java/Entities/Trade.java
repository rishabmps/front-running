package Entities;

import java.util.Date;

//import org.joda.time.DateTime;

public class Trade {
	private int TradeId;
	private int CustomerID;
	private String TradeType;
	private String SecurityType;
	private String SecurityName;
	private Date time;
	private float Price;
	private int Quantity;

	public Trade(int tradeId, int customerID, String tradeType, String securityType, String securityName, float price,
			int quantity) {
		super();
		TradeId = tradeId;
		CustomerID = customerID;
		TradeType = tradeType;
		SecurityType = securityType;
		SecurityName = securityName;
		this.time = new Date();
		Price = price;
		Quantity = quantity;
	}

	public Trade(int tradeId, int customerID, String tradeType, String securityType, String securityName, Float price,
			int quantity, Date time) {
		// TODO Auto-generated constructor stub
		super();
		TradeId = tradeId;
		CustomerID = customerID;
		TradeType = tradeType;
		SecurityType = securityType;
		SecurityName = securityName;
		this.time = time;
		Price = price;
		Quantity = quantity;
	}

	public int getTradeId() {
		return TradeId;
	}

	public void setTradeId(int tradeId) {
		TradeId = tradeId;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getTradeType() {
		return TradeType;
	}

	public void setTradeType(String tradeType) {
		TradeType = tradeType;
	}

	public String getSecurityType() {
		return SecurityType;
	}

	public void setSecurityType(String securityType) {
		SecurityType = securityType;
	}

	public String getSecurityName() {
		return SecurityName;
	}

	public void setSecurityName(String securityName) {
		SecurityName = securityName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Trade [TradeId=" + TradeId + ", CustomerID=" + CustomerID + ", TradeType=" + TradeType
				+ ", SecurityType=" + SecurityType + ", SecurityName=" + SecurityName + ", time=" + time + ", Price="
				+ Price + ", Quantity=" + Quantity + "]";
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public float getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
