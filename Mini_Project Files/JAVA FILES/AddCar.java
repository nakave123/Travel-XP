package com.darkfighter.basicui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AddCar extends AppCompatActivity {

    EditText ET_CAR_NO,ET_CAR_NAME,ET_CAR_CAPACITY,ET_RATE;
    String car_no,car_name,car_capacity,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        setTitle("Add Car");

        ET_CAR_NO = (EditText)findViewById(R.id.editText_car_no);
        ET_CAR_NAME = (EditText)findViewById(R.id.editText_car_name);
        ET_CAR_CAPACITY = (EditText)findViewById(R.id.editText_car_capacity);
        ET_RATE = (EditText)findViewById(R.id.editText_rate);

    }

    public void onAdding(View view) {
        car_no = ET_CAR_NO.getText().toString();
        car_name = ET_CAR_NAME.getText().toString();
        car_capacity = ET_CAR_CAPACITY.getText().toString();
        rate = ET_RATE.getText().toString();
        if(TextUtils.isEmpty(car_no)) {
            ET_CAR_NO.setError("Enter Car_No");
        }
        else if (TextUtils.isEmpty(car_name)) {
            ET_CAR_NAME.setError("Enter Car_Name");
        }
        else if (TextUtils.isEmpty(car_capacity)) {
            ET_CAR_CAPACITY.setError("Enter Car_Capacity");
        }
        else if (TextUtils.isEmpty(rate)) {
            ET_RATE.setError("Enter Rate");
        }
        else {
            String method = "adding";
            ET_CAR_NO.setText("");
            ET_CAR_NAME.setText("");
            ET_CAR_CAPACITY.setText("");
            ET_RATE.setText("");

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, car_no, car_name, car_capacity, rate);
            finish();
        }
    }


}
