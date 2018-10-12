package com.example.jessi.servicesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegister;
    TextView tvForgotPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        tvForgotPW = findViewById(R.id.tv_forgotpw);

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
                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);

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
}
