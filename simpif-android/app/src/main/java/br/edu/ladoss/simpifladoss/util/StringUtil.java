package br.edu.ladoss.simpifladoss.util;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public static final String STRING_VAZIO = "";

	public static boolean isEmptyOrNull(String value) {

		boolean isEmptyOrNull = false;
		
		if (value == null 
				|| (value != null && value.trim().equals(STRING_VAZIO))) {
			isEmptyOrNull = true;
		}
		
		return isEmptyOrNull;		
	}

	
	public static String replaceLastToEmptySpace(String text, String regex) {
		return replaceLast(text, regex, STRING_VAZIO);
	}

	public static String replaceLast(String text, String regex,
			String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}

    public static String criptografarBase64(String valor){

        try {

            valor = Base64.encodeToString(valor.getBytes("UTF-8"), Base64.DEFAULT);

        } catch (Exception ex) {

            throw new IllegalStateException("Base-64: Cannot encode with UTF-8");
        }

        return valor;
    }


}
