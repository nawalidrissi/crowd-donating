package org.mql.crowddonating.models;

import org.hibernate.validator.constraints.UniqueElements;

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
    private String name;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private double amount;

    @Column
    private Date deadLine;

    @Column
    private Date postedDate = new Date();

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
    @JoinTable(
            name = "case_type",
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
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
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", deadLine=" + deadLine +
                ", postedDate=" + postedDate +
                ", image='" + image + '\'' +
                '}';
    }
}