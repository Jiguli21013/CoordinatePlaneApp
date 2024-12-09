package com.yanchelenko.coordinateplaneapp.di

import com.yanchelenko.coordinateplaneapp.data.NetworkPointsRepository
import com.yanchelenko.coordinateplaneapp.data.remote.services.points.PointService
import com.yanchelenko.coordinateplaneapp.domain.repository.PointsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAuthRepository(service: PointService): PointsRepository = NetworkPointsRepository(service)
}
