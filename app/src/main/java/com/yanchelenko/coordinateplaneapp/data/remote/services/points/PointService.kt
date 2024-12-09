package com.yanchelenko.coordinateplaneapp.data.remote.services.points

import com.yanchelenko.coordinateplaneapp.data.remote.responses.PointsResponse

interface PointService {
    suspend fun getListOfPoints(numberOfPoints: Int): Result<PointsResponse>
}
