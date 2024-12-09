package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.interfaces

import android.graphics.Canvas
import android.graphics.Paint

interface ITextDrawer {
    fun drawText(
        canvas: Canvas,
        x: Float,
        y: Float,
        text: String,
        textPaint: Paint
    )
}
