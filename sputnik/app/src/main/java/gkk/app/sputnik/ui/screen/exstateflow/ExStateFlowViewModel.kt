package gkk.app.sputnik.ui.screen.exstateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExStateFlowViewModel @Inject constructor(): ViewModel() {
    private val _id = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    private val _uiState = MutableStateFlow(ExStateFlowUiState())
    val uiState: StateFlow<ExStateFlowUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(_id, _password) { i, p ->
                (i != "" && p != "")
            }.collect { enabled ->
                _uiState.update { current ->
                    current.copy(enabledBtnByCombine = enabled)
                }
            }
        }
    }

    private fun reduce(intent: ExStateFlowIntent) {
        when (intent) {
            is ExStateFlowIntent.SetId -> {
                _id.value = intent.id
                _uiState.update { it.copy(id = intent.id) }
            }
            is ExStateFlowIntent.SetPassword -> {
                _password.value = intent.password
                _uiState.update { it.copy(pw = intent.password) }
            }
        }
    }

    fun handleIntent(intent: ExStateFlowIntent) {
        viewModelScope.launch {
            reduce(intent)
        }
    }

}