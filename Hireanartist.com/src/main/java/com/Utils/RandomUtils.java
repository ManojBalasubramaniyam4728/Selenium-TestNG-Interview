package com.Utils;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
	
	public static String getFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Ram"+generatedString);
	}
	
	public static String getLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Laxman"+generatedString);
	}
	
	public static String getPassword() {
		String generatedString = RandomStringUtils.randomNumeric(3);
		return("Password@"+generatedString);
	}
	
	public static String getEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return("ram"+generatedString+"@gmail.com");
	}
	
	public static String getPhoneNumber() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return("98807"+generatedString);
	}

}
