package com.yanchelenko.coordinateplaneapp.presentation.modelsUI

import com.yanchelenko.coordinateplaneapp.domain.entities.PointEntity

data class GraphModelUI(
    val points: List<PointUI>,
    val maxXorY: Float,
    val sizeOfXAxisInWidth: Float, // |maxX| or |-maxX|
    val sizeOfYAxisInHeight: Float, // |maxY| or |-maxY|
)

fun List<PointEntity>.toGraphModelUI(): GraphModelUI {

    var maxX = 0f
    var maxY = 0f

    val points = mutableListOf<PointUI>()

    for (pointEntity in this) {
        val pointUI = PointUI(pointEntity.x, pointEntity.y)
        points.add(pointUI)

        maxX = maxOf(maxX, kotlin.math.abs(pointEntity.x))
        maxY = maxOf(maxY, kotlin.math.abs(pointEntity.y))
    }

    val maxXorY = maxOf(maxX, maxY)
    val additionalAxisSize = 50f

    return GraphModelUI(
        points = points,
        maxXorY = maxXorY,
        sizeOfXAxisInWidth = maxX + additionalAxisSize,
        sizeOfYAxisInHeight = maxY + additionalAxisSize
    )
}
