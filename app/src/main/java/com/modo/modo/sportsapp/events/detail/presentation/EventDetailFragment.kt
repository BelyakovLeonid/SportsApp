package com.modo.modo.sportsapp.events.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentMyEventDetailBinding
import com.modo.modo.sportsapp.events.myevents.presentation.model.ParticipantStatus
import com.modo.modo.sportsapp.qr.presentation.QrFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventDetailFragment : Fragment(R.layout.fragment_my_event_detail) {

    private val binding by viewBinding(FragmentMyEventDetailBinding::bind)

    private val viewModel by viewModel<EventDetailViewModel> {
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
            checkIn.isVisible = model.isOpen && model.userStatus != ParticipantStatus.NONE
            name.text = model.name
            date.text = model.date
            description.text = model.description
            place.text = model.address
            regStatus.isVisible = model.userStatus != ParticipantStatus.NONE
            val labelStrId = if (model.userStatus == ParticipantStatus.FAN) {
                R.string.detail_you_are_fun
            } else {
                R.string.detail_you_are_sport
            }
            regStatus.setText(labelStrId)
            val buttonStrId = if (model.userStatus == ParticipantStatus.NONE) {
                R.string.detail_register
            } else {
                R.string.detail_you_are_cancel
            }
            buttonConnect.setText(buttonStrId)
            image.load(model.imageUrl)
        }
    }

    companion object {
        const val EVENT_ID_EXTRA = "event_id_exrtra"
    }
}