package com.sunday.taskapp.util

sealed class CrossEvent {
    object NavigateBack: CrossEvent()
    data class NavigateTo(val route: String): CrossEvent()
    data class ShowSnackbar(val message: String, val action: String? = null): CrossEvent()
}