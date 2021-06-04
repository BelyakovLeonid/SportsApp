package com.modo.modo.sportsapp.root

import android.os.Bundle
import android.view.View
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

    private fun setupBottomNavigation() {
        childFragmentManager.findFragmentById(R.id.contentContainer)?.findNavController()?.let {
            binding.bottomNav.setupWithNavController(it)
            binding.bottomNav.setOnNavigationItemReselectedListener {
                // Do nothing to ignore the reselection
            }
        }
    }
}