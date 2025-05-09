package gkk.app.sputnik.ui.screen.exstateflow

sealed class ExStateFlowIntent {
    data class SetId(val id: String): ExStateFlowIntent()
    data class SetPassword(val password: String): ExStateFlowIntent()
}