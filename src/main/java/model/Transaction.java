package model;

public class Transaction {
	private String connection;
	private String description;
	private String amount;
	private User user;
	
	public Transaction(String connection, String description, String amount, User user) {
		super();
		this.connection = connection;
		this.description = description;
		this.amount = amount;
		this.user = user;
	}
	public String getConnection() {
		return connection;
	}
	public String getDescription() {
		return description;
	}
	public String getAmount() {
		return amount;
	}
	public User getUser() {
		return user;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
