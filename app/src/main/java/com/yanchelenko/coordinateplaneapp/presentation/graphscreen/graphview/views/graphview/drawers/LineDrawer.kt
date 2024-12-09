package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces.ILineDrawer

class LineDrawer : ILineDrawer {
    override fun drawLine(canvas: Canvas, path: Path, linePaint: Paint) {
        canvas.drawPath(path, linePaint)
    }
}
