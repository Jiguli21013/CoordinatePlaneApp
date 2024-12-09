package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.GraphStateController
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.Renderer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers.LineDrawer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers.PointDrawer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers.TextDrawer
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.PointUI

class GraphRenderer : Renderer {

    private val pointDrawer = PointDrawer()
    private val textDrawer = TextDrawer()
    private val lineDrawer = LineDrawer()

    override fun draw(
        canvas: Canvas,
        graphState: GraphStateController,
        cellSize: Float,
        points: List<PointUI>,
        gridPaint: Paint,
        axisPaint: Paint,
        textPaint: Paint,
        pointPaint: Paint,
        linePaint: Paint
    ) {
        val centerX = canvas.width / 2f
        val centerY = canvas.height / 2f
        val path = Path()

        for ((index, point) in points.withIndex()) {
            val x = centerX + (point.x * cellSize) + graphState.offsetX
            val y = centerY - (point.y * cellSize) + graphState.offsetY

            // Точки
            pointDrawer.drawPoint(
                canvas = canvas,
                x = x,
                y = y,
                pointPaint = pointPaint
            )

            // Текст с координатами
            val text = "(${point.x.toInt()}, ${point.y.toInt()})"
            textDrawer.drawText(
                canvas = canvas,
                x = x,
                y = y,
                text = text,
                textPaint = textPaint
            )

            // Соединяем точки
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        // Рисуем линии
        lineDrawer.drawLine(
            canvas = canvas,
            path = path,
            linePaint = linePaint
        )
    }
}