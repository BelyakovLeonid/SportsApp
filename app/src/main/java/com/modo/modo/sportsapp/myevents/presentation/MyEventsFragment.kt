package com.modo.modo.sportsapp.myevents.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentMyEventsBinding

class MyEventsFragment : Fragment(R.layout.fragment_my_events) {

    private val viewModel by viewModels<MyEventsViewModel>()

    private val binding by viewBinding(FragmentMyEventsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {
        viewModel.bindPing().observe(viewLifecycleOwner) { userDataWrapper ->

        }
    }
}