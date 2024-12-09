package com.yanchelenko.coordinateplaneapp.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.yanchelenko.coordinateplaneapp.domain.entities.PointEntity

data class PointResponse(
    @SerializedName ("x") val x: Float?,
    @SerializedName ("y") val y: Float?
)

fun PointResponse.toEntity() = PointEntity(
    x = x ?: 0f,
    y = y ?: 0f,
)
