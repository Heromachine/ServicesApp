package com.example.jessi.servicesapp.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.jessi.servicesapp.R;

public class LogInDialog extends Dialog implements android.view.View.OnClickListener {

    Button btnOk;
    Dialog dialog;
    String message;
    TextView dialogMessage;

    public LogInDialog(Context context, String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_login);
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);

        dialogMessage = findViewById(R.id.tv_dialog);
        dialogMessage.setText(this.message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                dismiss();
        }

    }
}
