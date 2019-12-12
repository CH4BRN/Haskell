package com.uldskull.role_1.ui.LoadGame

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.uldskull.role_1.R

class LoadGameFragment : Fragment() {

    companion object {
        fun newInstance() = LoadGameFragment()
    }

    private lateinit var viewModel: LoadGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.load_game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoadGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
