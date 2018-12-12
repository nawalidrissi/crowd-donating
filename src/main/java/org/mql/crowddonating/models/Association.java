package org.mql.crowddonating.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Association extends User {

    @Column
    private String phone;
    @Column
    private String address;

    @OneToMany(mappedBy = "association", fetch = FetchType.LAZY)
    private List<Case> cases;

    @OneToMany(mappedBy = "association", fetch = FetchType.LAZY)
    private List<File> files;

    @OneToMany(mappedBy = "association", fetch = FetchType.LAZY)
    private List<Event> events;

    @ManyToMany
    @JoinTable(name = "association_domain",
            joinColumns = @JoinColumn(name = "domain_id"),
            inverseJoinColumns = @JoinColumn(name = "association_id")
    )
    private List<Domain> domains;

    public Association() {
    }

    public void addCase(Case aCase) {
        cases.add(aCase);
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addDomain(Domain domain) {
        domains.add(domain);
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

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
}