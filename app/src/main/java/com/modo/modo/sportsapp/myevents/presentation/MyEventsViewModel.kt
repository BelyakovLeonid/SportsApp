package com.modo.modo.sportsapp.myevents.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.myevents.data.MyEventsRepository
import com.modo.modo.sportsapp.myevents.presentation.model.EventUiModel
import com.modo.modo.sportsapp.myevents.presentation.model.toUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyEventsViewModel(
    private val repository: MyEventsRepository
) : ViewModel() {

    private val _content = MutableStateFlow<List<EventUiModel>?>(null)
    val content: Flow<List<EventUiModel>> = _content.filterNotNull()

    init {
        viewModelScope.launch(CoroutineExceptionHandler(::onError)) {
            val events = repository.loadEvents()
            _content.value = events.mapIndexed { i, e -> e.toUi(i) }
        }
    }

    private fun onError(context: CoroutineContext, t: Throwable) {
        Log.d("MyTag", "MyEventsViewModel error = $t")
        _content.value = emptyList()
    }
}