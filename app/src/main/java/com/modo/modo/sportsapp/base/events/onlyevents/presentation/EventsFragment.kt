package com.modo.modo.sportsapp.base.events.onlyevents.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.events.allevents.presentation.adapter.OffsetItemsDecorator
import com.modo.modo.sportsapp.base.events.detail.presentation.EventDetailFragment
import com.modo.modo.sportsapp.base.events.myevents.presentation.adapter.EventsAdapter
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.databinding.FragmentAdminHomeBinding
import com.modo.modo.sportsapp.user.base.utils.observeFlow
import org.koin.android.viewmodel.ext.android.viewModel

class EventsFragment : Fragment(R.layout.fragment_admin_home) {

    private val viewModel by viewModel<EventsViewModel>()

    private val binding by viewBinding(FragmentAdminHomeBinding::bind)

    private val eventsAdapter by lazy {
        EventsAdapter(
            onItemClick = ::openEventDetail
        )
    }

    private val decorator by lazy {
        OffsetItemsDecorator(
            offset = resources.getDimensionPixelOffset(R.dimen.decorator_offset)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeData()
    }

    private fun observeData() {
        observeFlow(viewModel.content) {
            eventsAdapter.submitList(it)
        }
    }

    private fun setupView() = with(binding) {
        eventsList.adapter = eventsAdapter
        eventsList.addItemDecoration(decorator)
    }

    private fun openEventDetail(event: EventItem.EventUiModel) {
        Navigation.findNavController(requireActivity(), R.id.activityContent)
            .navigate(
                R.id.eventDetailFragment,
                bundleOf(EventDetailFragment.EVENT_ID_EXTRA to event.id)
            )
    }
}