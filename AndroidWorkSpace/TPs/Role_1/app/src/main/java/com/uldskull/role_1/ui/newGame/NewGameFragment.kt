package com.uldskull.role_1.ui.newGame

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.uldskull.role_1.R
import com.uldskull.role_1.ui.welcome.WelcomeFragment
import kotlinx.android.synthetic.main.new_game_fragment.*

class NewGameFragment : Fragment() {

    companion object {
        fun newInstance() = NewGameFragment()
    }

    private lateinit var viewModel: NewGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewGameViewModel::class.java)
        // TODO: Use the ViewModel
        val personNames = arrayOf("Basic Role Playing")

        if(spinner_game_type!=null){

            val arrayAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, personNames)
            spinner_game_type.adapter = arrayAdapter
        }
        val i = 0
        if(button_back!=null){
            val fm = fragmentManager
            button_back.setOnClickListener(View.OnClickListener {
                val fm = fragmentManager
                val fragmentTransaction = fm?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, WelcomeFragment.newInstance())
                fragmentTransaction?.addToBackStack("WelcomeFragment")
                fm?.popBackStack()
            })




        }

    }



}

