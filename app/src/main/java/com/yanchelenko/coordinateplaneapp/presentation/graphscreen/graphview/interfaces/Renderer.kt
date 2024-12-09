package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces

import android.graphics.Canvas
import android.graphics.Paint
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.PointUI

interface Renderer {
    fun draw(
        canvas: Canvas,
        graphState: GraphStateController,
        cellSize: Float,
        points: List<PointUI>,
        gridPaint: Paint,
        axisPaint: Paint,
        textPaint: Paint,
        pointPaint: Paint,
        linePaint: Paint
    )
}
