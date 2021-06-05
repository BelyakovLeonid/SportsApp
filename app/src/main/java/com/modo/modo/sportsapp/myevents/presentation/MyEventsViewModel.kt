package com.modo.modo.sportsapp.myevents.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.myevents.data.EventsRepository
import com.modo.modo.sportsapp.myevents.presentation.model.EventItem
import com.modo.modo.sportsapp.myevents.presentation.model.toUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyEventsViewModel(
    private val repository: EventsRepository
) : ViewModel() {

    private val _content = MutableStateFlow<List<EventItem>?>(null)
    val content: Flow<List<EventItem>> = _content.filterNotNull()

    init {
        viewModelScope.launch(CoroutineExceptionHandler(::onError)) {
            val events = repository.loadEvents().mapIndexed { i, e -> e.toUi(i) }
            if (events.isNullOrEmpty()) {
                _content.value = events
            } else {
                _content.value = listOf(EventItem.EmptyEventUiModel(true), EventItem.EmptyEventUiModel(false))
            }
        }
    }

    private fun onError(context: CoroutineContext, t: Throwable) {
        _content.value = listOf(EventItem.EmptyEventUiModel(true), EventItem.EmptyEventUiModel(false))
        //        _content.value = emptyList()
    }
}