package ua.cn.sg.teach.kz.loginregistration.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kz on 21.05.17.
 */

public class PreferenceManager {

    public static final String FILE_NAME = "ua.cn.sg.kz.lr.preference.FILE";

    private static final String CURRENT_USER_EMAIL_KEY = "CURRENT_USER_EMAIL";

    public static void writeUserEmail(Context context, String email){
        writeSessionKey(context, CURRENT_USER_EMAIL_KEY, email);
    }

    public static String readUserEmail(Context context){
        return readSessionKey(context, CURRENT_USER_EMAIL_KEY);
    }

    public static void writeSessionKey(Context context, String key, String email){
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (email != null) {
            editor.putString(key, email);
        }else{
            editor.remove(key);
        }
        editor.commit();
    }

    public static String readSessionKey(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
}
