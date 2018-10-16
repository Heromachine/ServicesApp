package com.example.jessi.servicesapp.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jessi.servicesapp.AppController;
import com.example.jessi.servicesapp.R;
import com.example.jessi.servicesapp.User;
import com.example.jessi.servicesapp.category.CategoryActivity;
import com.example.jessi.servicesapp.category.CategoryNav;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Button btnLogin;
    private Button btnRegister;
    private TextView tvForgotPW;

    private EditText email;
    private EditText password;

    private static final String baseURL = "http://servdoservice.com/api/rest/v1/login.php?" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin    = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        tvForgotPW  = findViewById(R.id.tv_forgotpw);
        email       = findViewById(R.id.et_username);
        password    = findViewById(R.id.et_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.regframefrag, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyCall();
            }

        });

        tvForgotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fpwframefrag, new ForgotPasswordFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    String getFinalURL(){
        return baseURL +"email="+email.getText().toString()+"&password="+ password.getText().toString();
    }

    private void volleyCall(){
        Log.d(TAG, "volleyCall: ");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                getFinalURL(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response.toString());

                        try {
                            String result = response.get("result").toString();

                            if (result.equals("login_ok"))
                            {
                                Log.d(TAG, "onResponse: Login Ok Started");
                                User tempUser = new User();
                                tempUser.setUserID(response.get("userId").toString());
                                AppController.getInstance().setCurrentUser(tempUser);
                                startNextActivity(LoginActivity.this, CategoryNav.class);
                            }
                            else if(result.equals("email_password_error")){

                                alertUserError("Email or Password are invalid. Please try again.", LoginActivity.this);
                            }
                            else if (result.equals("mandatory_field_required")){
                                alertUserError("Email/Password are missing. Please complete all fields.", LoginActivity.this);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        }
        );
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void startNextActivity(Context context, Class next){
        Intent intent =
                new Intent(context, next);
        startActivity(intent);
    }

    private void alertUserError(String message, Context context){
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setMessage(message)
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        AlertDialog emptyFieldAlert = alert.create();
//        emptyFieldAlert.show();

        LogInDialog logInDialog = new LogInDialog(context, message);
        logInDialog.show();
    }


}
