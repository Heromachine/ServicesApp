package com.example.jessi.servicesapp.services;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessi.servicesapp.R;
import com.squareup.picasso.Picasso;


public class ServicesActivity extends AppCompatActivity {

    private static final String TAG = "ServicesActivity";
    private static final String BASE_URL = "http://servdoservice.com/api/rest/v1/services.php?subCategoryId=";
    //TODO NEED SERVICES MODEL
    private String servicesId;

    ImageView serviceImage;
    TextView serviceName;
    TextView serviceDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        serviceImage = findViewById(R.id.iv_service);
        serviceName = findViewById(R.id.tv_service_name);
        serviceDescription = findViewById(R.id.tv_service_description);

        Log.d(TAG, "onCreate2: "
                + getIntent().getExtras().getString("SERVICES") +"/"
                + getIntent().getExtras().getString("DESCRIPTION")+"/"
                +  getIntent().getExtras().getString("IMAGEURL"));

        serviceName.setText(getIntent().getExtras().getString("SERVICES"));
        serviceDescription.setText(getIntent().getExtras().getString("DESCRIPTION"));
        Picasso
                .get()
                .load( getIntent().getExtras().getString("IMAGEURL"))
                .placeholder(R.drawable.banner)
                .into(serviceImage);


        //Toast.makeText(this, "SERVICE # "+ servicesId, Toast.LENGTH_SHORT).show();

    }
}
