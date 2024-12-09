package com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.DataUpdatable
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.GraphStateController
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.interfaces.Renderer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.listeners.GestureListener
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.listeners.ScaleListener
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.PaintType
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.axisview.AxisRenderer
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.createPaintByType
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.graphview.views.graphview.GraphRenderer
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.GraphModelUI
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.PointUI

class GraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), GraphStateController, DataUpdatable<GraphModelUI> {

    private val points = mutableListOf<PointUI>()
    private var graphModelUI: GraphModelUI? = null

    override val graphWidth: Float = DEFAULT_SIZE_OF_GRAPH_IN_WIDTH
    override val graphHeight: Float = DEFAULT_SIZE_OF_GRAPH_IN_HEIGHT

    // Смещение
    override var offsetX = DEFAULT_OFFSET_FACTOR
    override var offsetY = DEFAULT_OFFSET_FACTOR

    // Масштаб
    override var scaleFactor = DEFAULT_SCALE_FACTOR

    // Размер ячейки
    override var cellSize = minOf(width, height) / (DEFAULT_CELL_SIZE_DIVIDER)

    private val pointPaint = createPaintByType(type = PaintType.POINT)
    private val axisPaint = createPaintByType(type = PaintType.AXIS)
    private val textPaint = createPaintByType(type = PaintType.TEXT)
    private val linePaint = createPaintByType(type = PaintType.LINE)
    private val gridPaint = createPaintByType(type = PaintType.GRID)

    private val gestureDetector = GestureDetector(context, GestureListener(this))
    private val scaleDetector: ScaleGestureDetector = ScaleGestureDetector(context, ScaleListener(this))

    private val renderers = listOf<Renderer>(AxisRenderer(), GraphRenderer())

    override fun resetGraph() {
        offsetX = DEFAULT_OFFSET_FACTOR
        offsetY = DEFAULT_OFFSET_FACTOR
        scaleFactor = DEFAULT_SCALE_FACTOR
        invalidate()
    }
    // Ограничения при смещении
    override fun constrainOffsets() {
        graphModelUI?.let { graphModel ->
            val graphWidthInPixels =
                (graphModel.sizeOfXAxisInWidth * cellSize * scaleFactor).coerceAtLeast(1f)
            val graphHeightInPixels =
                (graphModel.sizeOfYAxisInHeight * cellSize * scaleFactor).coerceAtLeast(1f)

            var minX = -(graphWidthInPixels / 2) + getHorizontalCenterOfView()
            var maxX = (graphWidthInPixels / 2) - getHorizontalCenterOfView()

            if (minX > maxX) minX = maxX.also { maxX = minX }

            var minY = -(graphHeightInPixels / 2) + getVerticalCenterOfView()
            var maxY = (graphHeightInPixels / 2) - getVerticalCenterOfView()

            if (minY > maxY) minY = maxY.also { maxY = minY }

            offsetX = offsetX.coerceIn(minX, maxX)
            offsetY = offsetY.coerceIn(minY, maxY)

            invalidate()
        }
    }

    override fun updateScale(focusX: Float, focusY: Float, scaleDelta: Float) {
        offsetX += (focusX - getHorizontalCenterOfView() - offsetX) * (1 - scaleDelta) * DEFAULT_SMOOTHING_FACTOR
        offsetY += (focusY - getVerticalCenterOfView() - offsetY) * (1 - scaleDelta) * DEFAULT_SMOOTHING_FACTOR
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        // Масштаб и смещение
        canvas.scale(scaleFactor, scaleFactor, getHorizontalCenterOfView().toFloat(), getVerticalCenterOfView().toFloat())
        canvas.translate(offsetX / scaleFactor, offsetY / scaleFactor)

        // Рисуем view
        renderers.forEach {
            it.draw(
                canvas = canvas,
                graphState = this,
                cellSize = cellSize,
                points = points,
                gridPaint = gridPaint,
                axisPaint = axisPaint,
                textPaint = textPaint,
                pointPaint = pointPaint,
                linePaint = linePaint
            )
        }
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cellSize = getNewCellSize()
    }

    override fun invalidateView() {
        invalidate()
    }

    override fun setData(newData: GraphModelUI) {
        points.clear()
        points.addAll(newData.points)
        graphModelUI = newData
        cellSize = getNewCellSize()
        invalidate()
    }

    private fun getHorizontalCenterOfView(): Int = (width / 2)
    private fun getVerticalCenterOfView(): Int = (height / 2)

    private fun getNewCellSize(): Float =
        minOf(width, height) / (graphModelUI?.maxXorY ?: DEFAULT_CELL_SIZE_DIVIDER)

    companion object {
        private const val DEFAULT_SCALE_FACTOR: Float = 0.5f
        private const val DEFAULT_SMOOTHING_FACTOR: Float = 0.5f
        private const val DEFAULT_OFFSET_FACTOR: Float = 0f

        private const val DEFAULT_SIZE_OF_GRAPH_IN_WIDTH = 300f
        private const val DEFAULT_SIZE_OF_GRAPH_IN_HEIGHT = 300f
        private const val DEFAULT_CELL_SIZE_DIVIDER = 160f
    }
}
