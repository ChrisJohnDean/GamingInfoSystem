/**
 * Project: a01001690Gis
 * File: Validator.java
 * Date: Feb 24, 2017
 * Time: 3:30:17 AM
 */
package a01001690.player.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a01001690.data.ApplicationException;

/**
 * @author chrisdean A01001690
 *
 */
public class Validator {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	public static String validateEmail(String emailStr) throws ApplicationException {
		if (!validate(emailStr)) {
			throw new ApplicationException("'" + emailStr + "'" + " is not a valid email.");
		}
		return emailStr;
	}

	/*
	 * public static String validateEmail(String emailStr) {
	 * return validate(emailStr) ? emailStr : "N/A";
	 * }
	 */
}
