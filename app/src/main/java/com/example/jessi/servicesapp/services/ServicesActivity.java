package com.example.jessi.servicesapp.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jessi.servicesapp.R;


public class ServicesActivity extends AppCompatActivity {

    private static final String TAG = "ServicesActivity";
    private static final String BASE_URL = "http://servdoservice.com/api/rest/v1/services.php?subCategoryId=";
    //TODO NEED SERVICES MODEL
    private String servicesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        servicesId = getIntent().getExtras().getString("SERVICES");
        Toast.makeText(this, "SERVICE # "+ servicesId, Toast.LENGTH_SHORT).show();

    }
}
