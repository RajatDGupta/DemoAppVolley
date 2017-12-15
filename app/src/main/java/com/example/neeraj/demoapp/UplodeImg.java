package com.example.neeraj.demoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UplodeImg extends AppCompatActivity implements View.OnClickListener {

    Button bt_imgPick, bt_img_uplode;
    ImageView imageView;
    final int IMG_REQ_CODE=1234;
    Bitmap bitmap;
    String url="http://neerajgupta.netne.net/Volley/uplodeImg.php";
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplode_img);

        init();
        bt_imgPick.setOnClickListener(this);
        bt_img_uplode.setOnClickListener(this);

    }

    private void init() {

        imageView = findViewById(R.id.imgView);
        bt_imgPick = findViewById(R.id.imgPick);
        bt_img_uplode = findViewById(R.id.imgUplode);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPick:
                selectImg();
                break;
            case R.id.imgUplode:
                uploadeImg();
                break;

        }
    }

   public void uploadeImg() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UplodeImg.this, "hi", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UplodeImg.this, "by", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               HashMap<String,String> params=new HashMap<String, String>();
               params.put("image",bitmapToString(bitmap));
               params.put("name",file.getName());
               return params;
            }
        };

          MySingletonClass.getInstance(UplodeImg.this).addToRequestQue(stringRequest);
    }

    private void selectImg() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQ_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMG_REQ_CODE && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                if(uri!=null) {
                    file = new File(uri.getPath());
                }
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imageByte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte,Base64.DEFAULT);
    }
}
