package org.mql.crowddonating.models;

import javax.persistence.*;

@Entity
public class Donor extends User {

    @Column
    private String phone;

    @Column
    private String address;
    

    public Donor() {
    }

    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
	@Override
	public String toString() {
		return "Donor [ " + super.toString() + ", phone=" + phone + ", address=" + address + "]";
	}
    
    
    
}