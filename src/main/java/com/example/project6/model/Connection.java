package com.example.project6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Connections")
public class Connection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="from_user_id")
	private User from;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="to_user_id")
	private User to;
	
	public Connection() {
	}
	
	public Connection(User from, User to) {
		this.from = from;
		this.to = to;
	}

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
