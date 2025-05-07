package gkk.app.sputnik.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reducer: HomeReducer,
): ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun handleIntent(intent: HomeIntent) {
        val newState = reducer.reduce(_uiState.value, intent)
        _uiState.value = newState
    }

    fun resetNavigation() {
        _uiState.value = _uiState.value.copy(navigateTo = null)
    }
}