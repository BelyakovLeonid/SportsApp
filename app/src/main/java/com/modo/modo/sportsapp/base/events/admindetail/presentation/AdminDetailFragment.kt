package com.modo.modo.sportsapp.base.events.admindetail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.user.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentMyEventDetailBinding
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import com.modo.modo.sportsapp.databinding.FragmentAdminDetailBinding
import com.modo.modo.sportsapp.user.qr.presentation.QrFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AdminDetailFragment : Fragment(R.layout.fragment_admin_detail) {

    private val binding by viewBinding(FragmentAdminDetailBinding::bind)

    private val viewModel by viewModel<AdminDetailViewModel> {
        parametersOf(arguments?.getString(EVENT_ID_EXTRA))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeData()
    }

    private fun setupView() = with(binding) {
        checkIn.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.activityContent)
                .navigate(
                    R.id.qrFragment,
                    bundleOf(QrFragment.EVENT_ID_EXTRA to arguments?.getString(EVENT_ID_EXTRA))
                )
        }
    }

    private fun observeData() = with(binding) {
        observeFlow(viewModel.content) { model ->
            image.load(model.imageUrl)
            name.text = model.name
            date.text = model.date
            description.text = model.description
            place.text = model.address
        }
    }

    companion object {
        const val EVENT_ID_EXTRA = "event_id_exrtra"
    }
}