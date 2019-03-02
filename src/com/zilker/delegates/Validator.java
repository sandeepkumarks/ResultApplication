package com.zilker.delegates;

public class Validator {
	public static boolean validate(String dataTovalidate, String regex) {
		if (dataTovalidate.matches(regex)) {
			return true;
		}
		return false;
	}
}
