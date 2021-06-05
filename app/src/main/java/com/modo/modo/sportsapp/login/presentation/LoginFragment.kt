package com.modo.modo.sportsapp.login.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.LocalStorage
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentLoginBinding
import com.modo.modo.sportsapp.login.constants.LoginFragmentConstants.AUTH_DATA
import com.modo.modo.sportsapp.login.domain.AuthResponse

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: LoginViewModel
    private lateinit var storage: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeData()
        checkAuth()
    }

    private fun handleView() = with(binding) {
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)
        storage = LocalStorage(requireActivity())

        linkText.movementMethod = LinkMovementMethod.getInstance()
        nextButton.setOnClickListener {
            viewModel.doLogin(
                idInputText.text.toString(),
                passwordInputText.text.toString()
            )
        }
    }

    private fun observeData() {
        viewModel.bindAuth()?.observe(viewLifecycleOwner) { authDataWrapper ->
            if (authDataWrapper != null) {
                if (authDataWrapper.isError) {
                    showError()
                } else {
                    storage.putData(AUTH_DATA, authDataWrapper.dataObject)
                    openInterestsScreen()
                }
            }
        }
    }


    private fun checkAuth() {
        val authData = storage.getData(AUTH_DATA, AuthResponse::class.java)
        if (authData?.token != null) {
            // todo чекать если интересы были уже заполнены
            openTabScreen()
        }
    }

    private fun openInterestsScreen() {
        findNavController().navigate(R.id.interestsFragment)
    }

    private fun openTabScreen() {
        findNavController().navigate(R.id.tabsFragment)
    }

    private fun showError() = with(binding) {
        idInput.error = resources.getString(R.string.text_error)
        passwordInput.error = resources.getString(R.string.text_error)
        passwordInputText.setText("")
    }
}