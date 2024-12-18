package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.interfaces

import android.graphics.Canvas
import android.graphics.Paint

interface IGridDrawer {
    fun drawGridLine(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, paint: Paint)
}
