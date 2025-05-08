package gkk.app.sputnik.ui.screen.livedata

sealed class ExLiveDataIntent {
    object ValidId: ExLiveDataIntent()
    object ValidPw: ExLiveDataIntent()
}