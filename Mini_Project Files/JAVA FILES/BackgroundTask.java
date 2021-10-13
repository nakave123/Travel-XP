package com.darkfighter.basicui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    static String a;
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }
    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://projtest.orgfree.com/webappdb/register.php";
        String login_url = "http://projtest.orgfree.com/webappdb/login.php";
        String adding_url = "http://projtest.orgfree.com/webappdb/add_car.php";
        String method = params[0];
        if ("register".equalsIgnoreCase(method)) {
            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];
            String user_type = params[4];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8") + "&" +
                        URLEncoder.encode("user_type", "UTF-8") + "=" + URLEncoder.encode(user_type, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Registration Success...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("login".equalsIgnoreCase(method))
        {
            String login_name = params[1];
            a = login_name;
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("adding".equalsIgnoreCase(method)) {
            String car_no = params[1];
            String car_name = params[2];
            String car_capacity = params[3];
            String rate = params[4];
            try {
                URL url = new URL(adding_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8") + "&" +
                        URLEncoder.encode("car_name", "UTF-8") + "=" + URLEncoder.encode(car_name, "UTF-8") + "&" +
                        URLEncoder.encode("car_capacity", "UTF-8") + "=" + URLEncoder.encode(car_capacity, "UTF-8") + "&" +
                        URLEncoder.encode("rate", "UTF-8") + "=" + URLEncoder.encode(rate, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Car Added Successfully...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if("removing".equalsIgnoreCase(method)) {
            String car_no = params[1];
            try {
                URL url = new URL(adding_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("car_no", "UTF-8") + "=" + URLEncoder.encode(car_no, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();
                return "Car Removed Successfully...";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if("Registration Success...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("customer".equalsIgnoreCase(result)) {
            Intent intent = new Intent(ctx, AfterLogin.class);
            ctx.startActivity(intent);
        }
        else if("owner".equalsIgnoreCase(result)) {
            Intent intent = new Intent(ctx, Owner.class);
            intent.putExtra("a",a);
            ctx.startActivity(intent);
        }
        else if("Login Failed".equalsIgnoreCase((result))){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if("Car Added Successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if("Car Removed Successfully...".equalsIgnoreCase(result)) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}