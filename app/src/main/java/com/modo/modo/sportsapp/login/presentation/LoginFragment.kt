package com.modo.modo.sportsapp.login.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.LocalStorage
import com.modo.modo.sportsapp.databinding.FragmentLoginBinding
import com.modo.modo.sportsapp.login.constants.LoginFragmentConstants.*
import com.modo.modo.sportsapp.login.domain.AuthResponse

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: ViewModel
    private lateinit var storage: LocalStorage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeData()
        checkAuth()
    }

    private fun handleView() = with(binding) {
        viewModel = ViewModelProviders.of(requireActivity()).get(ViewModel::class.java)
        storage = LocalStorage(requireActivity());

        linkText.movementMethod = LinkMovementMethod.getInstance()
        nextButton.setOnClickListener {
            viewModel.doLogin(
                idInputText.text.toString(),
                passwordInputText.text.toString()
            )
        }
    }

    private fun observeData() {
        viewModel.bindAuth().observe(viewLifecycleOwner) { authDataWrapper ->
            if (authDataWrapper.isError) {
                showError()
            } else {
                storage.putData(AUTH_DATA, authDataWrapper.dataObject)
                openTabScreen()
            }
        }
    }


    private fun checkAuth() {
        val authData = storage.getData(AUTH_DATA, AuthResponse::class.java)
        if (authData?.token != null) {
            openTabScreen()
        }
    }

    private fun openTabScreen() {
        findNavController().navigate(R.id.tabsFragment)
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