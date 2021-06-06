package com.modo.modo.sportsapp.base.events.onlyevents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.base.events.myevents.data.EventsRepository
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.toUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EventsViewModel(
    private val repository: EventsRepository
) : ViewModel() {

    private val _content = MutableStateFlow<List<EventItem.EventUiModel>?>(null)
    val content: Flow<List<EventItem.EventUiModel>> = _content.filterNotNull()

    init {
        loadEvents()
        subscribeToEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch(CoroutineExceptionHandler(::onError)) {
            repository.loadEvents()
        }
    }

    private fun subscribeToEvents() {
        repository.getEventsAsFlow()
            .onEach { events ->
                val uiEvents = events.mapIndexed { i, e -> e.toUi(i) }
                if (!uiEvents.isNullOrEmpty()) {
                    _content.value = uiEvents
                }
            }.launchIn(viewModelScope)
    }

    private fun onError(context: CoroutineContext, t: Throwable) {
        _content.value = emptyList()
    }
}