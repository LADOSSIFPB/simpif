package br.edu.ladoss.simpifladoss.validation;

public class Validate {

	private static EmailValidator emailValidator = new EmailValidator();

	public static boolean validaIdentificador(String identificador) {

		boolean isValidated = false;

		if (emailValidator.validate(identificador)){
			isValidated = true;
		}

		return isValidated;
	}

}
