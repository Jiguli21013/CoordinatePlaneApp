package com.yanchelenko.coordinateplaneapp.presentation.graphscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanchelenko.coordinateplaneapp.domain.usecases.GetPointsUseCase
import com.yanchelenko.coordinateplaneapp.presentation.UIState
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.toGraphModelUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val getPointsUseCase: GetPointsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _graphState = MutableStateFlow<UIState>(UIState.None)
    val graphState = _graphState.asStateFlow()

    init {
        getPointsRequest()
    }

    fun getPointsRequest() =
        viewModelScope.launch(Dispatchers.IO) {
            _graphState.emit(UIState.Loading)
            getPointsUseCase.execute(
                numberOfPoints = savedStateHandle[GraphFragment.NUMBER_OF_POINTS] ?: return@launch
            ).map { listOfPointEntities ->
                listOfPointEntities.toGraphModelUI()
            }.onSuccess { graphModel ->
                _graphState.emit(UIState.Success(graphModel))
            }.onFailure { throwable ->
                _graphState.emit(UIState.Error(messageError = throwable.cause?.message + throwable.message))
            }

        }
}
