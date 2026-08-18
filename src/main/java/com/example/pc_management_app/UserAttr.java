package com.example.pc_management_app;

public enum UserAttr {
	USER("利用者"), STAFF("スタッフ"), TRIAL("体験者");

	private final String label;

	UserAttr(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
