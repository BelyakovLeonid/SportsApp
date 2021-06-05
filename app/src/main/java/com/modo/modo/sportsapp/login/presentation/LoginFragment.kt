package com.modo.modo.sportsapp.login.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeData()
    }

    private fun handleView() = with(binding) {
        nextButton.setOnClickListener {
            viewModel.onLoginClick(
                login = idInputText.text.toString(),
                password = passwordInputText.text.toString()
            )
        }
    }

    private fun observeData() {
        observeFlow(viewModel.loginState) { loginSuccess ->
            if (!loginSuccess) showError()
        }
        observeFlow(viewModel.navigationCommands) { destinationId ->
            val navController = Navigation.findNavController(requireActivity(), R.id.activityContent)
            val mainGraph = navController.navInflater.inflate(R.navigation.main_graph)

            // Way to change first screen at runtime.
            mainGraph.startDestination = destinationId
            navController.graph = mainGraph
        }
    }

    private fun showError() = with(binding) {
        idInput.error = resources.getString(R.string.text_error)
        passwordInput.error = resources.getString(R.string.text_error)
        passwordInputText.setText("")
    }
}