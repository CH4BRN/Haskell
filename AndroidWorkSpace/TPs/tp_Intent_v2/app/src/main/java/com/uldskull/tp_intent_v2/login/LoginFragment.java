package com.uldskull.tp_intent_v2.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.uldskull.tp_intent_v2.R;

import java.util.List;

public class LoginFragment extends Fragment {

    //  Buttons
    Button buttonValidateByClass;
    Button buttonValidateByClassName;
    Button buttonValidateByComponentName;
    Button buttonValidateImplicitIntent;
    Button buttonCancel;

    //  Edit-Text
    EditText editTextLogin;
    EditText editTextPassword;


    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.login_fragment, container, false);
    }


    private void getValues() {
        if (editTextLogin != null) {
            mViewModel.setTypedLogin(editTextLogin.getText().toString());
        }

        if (editTextPassword != null) {
            mViewModel.setTypedPassword(editTextPassword.getText().toString());
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // Get the edit text from the view and set the default login text.
        editTextLogin = (EditText)getActivity().getView().findViewById(R.id.editText_login);
        if (editTextLogin != null) {
            editTextLogin.setText(mViewModel.defaultLogin);
        }

        // Same operation than above.
        editTextPassword = (EditText) getView().findViewById(R.id.editText_password);
        if (editTextPassword != null) {
            editTextPassword.setText(mViewModel.defaultPassword);
        }

        // Cancel button
        buttonCancel = getView().findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent();
                getActivity().setResult(666, loginIntent);
                getActivity().finish();
            }
        });

    }
    private final String PACKAGE_PATH = "com.uldskull.tp_intent_v2";

    private View.OnClickListener implicitIntentListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                if (mViewModel.checkValues()) {
                    Intent loginIntent = new Intent();
                    loginIntent.setAction("messaging.ACTION");
                    PackageManager pm = getActivity().getPackageManager();
                    List<ResolveInfo> l = pm.queryIntentActivities(loginIntent, 0);
                    if (l.size() > 0) {
                        startActivity(loginIntent);
                    }
                }
            }
        };
    }


}
