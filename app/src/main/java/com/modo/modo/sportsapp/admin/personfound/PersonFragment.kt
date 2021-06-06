package com.modo.modo.sportsapp.admin.personfound

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.admin.camera.presentation.model.PersonUiModel
import com.modo.modo.sportsapp.databinding.FragmentPersonBinding

class PersonFragment : Fragment(R.layout.fragment_person) {

    private val binding by viewBinding(FragmentPersonBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = arguments?.getParcelable<PersonUiModel>(PERSON_EXTRA)
        if (person != null) {
            binding.fio.text = person.name
            binding.image.load(person.imageUrl)
        } else {
            closeFragment()
        }

        binding.back.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.activityContent)
                .navigateUp()
        }
    }

    private fun closeFragment() {
        Navigation.findNavController(requireActivity(), R.id.activityContent)
            .navigateUp()
    }

    companion object {
        const val PERSON_EXTRA = "person"
    }
}