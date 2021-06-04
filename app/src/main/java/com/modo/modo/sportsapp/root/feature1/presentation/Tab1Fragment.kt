package com.modo.modo.sportsapp.root.feature1.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment(R.layout.fragment_tab1) {

    private val binding by viewBinding(FragmentTab1Binding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            requireActivity().findNavController(R.id.activityContent).navigate(R.id.cameraFragment)
        }
    }
}