package com.modo.modo.sportsapp.interests.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.databinding.FragmentInterestsBinding

class InterestsFragment : Fragment(R.layout.fragment_interests) {

    private val binding by viewBinding(FragmentInterestsBinding::bind)

    private val viewModel by viewModels<InterestsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        buttonNext.setOnClickListener {
            viewModel.onInterestsSelected(
                sportsmanChips.getSelectedChipsStrings(),
                fanChips.getSelectedChipsStrings()
            )
            findNavController().navigate(R.id.gadgetsFragment)
        }
    }

    private fun ChipGroup.getSelectedChipsStrings(): List<String> {
        return checkedChipIds.mapNotNull { id ->
            (children.find { it.id == id } as? Chip)?.text?.toString()
        }
    }
}
