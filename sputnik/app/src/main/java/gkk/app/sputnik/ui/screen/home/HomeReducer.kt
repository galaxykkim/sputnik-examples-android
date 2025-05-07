package gkk.app.sputnik.ui.screen.home

import gkk.app.sputnik.common.Screen
import javax.inject.Inject

class HomeReducer @Inject constructor() {
    fun reduce(currentState: HomeUiState, intent: HomeIntent): HomeUiState {
        return when (intent) {
            is HomeIntent.NavigateToExampleLiveData
                -> currentState.copy(navigateTo = Screen.EXAMPLE_LIVEDATA)

        }
    }
}