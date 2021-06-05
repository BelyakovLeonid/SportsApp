package com.modo.modo.sportsapp.events.presentation

import androidx.lifecycle.ViewModel
import com.modo.modo.sportsapp.myevents.data.MyEventsRepository
import com.modo.modo.sportsapp.myevents.presentation.model.EventUiModel
import com.modo.modo.sportsapp.myevents.presentation.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class EventDetailViewModel(
    private val repository: MyEventsRepository,
    private val eventId: String
) : ViewModel() {

    private val _content = MutableStateFlow<EventUiModel?>(null)
    val content: Flow<EventUiModel> = _content.filterNotNull()

    init {
        _content.value = repository.getEvent(eventId)?.toUi()
    }
}