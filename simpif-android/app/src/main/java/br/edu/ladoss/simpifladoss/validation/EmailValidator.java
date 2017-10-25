package br.edu.ladoss.simpifladoss.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ladoss.simpifladoss.util.StringUtil;

public class EmailValidator{

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param email
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String email) {
		
		boolean isEmail = false;
		
		if(!StringUtil.isEmptyOrNull(email)) {
			matcher = pattern.matcher(email.trim());
			isEmail = matcher.matches();
		}
		
		return isEmail;
	}
}
