package com.uldskull.tp_intent_v2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHandler {
    public static void doLoadFragment(Fragment fragmentToLoad, FragmentManager fragmentManager){
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragmentToLoad);
        transaction.commit();
    }
}
