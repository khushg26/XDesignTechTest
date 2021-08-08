package com.munro.util;

public enum HillCategory {
	MUN,
	TOP;

	public static HillCategory getValueOfCategory(String cat) {
		try {
			return HillCategory.valueOf(cat);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
