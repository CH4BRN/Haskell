package com.uldskull.rolegame

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityUtil {


    /**
     *  This is the Extension function to the FragmentManager which accepts
     *  a Lambda with Receiver as its argument, whereas the FragmentTransaction
     *  is the Receiver.
     *  the parameter func is an Extension function to the FragmentTransaction
     *  it has zero parameters and returns Unit.
     *  We invoke that function after calling the beginTransaction()
     *  and end the transaction by calling commit().
     */
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }



    companion object {
        fun addFragmentToActivity(manager:FragmentManager, fragment:Fragment, frameId:Int){

            var transaction = manager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}