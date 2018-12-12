package org.mql.crowddonating.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Donation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private double amount;

	@Column
	private Date date;

	@ManyToOne
	@JoinColumn(name = "case_id")
	private Case aCase;

	@ManyToOne
	@JoinColumn(name = "bank_card_id")
	private BankCard bankCard;

	public Donation() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Case getCase() {
		return aCase;
	}

	public void setCase(Case aCase) {
		this.aCase = aCase;
	}

	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}
}