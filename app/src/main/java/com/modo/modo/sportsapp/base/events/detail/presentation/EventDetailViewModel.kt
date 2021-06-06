package com.modo.modo.sportsapp.base.events.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.base.events.myevents.data.EventsRepository
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.toUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val repository: EventsRepository,
    private val eventId: String
) : ViewModel() {

    private val _content = MutableStateFlow<EventItem.EventUiModel?>(null)
    val content: Flow<EventItem.EventUiModel> = _content.filterNotNull()

    init {
        _content.value = repository.getEvent(eventId)?.toUi()
    }

    fun changeStatus(status: ParticipantStatus) {
        viewModelScope.launch(CoroutineExceptionHandler { _, t -> Log.d("MyTag", "t: $t") }) {
            repository.setParticipateInEvent(
                eventId,
                status
            )
            _content.value = repository.getEvent(eventId)?.toUi()
        }
    }
}