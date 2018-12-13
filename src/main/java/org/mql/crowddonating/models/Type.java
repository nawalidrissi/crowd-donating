package org.mql.crowddonating.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String label;

	@ManyToMany(mappedBy = "types")
	private List<Case> cases;

	public Type() {
	}

	public void addCase(Case aCase){
		cases.add(aCase);
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

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}
}