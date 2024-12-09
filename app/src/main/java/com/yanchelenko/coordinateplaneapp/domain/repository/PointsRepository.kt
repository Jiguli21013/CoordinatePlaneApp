package com.yanchelenko.coordinateplaneapp.domain.repository

import com.yanchelenko.coordinateplaneapp.domain.entities.PointEntity

interface PointsRepository {
    suspend fun doRequest(numberOfPoints: Int): Result<List<PointEntity>>
}
