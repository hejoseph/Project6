package com.example.project6.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Transfers")
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sender_id")
	private User sender;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receiver_id")
	private User receiver;
	private Date date;
	private double sum;
	private String description;
	private double commission;
	
	public Transfer() {
	}
	
	public Transfer(User sender, User receiver, Date date, double sum, String description, double commission) {
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.sum = sum;
		this.description = description;
		this.commission = commission;
	}

	public Long getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public Date getDate() {
		return date;
	}

	public double getSum() {
		return sum;
	}

	public double getCommission() {
		return commission;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
