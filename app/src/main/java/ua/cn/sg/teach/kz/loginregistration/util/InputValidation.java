package ua.cn.sg.teach.kz.loginregistration.util;

import android.app.Activity;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

/**
 * Created by kz on 21.05.17.
 */

public class InputValidation {

    private Activity context;

    public InputValidation(Activity context){
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || value.length()<4){
            textInputLayout.setError(message);
            hideKeyboardFrom();
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || value.length()<6 || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            hideKeyboardFrom();
            return false;

        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message ){
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom();
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void hideKeyboardFrom(){
        Utils.hideSoftKeyboard(context);
    }

}
