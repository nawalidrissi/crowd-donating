package org.mql.crowddonating.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String label;

    @Column
    private String description;

    @Column
    private String deadLine;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;

    @OneToMany(mappedBy = "aCase", fetch = FetchType.LAZY)
    private List<File> files;

    @OneToMany(mappedBy = "aCase", fetch = FetchType.LAZY)
    private List<Donation> donations;

    @ManyToMany
    @JoinTable(name = "case_type",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "case_id")
    )
    private List<Type> types;

    public Case() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

	@Override
	public String toString() {
		return "Case [id=" + id + ", label=" + label + ", description=" + description + ", deadLine=" + deadLine
				+ ", amount=" + amount + ", association=" + association + ", files=" + files + ", donations="
				+ donations + ", types=" + types + "]";
	}
    
    
}