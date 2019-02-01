package org.mql.crowddonating.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

//@Entity
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String cardNumber;

    @Column
    private String cardHolderFirstName;

    @Column
    private String cardHolderLastName;

    @Column
    private String expiryDateMonth;

    @Column
    private String expiryDateYear;

    @Column
    private String securityCode;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getCardHolderFirstName() {
        return cardHolderFirstName;
    }

    public void setCardHolderFirstName(String cardHolderFirstName) {
        this.cardHolderFirstName = cardHolderFirstName;
    }

    public String getCardHolderLastName() {
        return cardHolderLastName;
    }

    public void setCardHolderLastName(String cardHolderLastName) {
        this.cardHolderLastName = cardHolderLastName;
    }

    public String getExpiryDateMonth() {
        return expiryDateMonth;
    }

    public void setExpiryDateMonth(String expiryDateMonth) {
        this.expiryDateMonth = expiryDateMonth;
    }

    public String getExpiryDateYear() {
        return expiryDateYear;
    }

    public void setExpiryDateYear(String expiryDateYear) {
        this.expiryDateYear = expiryDateYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
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
        return "BankCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderFirstName='" + cardHolderFirstName + '\'' +
                ", cardHolderLastName='" + cardHolderLastName + '\'' +
                ", expiryDateMonth='" + expiryDateMonth + '\'' +
                ", expiryDateYear='" + expiryDateYear + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}