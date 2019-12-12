package com.uldskull.tp_intent_v2.not_logged;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uldskull.tp_intent_v2.FragmentHandler;
import com.uldskull.tp_intent_v2.R;
import com.uldskull.tp_intent_v2.login.LoginFragment;

public class NotLoggedFragment extends Fragment {

    private NotLoggedViewModel mViewModel;

    public static NotLoggedFragment newInstance() {
        return new NotLoggedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.not_logged_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotLoggedViewModel.class);
        Button buttonAccessLogin = getView().findViewById(R.id.button_login);


        buttonAccessLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSetLoginView();
            }
        });

        // TODO: Use the ViewModel
    }
    private void doSetLoginView(){
        // Intent loginIntent = new Intent(this, LoginActivity.class);
        Fragment newFragment = new LoginFragment();
        FragmentHandler.doLoadFragment(newFragment, getActivity().getSupportFragmentManager());

    }


}
