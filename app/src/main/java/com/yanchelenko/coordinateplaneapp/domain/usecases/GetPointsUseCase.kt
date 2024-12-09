package com.yanchelenko.coordinateplaneapp.domain.usecases

import com.yanchelenko.coordinateplaneapp.domain.repository.PointsRepository
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.GraphModelUI
import com.yanchelenko.coordinateplaneapp.presentation.modelsUI.toGraphModelUI


class GetPointsUseCase(
    private val repository: PointsRepository
) {

    suspend fun execute(numberOfPoints: Int): Result<GraphModelUI> {
          return repository.doRequest(numberOfPoints = numberOfPoints)
            .map { listOfPointEntity ->
                listOfPointEntity
                    .sortedBy { it.x }
                    .toGraphModelUI()
            }
    }
}
