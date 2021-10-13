package com.darkfighter.basicui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ET_USER_NAME,ET_USER_PASS;
    String login_name,login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_USER_NAME = (EditText)findViewById(R.id.user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.user_pass);

    }

    public void userReg(View view) {
        startActivity(new Intent(this,Register.class));
    }

    public void userLogin(View view) {
        login_name = ET_USER_NAME.getText().toString();
        login_pass = ET_USER_PASS.getText().toString();
        if(TextUtils.isEmpty(login_name)) {
            ET_USER_NAME.setError("Enter username");
        }
        else if(TextUtils.isEmpty(login_pass)) {
            ET_USER_PASS.setError("Enter password");
        }
        else {
            String method = "login";
            ET_USER_NAME.setText("");
            ET_USER_PASS.setText("");
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, login_name, login_pass);
        }
    }
}
