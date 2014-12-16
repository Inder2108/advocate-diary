package com.adv.util;

public enum CaseStatus {
	PENDING("Pending"), DECIDED("Decided");

	private String text;

	CaseStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static CaseStatus fromString(String text) {
		if (text != null) {
			for (CaseStatus section : CaseStatus.values()) {
				if (text.equalsIgnoreCase(section.text)) {
					return section;
				}
			}
		}
		return null;
	}
}
