package com.example.jessi.servicesapp.subcategory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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

public class SubCategoryActivity extends AppCompatActivity {

    private static final String TAG = "SubCategoryActivity";
    private static final String BASE_URL = "http://servdoservice.com/api/rest/v1/subCategories.php?categoryId=";
    private SubCategoryModel subCategoryModel;
    private String subCategoryId;
    private String subCategoryTitle;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tvSubCategoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        subCategoryId = getIntent().getExtras().getString("SUB_CATEGORIES");
        subCategoryTitle = getIntent().getExtras().getString("SUB_CATEGORY_TITLE");
        tvSubCategoryTitle = findViewById(R.id.tv_subcategory_title);
        tvSubCategoryTitle.setText(subCategoryTitle);
        Log.d(TAG, "onCreate: " + getIntent().getExtras().getString("SUB_CATEGORIES"));
        volleyCall();
    }

    private void volleyCall() {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.POST,
                        getFinalURL(),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "onResponse: " + response.toString());
                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = response.getJSONArray("SUB_CATEGORIES");
                                    List<SubCategoryModel> subCategoryModelList = new ArrayList<>();
                                    for(int i = 0; i < jsonArray.length(); i++){
                                        SubCategoryModel subCategoryModel = new SubCategoryModel(
                                                jsonArray.getJSONObject(i).getString("subCategoryId"),
                                                jsonArray.getJSONObject(i).getString("categoryId"),
                                                jsonArray.getJSONObject(i).getString("subCategoryName"),
                                                jsonArray.getJSONObject(i).getString("subCategoryImage"),
                                                jsonArray.getJSONObject(i).getString("subCategoryDescription")
                                        );
                                        subCategoryModelList.add(subCategoryModel);
                                    }
                                    Log.d(TAG, "onResponse: Adapter View Started");
                                    layoutManager = new GridLayoutManager(SubCategoryActivity.this, 2);//new LinearLayoutManager(SubCategoryActivity.this);
                                    adapter = new SubCategoryAdapter(SubCategoryActivity.this, subCategoryModelList);
                                    recyclerView = findViewById(R.id.rv_subcategory);
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private String getFinalURL() {
        return BASE_URL + subCategoryId;
    }
}
