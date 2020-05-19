package model;

public class Card {
	private Long id;
	private String cardInfo;
	private User user;
	
	
	public Card(String cardInfo) {
		super();
		this.cardInfo = cardInfo;
	}


	public Long getId() {
		return id;
	}


	public String getCardInfo() {
		return cardInfo;
	}


	public User getUser() {
		return user;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
