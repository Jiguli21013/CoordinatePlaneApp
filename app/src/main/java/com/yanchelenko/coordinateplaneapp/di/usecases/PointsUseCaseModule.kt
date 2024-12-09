package com.yanchelenko.coordinateplaneapp.di.usecases

import com.yanchelenko.coordinateplaneapp.domain.repository.PointsRepository
import com.yanchelenko.coordinateplaneapp.domain.usecases.GetPointsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PointsUseCaseModule {
    @Provides
    fun provideGetPointsUseCase(pointsRepository: PointsRepository) =
        GetPointsUseCase(pointsRepository)
}
