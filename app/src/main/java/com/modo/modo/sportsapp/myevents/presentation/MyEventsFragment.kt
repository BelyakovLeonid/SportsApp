package com.modo.modo.sportsapp.myevents.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.utils.observeFlow
import com.modo.modo.sportsapp.databinding.FragmentMyEventsBinding
import com.modo.modo.sportsapp.events.presentation.EventDetailFragment
import com.modo.modo.sportsapp.myevents.presentation.adapter.EventsAdapter
import com.modo.modo.sportsapp.myevents.presentation.adapter.EventsItemsDecorator
import com.modo.modo.sportsapp.myevents.presentation.model.EventItem
import org.koin.android.viewmodel.ext.android.viewModel

class MyEventsFragment : Fragment(R.layout.fragment_my_events) {

    private val viewModel by viewModel<MyEventsViewModel>()

    private val binding by viewBinding(FragmentMyEventsBinding::bind)

    private val eventsAdapter by lazy {
        EventsAdapter(
            onItemClick = ::openEventDetail
        )
    }

    private val decorator by lazy {
        EventsItemsDecorator(
            resources.getDimensionPixelOffset(R.dimen.decorator_offset),
            resources.getDimensionPixelOffset(R.dimen.decorator_text_size),
            eventsAdapter
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