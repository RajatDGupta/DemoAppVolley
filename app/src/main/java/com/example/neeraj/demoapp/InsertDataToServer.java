package com.example.neeraj.demoapp;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertDataToServer extends AppCompatActivity {

    EditText et_name, et_email, et_mobile;
    Button button;
    String url = "http://neerajgupta.netne.net/Volley/InsertData.php";
    String name, email, mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_to_server);

        et_name = findViewById(R.id.editText_name);
        et_email = findViewById(R.id.editText_email);
        et_mobile = findViewById(R.id.editText_mobile);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = et_name.getText().toString();
                email = et_email.getText().toString();
                mobile = et_mobile.getText().toString();


                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject=new JSONObject(response);

                            Toast.makeText(InsertDataToServer.this, jsonObject.getString("code")+" "+
                                    jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InsertDataToServer.this, "somthing went to worng", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> param=new HashMap<String, String>();
                        param.put("name",name);
                        param.put("email",email);
                        param.put("mobile",mobile);
                        return param;
                    }
                };


                MySingletonClass.getInstance(InsertDataToServer.this).addToRequestQue(stringRequest);





            }
        });
    }
}
