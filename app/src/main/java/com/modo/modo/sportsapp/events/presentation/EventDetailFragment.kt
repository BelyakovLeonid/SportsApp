package com.modo.modo.sportsapp.events.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentMyEventDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventDetailFragment : Fragment(R.layout.fragment_my_event_detail) {

    private val binding by viewBinding(FragmentMyEventDetailBinding::bind)

    private val viewModel by viewModel<EventDetailViewModel> {
        parametersOf(arguments?.getParcelable(EVENT_ID_EXTRA))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() = with(binding) {
        observeFlow(viewModel.content) { model ->
            checkIn.isVisible = model.isOpen
            name.text = model.name
            date.text = model.date
            description.text = model.name // todo need description
        }
    }

    companion object {
        private const val EVENT_ID_EXTRA = "event_id_exrtra"
    }
}