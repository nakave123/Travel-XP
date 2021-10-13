package com.darkfighter.basicui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CarAdapter carAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        listView = (ListView)findViewById(R.id.listview);
        carAdapter = new CarAdapter(this,R.layout.row_layout);
        listView.setAdapter(carAdapter);
        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            int count=0;
            String car_no,car_name;
            jsonArray = jsonObject.getJSONArray("server_response");
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                car_no = JO.getString("Car_No");
                car_name = JO.getString("Car_Name");
                Cars car = new Cars(car_no,car_name);
                carAdapter.add(car);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
