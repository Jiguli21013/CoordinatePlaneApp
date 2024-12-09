package com.yanchelenko.coordinateplaneapp.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.yanchelenko.coordinateplaneapp.data.remote.responses.PointResponse

data class PointsResponse(
    @SerializedName("points") val points: List<PointResponse>
)
