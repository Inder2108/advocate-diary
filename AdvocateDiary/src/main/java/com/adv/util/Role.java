package com.adv.util;

public enum Role {
	PETITIONER("Petitioner"), RESPONDENT("Respondent");

	private String text;

	Role(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static Role fromString(String text) {
		if (text != null) {
			for (Role section : Role.values()) {
				if (text.equalsIgnoreCase(section.text)) {
					return section;
				}
			}
		}
		return null;
	}
}
