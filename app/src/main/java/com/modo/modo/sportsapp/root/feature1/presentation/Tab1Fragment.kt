package com.modo.modo.sportsapp.root.feature1.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private lateinit var viewModel: ViewModel;

    private val binding by viewBinding(FragmentTab1Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            requireActivity().findNavController(R.id.activityContent).navigate(R.id.cameraFragment)
        }
        setupView()
        bindView()
    }

    private fun setupView() {
        viewModel = ViewModelProviders.of(getActivityNonNull()).get(ViewModel::class.java)
    }

    private fun bindView() {
        viewModel.bindPing().observe(viewLifecycleOwner) { userDataWrapper ->

            // todo do something if success or not
        }
    }

    private fun getActivityNonNull(): FragmentActivity {
        return if (super.getActivity() != null) {
            super.requireActivity()
        } else {
            throw RuntimeException("null returned from getActivity()")
        }
    }
}