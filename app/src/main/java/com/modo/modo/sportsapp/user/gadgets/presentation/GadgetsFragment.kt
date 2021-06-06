package com.modo.modo.sportsapp.user.gadgets.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentGadgetsBinding

class GadgetsFragment : Fragment(R.layout.fragment_gadgets) {

    private val binding by viewBinding(FragmentGadgetsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNext.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.activityContent)
            val mainGraph = navController.navInflater.inflate(R.navigation.main_graph)
            mainGraph.startDestination = R.id.tabsFragment
            navController.graph = mainGraph
        }
    }
}