package org.mql.crowddonating.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String cardNumber;

    @Column
    private String cardHolder;

    @Column
    private Date expirationDate;

    @Column
    private int securityCode;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @OneToMany(mappedBy = "bankCard", fetch = FetchType.LAZY)
    private List<Donation> donations;

    public BankCard() {
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

	@Override
	public String toString() {
		return "BankCard [id=" + id + ", cardNumber=" + cardNumber + ", cardHolder=" + cardHolder + ", expirationDate="
				+ expirationDate + ", securityCode=" + securityCode + ", donor=" + donor + ", donations=" + donations
				+ "]";
	}
    
    
}