package com.example.jessi.servicesapp.login;

import android.content.Context;
import android.content.Intent;
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
import com.example.jessi.servicesapp.ValidationModel;
import com.example.jessi.servicesapp.category.CategoryNavActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private Button btnLogin;
    private Button btnRegister;
    private TextView tvForgotPW;

    private EditText email;
    private EditText password;

    ValidationModel validationModel;
    private static final String baseURL = "http://servdoservice.com/api/rest/v1/login.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        validationModel = new ValidationModel();
        validationModel.addPatternString(validationModel.EMAIL_PATTERN);
        validationModel.addPatternString(validationModel.PASSWORD_PATTERN);
        validationModel.addFeildNames("Email");
        validationModel.addFeildNames("Password");
        validationModel.addErrorMessage("(mail@email.com)\n");
        validationModel.addErrorMessage("Please try again.");


        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        tvForgotPW = findViewById(R.id.tv_forgotpw);
        email = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

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
                validationModel.addTextViewString(email.getText().toString());
                validationModel.addTextViewString(password.getText().toString());
                boolean valid = validationModel.validation();
                if (valid) {
                    volleyCall();
                } else {
                    AppController.getInstance().alertUserError(validationModel.getErrormsg(), LoginActivity.this);
                }

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

    String getFinalURL() {
        return baseURL + "email=" + email.getText().toString() + "&password=" + password.getText().toString();
    }

    private void volleyCall() {
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

                            if (result.equals("login_ok")) {
                                Log.d(TAG, "onResponse: Login Ok Started");
                                User tempUser = new User();
                                tempUser.setUserID(response.get("userId").toString());
                                AppController.getInstance().setCurrentUser(tempUser);
                                startNextActivity(LoginActivity.this, CategoryNavActivity.class);
                            } else if (result.equals("email_password_error")) {
                                AppController.getInstance().alertUserError
                                        ("Email or Password are invalid. Please try again.",
                                                LoginActivity.this);

                            } else if (result.equals("mandatory_field_required")) {
                                AppController.getInstance().alertUserError
                                        ("Email/Password are missing. Please complete all fields.",
                                                LoginActivity.this);
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

    private void startNextActivity(Context context, Class next) {
        Intent intent =
                new Intent(context, next);
        startActivity(intent);
    }
}
