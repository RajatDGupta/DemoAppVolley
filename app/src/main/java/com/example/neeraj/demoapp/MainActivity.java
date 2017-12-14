package com.example.neeraj.demoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {


    boolean doubleTap = false;
    Button button;
    TextView textView;
    String url = "http://neerajgupta.netne.net/Volley/hellow.php";
    String imageUrl = "http://neerajgupta.netne.net/Volley/Images/1332ipmsg.png";


    ImageView imageView;
    Button bt_getImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        button = findViewById(R.id.bt_click);


        imageView = findViewById(R.id.imageView);
        bt_getImage = findViewById(R.id.bt_getImage);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("something went to worng");
                        error.printStackTrace();
                    }
                });


                MySingletonClass.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
            }
        });


        bt_getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);

                    }

                }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "somthing went to worng", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

                MySingletonClass.getInstance(getApplicationContext()).addToRequestQue(imageRequest);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.jsonObject:
                Intent intent = new Intent(MainActivity.this, JsonObjectFetch.class);
                startActivity(intent);
                return true;
            case R.id.jsonArray:
                Intent intent1 = new Intent(MainActivity.this, JsonArrayClass.class);
                startActivity(intent1);
                return true;
            case R.id.insertData:
                Intent intent2 = new Intent(MainActivity.this, InsertDataToServer.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "please press back again", Toast.LENGTH_SHORT).show();
            doubleTap = true;
        }
    }


}

