package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.drawers

import android.graphics.Canvas
import android.graphics.Paint
import com.yanchelenko.tableandgraphapp.ui.table.diagramview.views.axisview.interfaces.ILabelDrawer

class LabelDrawer : ILabelDrawer {
    override fun drawLabel(canvas: Canvas, x: Float, y: Float, text: String, paint: Paint) {
        canvas.drawText(text, x, y, paint)
    }
}
