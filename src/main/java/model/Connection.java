package model;

public class Connection {
	private Long id;
	private User from;
	private User to;
	public Long getId() {
		return id;
	}
	public User getFrom() {
		return from;
	}
	public User getTo() {
		return to;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public void setTo(User to) {
		this.to = to;
	}
	
}
