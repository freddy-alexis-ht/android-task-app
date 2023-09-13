package com.sunday.taskapp.util

sealed class CrossEvent {
    object PopBackStack: CrossEvent()
    data class Navigate(val route: String): CrossEvent()
    data class ShowSnackbar(val message: String, val action: String? = null): CrossEvent()
}