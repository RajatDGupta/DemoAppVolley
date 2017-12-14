package com.example.neeraj.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonArrayClass extends AppCompatActivity {

    RecyclerView recyclerView;
    JsonAdapter jsonAdapter;
    ArrayList<PojoClass> arrayList;
    RecyclerView.LayoutManager layoutManager;
    String json_url = "http://neerajgupta.netne.net/Volley/jsonArray.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array_class);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getjsondata();
    }

    private void getjsondata() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        arrayList=new ArrayList<>();
                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                PojoClass pojoClass = new PojoClass(jsonObject.getString("name"),
                                        jsonObject.getString("email"),
                                        jsonObject.getString("mobile"));

                                arrayList.add(pojoClass);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        jsonAdapter = new JsonAdapter(arrayList);
                        recyclerView.setAdapter(jsonAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JsonArrayClass.this, "Error......", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingletonClass.getInstance(JsonArrayClass.this).addToRequestQue(jsonArrayRequest);
    }


}
