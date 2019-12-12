package com.uldskull.tp_intent_v2;
/**
 * LoginActivity.java        2019.12.23
 * CAI-IP03 Millau 2019-2020, Pas de copyright
 */

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * LoginActivity class.
 *
 * @Author : Pierre ANTOINE
 */
public class LoginActivity extends AppCompatActivity {

    private final String PACKAGE_PATH = "com.uldskull.tp_intent_v2";

    //  String for defaults login and password.
    String defaultLogin = "pierre";
    String defaultPassword = "0000";

    //  Buttons
    Button buttonValidateByClass;
    Button buttonValidateByClassName;
    Button buttonValidateByComponentName;
    Button buttonValidateImplicitIntent;
    Button buttonCancel;

    //  Edit-Text
    EditText editTextLogin;
    EditText editTextPassword;

    //  Typed strings
    private String typedPassword;
    private String typedLogin;


    /**
     * Entry point of the activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Initialize the components

        // Edit texts
        editTextLogin = findViewById(R.id.editText_login);
        if (editTextLogin != null) {
            editTextLogin.setText(defaultLogin);
        }
        editTextPassword = findViewById(R.id.editText_password);
        if (editTextPassword != null) {
            editTextPassword.setText(defaultPassword);
        }

        // Cancel button
        buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent();
                setResult(666, loginIntent);
                finish();
            }
        });

        // VALIDATE BUTTONS //

        // BY CLASS
        buttonValidateByClass = findViewById(R.id.button_validate_by_class);
        if (buttonValidateByClass != null) {
            buttonValidateByClass.setOnClickListener(byClassListener());
        }

        // BY CLASS-NAME
        buttonValidateByClassName = findViewById(R.id.button_validate_by_class_name);
        if (buttonValidateByClassName != null) {
            buttonValidateByClassName.setOnClickListener(byClassNameListener());
        }

        //  BY COMPONENT NAME
        buttonValidateByComponentName = findViewById(R.id.button_validate_by_component_name);
        if (buttonValidateByComponentName != null) {
            buttonValidateByComponentName.setOnClickListener(componentNameListener());
        }

        //  IMPLICIT LISTENER
        buttonValidateImplicitIntent = findViewById(R.id.button_validate_by_implicit_intent);
        if (buttonValidateImplicitIntent != null) {
            buttonValidateImplicitIntent.setOnClickListener(implicitIntentListener());
        }
    }

    /**
     * Listener to handle byClass intent.
     * @return
     */
    private View.OnClickListener byClassListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                Intent loginIntent = new Intent();
                if (checkValues()) {
                    setResult(5, loginIntent);
                } else {
                    setResult(66, loginIntent);
                }
                finish();
            }
        };
    }

    private View.OnClickListener componentNameListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                if (checkValues()) {
                    Intent loginIntent = new Intent();
                    loginIntent.setComponent(new ComponentName(PACKAGE_PATH, PACKAGE_PATH + ".MessagingActivity"));
                    startActivity(loginIntent);
                }
            }
        };
    }

    private View.OnClickListener implicitIntentListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                if (checkValues()) {
                    Intent loginIntent = new Intent();
                    loginIntent.setAction("messaging.ACTION");
                    PackageManager pm = getPackageManager();
                    List<ResolveInfo> l = pm.queryIntentActivities(loginIntent, 0);
                    if (l.size() > 0) {
                        startActivity(loginIntent);
                    }
                }
            }
        };
    }

    private View.OnClickListener byClassNameListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                if (checkValues()) {
                    Intent loginIntent = new Intent();
                    loginIntent.setClassName(getApplicationContext(), PACKAGE_PATH + ".MessagingActivity");
                    startActivity(loginIntent);
                }
            }
        };
    }

    private boolean checkValues() {
        getValues();
        if ((typedPassword.equals("0000")) && (typedLogin.equals("pierre"))) {
            return true;
        }
        return false;
    }

    private void getValues() {
        if (editTextLogin != null) {
            typedLogin = editTextLogin.getText().toString();
        }

        if (editTextPassword != null) {
            typedPassword = editTextPassword.getText().toString();
        }
    }

}
