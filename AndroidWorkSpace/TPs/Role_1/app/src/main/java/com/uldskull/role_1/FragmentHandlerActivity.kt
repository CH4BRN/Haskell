package com.uldskull.role_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.uldskull.role_1.ui.welcome.WelcomeFragment

class FragmentHandlerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_handler)

        loadFragment(WelcomeFragment.newInstance())

    }


    private fun loadFragment(fragment : Fragment){
        val supportFragmentManager =supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, "WelcomeFragment")
        fragmentTransaction.addToBackStack("WelcomeFragment")
        fragmentTransaction.commit()
    }

}
