package gkk.app.sputnik.ui.screen.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ExLiveDataViewModel @Inject constructor(): ViewModel() {
    private val _id = MutableStateFlow("")
    val id: StateFlow<String> = _id

    private val _pw = MutableStateFlow("")
    val pw: StateFlow<String> = _pw

    val enabledLoginButtonByCombine: StateFlow<Boolean> = combine(id, pw) { i, p ->
        if (i != "" && p != "") {
            true
        } else {
            false
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false,
    )

    val enabledLoginButtonByCombineTransform: StateFlow<Boolean>
        = combineTransform(id, pw) { i, p ->
            if (i != "" && p != "") {
                emit(true)
            } else {
                emit(false)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false,
        )


    fun setId(id: String) {
        _id.value = id
    }

    fun setPw(pw: String) {
        _pw.value = pw
    }

}