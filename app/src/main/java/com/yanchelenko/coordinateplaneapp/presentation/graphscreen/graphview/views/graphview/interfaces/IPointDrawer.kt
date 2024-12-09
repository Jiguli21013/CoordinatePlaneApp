package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces

import android.graphics.Canvas
import android.graphics.Paint

interface IPointDrawer {
    fun drawPoint(canvas: Canvas, x: Float, y: Float, pointPaint: Paint)
}
