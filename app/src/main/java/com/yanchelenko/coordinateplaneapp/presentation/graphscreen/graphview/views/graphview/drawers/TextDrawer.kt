package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.drawers

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces.ITextDrawer

class TextDrawer : ITextDrawer {
    override fun drawText(
        canvas: Canvas,
        x: Float,
        y: Float,
        text: String,
        textPaint: Paint
    ) {
        if (y < 0) {
            canvas.drawText(text, x, y + 35f, textPaint)
        } else {
            canvas.drawText(text, x, y - 25f, textPaint)
        }
    }
}
