package com.ruble.olinefood.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ruble on 24-01-2018.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {
    Context ctx;
    Activity activity;
    String registration_url="http://"+IpConfig.ip+"/online_food_app/registration.php";
    String login_url="http://"+IpConfig.ip+"/online_food_app/login.php";
    String item_reg_url="http://"+IpConfig.ip+"/online_food_app/item_reg.php";
    String item_update_url="http://"+IpConfig.ip+"/online_food_app/item_update.php";
    String item_stock_update_url="http://"+IpConfig.ip+"/online_food_app/item_stock_update.php";
    String stock_check_url="http://"+IpConfig.ip+"/online_food_app/stock_check.php";
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    String quantity;
    String account_name;



    public BackgroundTask(Context ctx){
        this.ctx=ctx;
        activity= (Activity) ctx;
    }
    @Override
    protected void onPreExecute() {
        builder=new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Connecting to server....");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {
        String method=strings[0];
        if(method.equals("register")){
            try {
                URL url=new URL(registration_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String name=strings[1];
                String pass=strings[2];
                String address=strings[3];
                String phone=strings[4];
                String data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode("user","UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(method.equals("login")){
            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String phone=strings[2];
                String password=strings[1];
                String data= URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else if (method.equals("item_register")){
            try {
                URL url=new URL(item_reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String id=strings[1];
                String name=strings[2];
                String type=strings[3];
                String price=strings[4];
                String data= URLEncoder.encode("item_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("item_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("item_type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"+
                        URLEncoder.encode("item_price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(method.equals("item_update")){
            try {
                URL url=new URL(item_update_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String id=strings[1];
                String price=strings[2];
                String data= URLEncoder.encode("item_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("item_price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(method.equals("item_stock_update")){
            try {
                URL url=new URL(item_stock_update_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String id=strings[1];
                String stock=strings[2];
                String data= URLEncoder.encode("item_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("item_stock","UTF-8")+"="+URLEncoder.encode(stock,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(method.equals("stock_check")){
            try {
                URL url=new URL(stock_check_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String id=strings[1];
                quantity=strings[2];
                account_name=strings[3];
                String data= URLEncoder.encode("item_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("item_stock","UTF-8")+"="+URLEncoder.encode(quantity,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line+"\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
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
    protected void onPostExecute(String json) {
        try {
            progressDialog.dismiss();
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            JSONObject jo=jsonArray.getJSONObject(0);
            String code=jo.getString("code");
            String message=jo.getString("message");
            if(code.equals("reg_true")||code.equals("item_reg_true")){
                showDialog("Registration Success",message,code);
            }else if (code.equals("reg_false")||code.equals("item_reg_false")){
                showDialog("Registration Failed",message,code);
            }else if (code.equals("login_true")){
                String name=jo.getString("name");
                String type=jo.getString("type");
                if(type.equals("user")){
                    Intent intent= new Intent(activity,UserHomeActivity.class);
                    intent.putExtra("username",name);
                    Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
                    activity.startActivity(intent);
                }
                if(type.equals("manager")){
                    Intent intent= new Intent(activity,ManagerHomeActivity.class);
                    intent.putExtra("username",name);
                    Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
                    activity.startActivity(intent);
                }
            }else if (code.equals("login_false")){
                showDialog("Login Failed",message,code);
            }else if (code.equals("update_true")){
                showDialog("Item Price Update Success",message,code);
            }else if (code.equals("update_false")){
                showDialog("Item Price Update Failed",message,code);
            }else if (code.equals("stock_update_true")){
                showDialog("Item Stock Update Success",message,code);
            }else if (code.equals("stock_update_false")){
                showDialog("Item Stock Update Failed",message,code);
            }else if (code.equals("stock_check_true")||code.equals("stock_check_false")){
                String name=jo.getString("item_name");
                String type=jo.getString("item_type");
                String id=jo.getString("item_id");
                String stock=jo.getString("item_stock");
                String price=jo.getString("item_price");
                if (code.equals("stock_check_true")){
                    DataBaseManager dataBaseManager=new DataBaseManager(activity);
                    String amount=Integer.parseInt(quantity)*Integer.parseInt(price)+"";
                    if(dataBaseManager.addItem(id,name,quantity,amount,account_name)==true){
                        Toast.makeText(activity, name+" Added To Cart"+account_name, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(activity,UserHomeActivity.class);
                        activity.startActivity(intent);
                    }
                }else if (code.equals("stock_check_false")){
                    Toast.makeText(activity, "Sorry Entered Quantity "+name+" Is Not Available", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(activity,AddToCartActivity.class);
                    intent.putExtra("item_id",id);
                    intent.putExtra("item_name",name);
                    intent.putExtra("item_price",price);
                    intent.putExtra("item_type",type);
                    intent.putExtra("item_stock",stock);
                    activity.startActivity(intent);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void showDialog(String title,String message,String code){
        builder.setTitle(title);
        if (code.equals("reg_true")||code.equals("reg_false")){
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    activity.finish();
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }else if (code.equals("login_false")){
            builder.setMessage(message);
            EditText phonenumber,passwords;
            phonenumber=activity.findViewById(R.id.phoneLoginEditText);
            passwords=activity.findViewById(R.id.passwordLoginEditText);
            phonenumber.setText("");
            passwords.setText("");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        } else if (code.equals("update_true")||code.equals("update_false")||code.equals("stock_update_true")||code.equals("stock_update_false")){
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText updateIdEditText,updatePriceEditText;
                    updateIdEditText=activity.findViewById(R.id.updateIdEditText);
                    updatePriceEditText=activity.findViewById(R.id.updatePriceEditText);
                    updateIdEditText.setText("");
                    updatePriceEditText.setText("");
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }else if (code.equals("item_reg_true")||code.equals("item_reg_false")){
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText itemIdEditText=null;
                    EditText itemNameEditText=null;
                    EditText itemTypeEditText=null;
                    EditText itemPriceEditText=null;
                    itemIdEditText=activity.findViewById(R.id.itemIdEditText);
                    itemNameEditText=activity.findViewById(R.id.itemNameEditText);
                    itemTypeEditText=activity.findViewById(R.id.itemTypeEditText);
                    itemPriceEditText=activity.findViewById(R.id.itemPriceEditText);
                    itemIdEditText.setText("");
                    itemNameEditText.setText("");
                    itemTypeEditText.setText("");
                    itemPriceEditText.setText("");
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }
    }
}
