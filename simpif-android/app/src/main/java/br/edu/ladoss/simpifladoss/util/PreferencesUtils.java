package br.edu.ladoss.simpifladoss.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 18/10/17.
 */

public class PreferencesUtils {

    private static String ACCESS_KEY_INDEX = "chave";

    public static void setAccessKeyOnSharedPreferences(Context context, String chave) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(ACCESS_KEY_INDEX, chave).apply();
    }

    public static String getAccessKeyOnSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accesskey = sharedPreferences.getString(ACCESS_KEY_INDEX, "");

        return accesskey;
    }
}
