package com.uldskull.role_1.ui.welcome

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uldskull.role_1.R
import com.uldskull.role_1.ui.LoadGame.LoadGameFragment
import com.uldskull.role_1.ui.newGame.NewGameFragment
import kotlinx.android.synthetic.main.welcome_fragment.*


class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()

    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel

        button_new_game?.setOnClickListener {
            loadFragment(NewGameFragment.newInstance())
        }

        button_load_game?.setOnClickListener {
            loadFragment(LoadGameFragment.newInstance())
        }
    }
    private fun loadFragment(fragment : Fragment){
        val supportFragmentManager = fragmentManager
        val fragmentTransaction = supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.commit()
    }

}
