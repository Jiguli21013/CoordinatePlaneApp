package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.interfaces

import android.graphics.Canvas
import android.graphics.Paint

interface ILabelDrawer {
    fun drawLabel(canvas: Canvas, x: Float, y: Float, text: String, paint: Paint)
}
