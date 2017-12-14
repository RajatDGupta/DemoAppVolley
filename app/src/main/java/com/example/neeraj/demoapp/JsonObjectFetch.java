package com.example.neeraj.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectFetch extends AppCompatActivity {

    String json_url = "http://neerajgupta.netne.net/Volley/getInfo.php";
    TextView tv_name, tv_mobile, tv_email;
    Button bt_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_object_fetch);


        tv_email = findViewById(R.id.email);
        tv_mobile = findViewById(R.id.mobile);
        tv_name = findViewById(R.id.name);

        bt_click = findViewById(R.id.bt_getData);

        bt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url
                        , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tv_name.setText(response.getString("name"));
                            tv_email.setText(response.getString("email"));
                            tv_mobile.setText(response.getString("mobile"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JsonObjectFetch.this, "something went to worng", Toast.LENGTH_SHORT).show();
                    }
                });

                MySingletonClass.getInstance(JsonObjectFetch.this).addToRequestQue(jsonObjectRequest);
            }
        });
    }
}
