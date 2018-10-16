package com.example.jessi.servicesapp.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jessi.servicesapp.AppController;
import com.example.jessi.servicesapp.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";
    private static final String URL = "http://servdoservice.com/api/rest/v1/categories.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_category);
        volleyCall();
    }

    private void volleyCall() {
        Log.d(TAG, "volleyCall: ");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: Started");
                        try {
                            Log.d(TAG, "onResponse: Try Started");
                            JSONArray jsonArray = response.getJSONArray("CATEGORIES");
                            List<CategoryModel> categoryModelList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.d(TAG, "onResponse: Array at " + i + " = " + jsonArray.getJSONObject(i).getString("categoryName"));
                                CategoryModel categoryModel = new CategoryModel(
                                        jsonArray.getJSONObject(i).getString("categoryId"),
                                        jsonArray.getJSONObject(i).getString("categoryName"),
                                        jsonArray.getJSONObject(i).getString("categoryImage"),
                                        jsonArray.getJSONObject(i).getString("categoryDescription")
                                );
                                categoryModelList.add(categoryModel);
                            }
                            //TODO ADD CARDVIEW ADAPTER
                            Log.d(TAG, "onResponse: Adapter View Started");

                            recyclerView = findViewById(R.id.rv_category);
                            layoutManager = new LinearLayoutManager(CategoryActivity.this);
                            recyclerView.setLayoutManager(layoutManager);

                            adapter = new CategoryAdapter(CategoryActivity.this, categoryModelList);
                            recyclerView.setAdapter(adapter);
                            //TODO END TODO

                        } catch (JSONException e) {
                            Log.d(TAG, "onResponse: error = " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Error: " + error.getMessage());
            }
        }
        );
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
