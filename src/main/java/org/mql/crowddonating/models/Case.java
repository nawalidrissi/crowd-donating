package org.mql.crowddonating.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "_case")
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;
	
	@Column
	private Date postedDate;

	@Column
	private String description;

	@Column
	private String deadLine;

	@Column
	private double amount;

	@Column
	private String image;

	@ManyToOne
	@JoinColumn(name = "association_id")
	private Association association;

	@OneToMany(mappedBy = "aCase", fetch = FetchType.LAZY)
	private List<File> files;

	@OneToMany(mappedBy = "aCase", fetch = FetchType.LAZY)
	private List<Donation> donations;

	@ManyToMany
	@JoinTable(name = "case_type", joinColumns = @JoinColumn(name = "type_id"), inverseJoinColumns = @JoinColumn(name = "case_id"))
	private List<Type> types;

	public Case() {
		postedDate = new Date();
	}

	public void addDonation(Donation donation) {
		donations.add(donation);
	}

	public void addFile(File file) {
		files.add(file);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public double getAmount() {
		return amount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}
	
	public Date getPostedDate() {
		return postedDate;
	}

}