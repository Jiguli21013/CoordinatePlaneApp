package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.listeners

import android.view.ScaleGestureDetector
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.GraphStateController

class ScaleListener(private val stateController: GraphStateController) : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        // Вычисление нового масштаба
        val newScaleFactor = (stateController.scaleFactor * detector.scaleFactor).coerceIn(0.5f, 3f)
        val scaleDelta = newScaleFactor / stateController.scaleFactor

        // Обновление масштаба
        stateController.scaleFactor = newScaleFactor

        // Точка фокуса
        val focusX = detector.focusX
        val focusY = detector.focusY

        // Обновление смещения относительно точки фокуса
        stateController.updateScale(focusX, focusY, scaleDelta)

        // Обновление представления
        stateController.invalidateView()

        return true
    }
}
