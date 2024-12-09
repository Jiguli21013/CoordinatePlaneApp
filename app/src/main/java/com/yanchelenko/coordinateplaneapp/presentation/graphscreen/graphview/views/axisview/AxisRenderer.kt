package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview

import android.graphics.Canvas
import android.graphics.Paint
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.GraphStateController
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.Renderer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.drawers.AxisDrawer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.drawers.GridDrawer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.drawers.LabelDrawer
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.PointUI
import com.yanchelenko.tableandgraphapp.ui.table.diagramview.views.axisview.interfaces.IAxisDrawer
import com.yanchelenko.tableandgraphapp.ui.table.diagramview.views.axisview.interfaces.IGridDrawer
import com.yanchelenko.tableandgraphapp.ui.table.diagramview.views.axisview.interfaces.ILabelDrawer

class AxisRenderer : Renderer {

    private val axisDrawer: IAxisDrawer = AxisDrawer()
    private val labelDrawer: ILabelDrawer = LabelDrawer()
    private val gridDrawer: IGridDrawer = GridDrawer()

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
        // Центр экрана с учетом смещения
        val centerX =  canvas.width / 2f + graphState.offsetX
        val centerY =  canvas.height / 2f + graphState.offsetY

        // Рисуем ось X
        val startX = centerX - graphState.graphWidth * cellSize
        val endX = centerX + graphState.graphWidth * cellSize
        axisDrawer.drawAxis(canvas, startX, centerY, endX, centerY, axisPaint)

        // Рисуем ось Y
        val startY = centerY - graphState.graphHeight * cellSize
        val endY = centerY + graphState.graphHeight * cellSize
        axisDrawer.drawAxis(canvas, centerX, startY, centerX, endY, axisPaint)

        // Рисуем метки и значения на оси X
        for (i in -graphState.graphWidth.toInt()..graphState.graphWidth.toInt() step 10) {
            val x = centerX + (i * cellSize)
            val y = centerY + EXTRA_SPACE
            labelDrawer.drawLabel(canvas, x, y, i.toString(), textPaint)
        }

        // Рисуем метки и значения на оси Y
        for (i in -graphState.graphHeight.toInt()..graphState.graphHeight.toInt() step 10) {
            val x = centerX + EXTRA_SPACE
            val y = centerY - (i * cellSize)
            labelDrawer.drawLabel(canvas, x, y, i.toString(), textPaint)
        }

        // Вертикальные линии (сетка)
        for (i in -graphState.graphWidth.toInt()..graphState.graphWidth.toInt() step 10) {
            val x = centerX + (i * cellSize)
            gridDrawer.drawGridLine(canvas, x, startY, x, endY, gridPaint)
        }

        // Горизонтальные линии (сетка)
        for (i in -graphState.graphHeight.toInt()..graphState.graphHeight.toInt() step 10) {
            val y = centerY - (i * cellSize)
            gridDrawer.drawGridLine(canvas, startX, y, endX, y, gridPaint)
        }
    }

    companion object {
        private const val EXTRA_SPACE = 30f
    }
}
