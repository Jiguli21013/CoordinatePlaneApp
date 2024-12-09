package com.yanchelenko.coordinateplaneapp.presentation

import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.GraphModelUI

sealed class UIState {
    data object None : UIState()
    data object Loading : UIState()
    class Error(val messageError: String) : UIState()
    class Success(val graphUiModel: GraphModelUI) : UIState()
}
