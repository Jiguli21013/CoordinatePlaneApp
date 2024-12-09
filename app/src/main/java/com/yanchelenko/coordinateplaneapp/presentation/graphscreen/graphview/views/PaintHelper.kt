package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views

import android.graphics.Color
import android.graphics.Paint

enum class PaintType {
    POINT, TEXT, LINE, AXIS, GRID
}

private fun createPaint(
    color: Int,
    strokeWidth: Float = 0f,
    style: Paint.Style = Paint.Style.FILL,
    isAntiAlias: Boolean = true,
    textSize: Float = 0f,
    textAlign: Paint.Align? = null
): Paint {
    return Paint().apply {
        this.color = color
        this.strokeWidth = strokeWidth
        this.style = style
        this.isAntiAlias = isAntiAlias
        if (textSize > 0) this.textSize = textSize
        if (textAlign != null) this.textAlign = textAlign
    }
}

fun createPaintByType(type: PaintType): Paint {
    return when (type) {
        PaintType.POINT -> createPaint(color = Color.RED, strokeWidth = 14f)
        PaintType.TEXT -> createPaint(color = Color.BLACK, textSize = 20f, textAlign = Paint.Align.CENTER)
        PaintType.LINE -> createPaint(color = Color.GREEN, strokeWidth = 6f, style = Paint.Style.STROKE)
        PaintType.AXIS -> createPaint(color = Color.BLACK, strokeWidth = 3f)
        PaintType.GRID -> createPaint(color = Color.LTGRAY, strokeWidth = 2f)
    }
}
