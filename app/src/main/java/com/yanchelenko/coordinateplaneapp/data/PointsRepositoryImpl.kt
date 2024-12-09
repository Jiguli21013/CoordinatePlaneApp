package com.yanchelenko.coordinateplaneapp.data

import com.yanchelenko.coordinateplaneapp.data.remote.responses.toEntity
import com.yanchelenko.coordinateplaneapp.data.remote.services.points.PointService
import com.yanchelenko.coordinateplaneapp.domain.entities.PointEntity
import com.yanchelenko.coordinateplaneapp.domain.repository.PointsRepository
import kotlinx.coroutines.flow.map

class PointsRepositoryImpl(
    private val service: PointService
): PointsRepository {

    override suspend fun doRequest(numberOfPoints: Int): Result<List<PointEntity>> {
        return service.getListOfPoints(numberOfPoints = numberOfPoints)
            .map { response ->
                response.points.map { it.toEntity() }
            }
    }
}
