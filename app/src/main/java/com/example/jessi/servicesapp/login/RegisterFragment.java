package com.example.jessi.servicesapp.login;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.jessi.servicesapp.AppController;
import com.example.jessi.servicesapp.R;
import com.example.jessi.servicesapp.ValidationModel;


import org.json.JSONException;
import org.json.JSONObject;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    EditText etName;
    EditText etPhone;
    EditText etEmail;
    EditText etPassword;
    Button btnRegister;

    ValidationModel validationModel;

    private final static String url = "http://servdoservice.com/api/rest/v1/registration.php?";
    private String  finalUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        validationModel = new ValidationModel();
        validationModel.addPatternString(validationModel.USERNAME_PATTERN);
        validationModel.addPatternString(validationModel.MOBILE_PATTERN);
        validationModel.addPatternString(validationModel.EMAIL_PATTERN);
        validationModel.addPatternString(validationModel.PASSWORD_PATTERN);

        validationModel.addFeildNames("Name");
        validationModel.addFeildNames("Mobile Phone");
        validationModel.addFeildNames("Email");
        validationModel.addFeildNames("Password");

        validationModel.addErrorMessage(": No symbols or numbers\n");
        validationModel.addErrorMessage(": Ten Digits Only\n");
        validationModel.addErrorMessage(": mail@email.com\n");
        validationModel.addErrorMessage(": No spaces\n");

        etName      =  view.findViewById(R.id.et_name);
        etPhone = view.findViewById(R.id.et_mobile);
        etEmail     = view.findViewById(R.id.et_email);
        etPassword  = view.findViewById(R.id.et_password_reg);
        btnRegister = view.findViewById(R.id.btn_Send);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareURL(etName.getText().toString(),
                        etPhone.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString());

                validationModel.addTextViewString(etName.getText().toString());
                validationModel.addTextViewString(etPhone.getText().toString());
                validationModel.addTextViewString(etEmail.getText().toString());
                validationModel.addTextViewString(etPassword.getText().toString());

                boolean valid = validationModel.validation();
                if (valid){
                    volleyCall();
                }
                else{
                    AppController.getInstance().alertUserError(validationModel.getErrormsg(), getContext() );
                }

            }
        });
        return view;
    }



    private void prepareURL(String name, String mobile, String email, String password){
        finalUrl = this.url + "name=" + name +"&phone=" + mobile + "&email=" +email +"&password="+ password;
        Log.d(TAG, "prepareURL: " + finalUrl);

    }
    private void volleyCall()
    {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.POST,
                        finalUrl,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String result = response.getString("result");
                                    if (result.equals("registration_ok")) {
                                        AppController.getInstance().alertUserError
                                                ("Registration Successful! Please login", getActivity());
                                        getActivity().getSupportFragmentManager().popBackStack();
                                    }
                                    else
                                    if (result.equals("mandatory_field_required")){
                                        AppController.getInstance().alertUserError
                                                ("One or more fields are empty. Please complete all fields to register.", getActivity());
                                    }
                                    else
                                    if (result.equals("email_id_error")){
                                        AppController.getInstance().alertUserError
                                                ("Email is Invalid or in use.", getActivity() );

                                    }
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
}
