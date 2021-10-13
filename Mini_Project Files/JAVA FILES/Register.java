package com.darkfighter.basicui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Register extends AppCompatActivity {

    private EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
    private String name,user_name,user_pass,user_type;
    private RadioButton RB_USER_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        setTitle("Register");
        ET_NAME = (EditText)findViewById(R.id.name);
        ET_USER_NAME = (EditText)findViewById(R.id.new_user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.new_user_pass);
        RadioButton r = (RadioButton) findViewById(R.id.radio_cust);
        r.setChecked(true);
    }

    public void userReg(View view) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_gr);
        int selectedId = rg.getCheckedRadioButtonId();
        RB_USER_TYPE = (RadioButton) findViewById(selectedId);
        name = ET_NAME.getText().toString();
        user_name = ET_USER_NAME.getText().toString();
        user_pass = ET_USER_PASS.getText().toString();
        user_type = RB_USER_TYPE.getText().toString();
        if(TextUtils.isEmpty(name)) {
            ET_NAME.setError("Enter Car_No");
        }
        else if (TextUtils.isEmpty(user_name)) {
            ET_USER_NAME.setError("Enter username");
        }
        else if (TextUtils.isEmpty(user_pass)) {
            ET_USER_PASS.setError("Enter password");
        }
        else {
            String method = "Register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, name, user_name, user_pass, user_type);
            finish();
        }
    }

}
