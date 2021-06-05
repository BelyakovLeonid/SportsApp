package com.modo.modo.sportsapp.events.presentation

import androidx.lifecycle.ViewModel
import com.modo.modo.sportsapp.myevents.data.EventsRepository
import com.modo.modo.sportsapp.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.myevents.presentation.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class EventDetailViewModel(
    repository: EventsRepository,
    eventId: String
) : ViewModel() {

    private val _content = MutableStateFlow<EventItem.EventUiModel?>(null)
    val content: Flow<EventItem.EventUiModel> = _content.filterNotNull()

    init {
        _content.value = repository.getEvent(eventId)?.toUi()
    }
}