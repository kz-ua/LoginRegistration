package ua.cn.sg.teach.kz.loginregistration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import ua.cn.sg.teach.kz.loginregistration.R;
import ua.cn.sg.teach.kz.loginregistration.util.PreferenceManager;

import static ua.cn.sg.teach.kz.loginregistration.Constants.EMAIL_ATTRIBUTE_KEY;

/**
 * Created by kz on 21.05.17.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewName;
    private AppCompatButton appCompatButtonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();

        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra(EMAIL_ATTRIBUTE_KEY);
        textViewName.setText("Welcome " + nameFromIntent);

        initViews();
        initListeners();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogout:
                PreferenceManager.writeUserEmail(getApplicationContext(), null);
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLogin);
                break;
        }
    }

    private void initListeners(){
        appCompatButtonLogout.setOnClickListener(this);
    }
    private void initViews(){
        appCompatButtonLogout = (AppCompatButton) findViewById(R.id.appCompatButtonLogout);
    }

}
