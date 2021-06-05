package com.modo.modo.sportsapp.events.allevents.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentEventsWithActivitiesBinding
import com.modo.modo.sportsapp.events.allevents.presentation.adapter.OffsetItemsDecorator
import com.modo.modo.sportsapp.events.detail.presentation.EventDetailFragment
import com.modo.modo.sportsapp.events.myevents.presentation.adapter.EventsAdapter
import com.modo.modo.sportsapp.events.myevents.presentation.model.EventItem
import org.koin.android.viewmodel.ext.android.viewModel

class EventsAndActivitiesFragment : Fragment(R.layout.fragment_events_with_activities) {

    private val binding by viewBinding(FragmentEventsWithActivitiesBinding::bind)

    private val viewModel by viewModel<EventsAndActivitiesViewModel>()

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

    private fun setupView() = with(binding) {
        eventsList.adapter = eventsAdapter
        eventsList.addItemDecoration(decorator)
    }

    private fun observeData() {
        observeFlow(viewModel.contentEvents) {
            eventsAdapter.submitList(it)
        }
    }

    private fun openEventDetail(event: EventItem.EventUiModel) {
        Navigation.findNavController(requireActivity(), R.id.activityContent)
            .navigate(
                R.id.eventDetailFragment,
                bundleOf(EventDetailFragment.EVENT_ID_EXTRA to event.id)
            )
    }
}