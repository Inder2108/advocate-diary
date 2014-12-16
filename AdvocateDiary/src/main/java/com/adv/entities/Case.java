package com.adv.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.adv.util.CaseStatus;
import com.adv.util.Role;

@Entity
public class Case {

	@Id
	@GeneratedValue
	private long id;

	private String caseNumber;

	@Enumerated(EnumType.STRING)
	private Role engagedFor;

	@OneToOne
	private Client client;

	@OneToOne
	private Court court;

	private String oppPartyName;

	private String oppPartyCouncel;

	private String remarks;

	@Enumerated(EnumType.STRING)
	private CaseStatus caseStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Role getEngagedFor() {
		return engagedFor;
	}

	public void setEngagedFor(Role engagedFor) {
		this.engagedFor = engagedFor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public String getOppPartyName() {
		return oppPartyName;
	}

	public void setOppPartyName(String oppPartyName) {
		this.oppPartyName = oppPartyName;
	}

	public String getOppPartyCouncel() {
		return oppPartyCouncel;
	}

	public void setOppPartyCouncel(String oppPartyCouncel) {
		this.oppPartyCouncel = oppPartyCouncel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}
}
