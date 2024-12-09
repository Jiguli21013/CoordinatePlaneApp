package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.listeners

import android.view.GestureDetector
import android.view.MotionEvent
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.GraphStateController

class GestureListener(
    private val stateController: GraphStateController
) : GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent): Boolean {
        stateController.resetGraph()
        return true
    }

    override fun onDown(event: MotionEvent): Boolean = true

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        val sensitivity = 0.8f / stateController.scaleFactor.coerceAtLeast(0.1f)

        // Обновляем смещение
        stateController.offsetX -= distanceX * sensitivity
        stateController.offsetY -= distanceY * sensitivity

        // Ограничения на смещения
        stateController.constrainOffsets()

        return true
    }
}
