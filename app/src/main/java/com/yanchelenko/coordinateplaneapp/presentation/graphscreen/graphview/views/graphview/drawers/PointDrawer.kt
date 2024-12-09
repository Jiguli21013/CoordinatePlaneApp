package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces.IPointDrawer

class PointDrawer : IPointDrawer {
    override fun drawPoint(canvas: Canvas, x: Float, y: Float, pointPaint: Paint) {
        canvas.drawCircle(x, y, 5f, pointPaint)
    }
}
