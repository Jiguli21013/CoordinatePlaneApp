package com.yanchelenko.coordinateplaneapp.data.remote.services.points

import com.yanchelenko.coordinateplaneapp.data.remote.api.PointsApi
import com.yanchelenko.coordinateplaneapp.data.remote.responses.PointsResponse
import com.yanchelenko.coordinateplaneapp.data.remote.services.BaseService

class PointServiceImpl(
    private val api: PointsApi
): PointService, BaseService() {
    override suspend fun getListOfPoints(numberOfPoints: Int): Result<PointsResponse> = apiCall {
        api.getListOfPoints(numberOfPoints = numberOfPoints)
    }
}
