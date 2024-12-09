package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces

interface GraphStateController {
    var offsetX: Float
    var offsetY: Float
    var scaleFactor: Float

    val cellSize: Float

    val graphWidth: Float
    val graphHeight: Float

    fun resetGraph()
    fun constrainOffsets()
    fun updateScale(focusX: Float, focusY: Float, scaleDelta: Float)
    fun invalidateView()
}
