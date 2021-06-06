package com.modo.modo.sportsapp.user.feature5.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentTab5Binding
import org.koin.android.viewmodel.ext.android.viewModel

class Tab5Fragment : Fragment(R.layout.fragment_tab5) {

    private val binding by viewBinding(FragmentTab5Binding::bind)

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonExit.setOnClickListener {
            viewModel.logout()
            requireActivity().finish()
        }
    }
}