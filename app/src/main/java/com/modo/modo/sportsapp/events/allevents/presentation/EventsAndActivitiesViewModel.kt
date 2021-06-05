package com.modo.modo.sportsapp.events.allevents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.events.myevents.data.EventsRepository
import com.modo.modo.sportsapp.events.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.events.myevents.presentation.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EventsAndActivitiesViewModel(
    repository: EventsRepository
) : ViewModel() {

    private val _contentEvents = MutableStateFlow<List<EventItem.EventUiModel>?>(null)
    val contentEvents = _contentEvents.filterNotNull()

    init {
        repository.getEventsAsFlow()
            .onEach { list ->
                _contentEvents.value = list.mapIndexed { index, event -> event.toUi(index) }
            }
            .launchIn(viewModelScope)
    }
}