package ua.cn.sg.teach.kz.loginregistration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import ua.cn.sg.teach.kz.loginregistration.R;
import ua.cn.sg.teach.kz.loginregistration.sql.DatabaseHelper;
import ua.cn.sg.teach.kz.loginregistration.util.InputValidation;
import ua.cn.sg.teach.kz.loginregistration.util.PreferenceManager;
import ua.cn.sg.teach.kz.loginregistration.util.Utils;

import static ua.cn.sg.teach.kz.loginregistration.Constants.EMAIL_ATTRIBUTE_KEY;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatButton appCompatButtonRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
        String email = PreferenceManager.readUserEmail(getApplicationContext());
        if (email != null && !email.isEmpty()){
            startUserActivity(email);
        }
    }


    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        appCompatButtonRegister.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                Utils.hideSoftKeyboard(activity);
                if(verifyFields()){
                    String email = textInputEditTextEmail.getText().toString().trim();
                    emptyInputEditText();
                    startUserActivity(email);
                }
                break;
            case R.id.appCompatButtonRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void startUserActivity(String email){
        Intent accountsIntent = new Intent(getApplicationContext(), UserActivity.class);
        accountsIntent.putExtra(EMAIL_ATTRIBUTE_KEY, email);
        PreferenceManager.writeUserEmail(getApplicationContext(), email);
        startActivity(accountsIntent);
    }

    private boolean verifyFields(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return false;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return false;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return false;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , Utils.encrypt(textInputEditTextPassword.getText().toString().trim()))) {
            return true;
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
        return false;
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
