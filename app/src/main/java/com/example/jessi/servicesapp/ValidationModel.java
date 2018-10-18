package com.example.jessi.servicesapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValidationModel {
    private static final String TAG = "Model_ValSTARTED";


    public final String USERNAME_PATTERN = "^[ A-Za-z0-9._-]{3,15}$";
    public final String PASSWORD_PATTERN = "^[A-Za-z0-9.-_!]{6,18}$";
    public final String EMAIL_PATTERN = "^[a-zA-Z0-9._-]{3,20}@[a-zA-Z0-9]{3,9}.com$";
    public final String DOB_PATTERN = "^(\\d{2}-?\\d{2}-?\\d{4})$";
    public final String MOBILE_PATTERN = "^(\\d{3}?\\d{3}?\\d{4})$";

    boolean match = false;
    private Pattern pattern;

    boolean isVal = true;

    ArrayList<String> fieldNamesList;
    ArrayList<String> patternList;
    ArrayList<String> textViewList;
    ArrayList<Boolean> fieldResultList;
    ArrayList<String> errorMessageList;

    String errormsg;

    int index = 0;

    //--------------------------------------------------------------------------------------------------
    public ValidationModel() {
        textViewList = new ArrayList<>();
        fieldNamesList = new ArrayList<>();
        fieldResultList = new ArrayList<>();
        patternList = new ArrayList<>();
        errorMessageList = new ArrayList<>();
    }

    //--------------------------------------------------------------------------------------------------
    public ValidationModel(final ArrayList<String> patterns,
                           ArrayList<String> fieldNames,
                           ArrayList<String> errorMessageList) {
        Log.d(TAG, "ModelValidation: STARTED");

        patternList = patterns;
        this.errorMessageList = errorMessageList;
        textViewList = new ArrayList<>();
        fieldNamesList = new ArrayList<>();
        fieldResultList = new ArrayList<>();

        for (int i = 0; i < patternList.size(); i++) {
            fieldNamesList.add(fieldNames.get(i));
        }
    }

    public void addPatternString(final String input) {
        patternList.add(input);
    }

    //--------------------------------------------------------------------------------------------------
    public void addTextViewString(String input) {
        textViewList.add(input);
    }

    public void addErrorMessage(String errormsg) {
        errorMessageList.add(errormsg);
    }

    public void addFeildNames(String name) {
        fieldNamesList.add(name);
    }

    //--------------------------------------------------------------------------------------------------
    public boolean validation() {
        Log.d(TAG, "validation: STARTED");

        this.isVal = true;
        for (int i = 0; i < patternList.size(); i++) {
            pattern = Pattern.compile(patternList.get(i));
            match = pattern.matcher(textViewList.get(i)).matches();
            if (!match) {
                Boolean tempbool = new Boolean(false);
                fieldResultList.add(tempbool);
                isVal = false;
            } else {
                Boolean tempbool = new Boolean(true);
                fieldResultList.add(tempbool);
            }
        }
        setFailResuls();
        //Clear for another attempt
        textViewList.clear();
        fieldResultList.clear();
        return isVal;
    }

    //--------------------------------------------------------------------------------------------------
    private void setFailResuls() {
        Log.d(TAG, "setFailResults: STARTED");
        errormsg = "ERROR: \n";

        for (int i = 0; i < fieldNamesList.size(); i++) {
            if (fieldResultList.get(i).booleanValue()) {
                errormsg += "\nSuccess: " + fieldNamesList.get(i) + "\n";
                continue;
            } else {
                errormsg += "\nError: " + fieldNamesList.get(i) + ". " + errorMessageList.get(i) + "\n";
            }
        }
    }

    public String getErrormsg() {
        Log.d(TAG, "getErrormsg: STARTED");
        return this.errormsg;
    }

    private boolean isMatch() {
        return match;
    }


}
