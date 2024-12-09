package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

interface ILineDrawer {
    fun drawLine(canvas: Canvas, path: Path, linePaint: Paint)
}
