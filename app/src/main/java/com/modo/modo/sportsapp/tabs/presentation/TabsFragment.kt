package com.modo.modo.sportsapp.tabs.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val binding by viewBinding(FragmentTabsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigation()
        }
    }

    private fun setupBottomNavigation() = with(binding) {
        childFragmentManager.findFragmentById(R.id.contentContainer)?.findNavController()?.let {
            bottomNav.setupWithNavController(it)
            bottomNav.setOnNavigationItemReselectedListener {
                // Do nothing to ignore the reselection
            }
        }
    }
}