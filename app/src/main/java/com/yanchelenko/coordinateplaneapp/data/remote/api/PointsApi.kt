package com.yanchelenko.coordinateplaneapp.data.remote.api

import com.yanchelenko.coordinateplaneapp.data.remote.responses.PointsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsApi {
    @GET("points")
    suspend fun getListOfPoints(@Query("count") numberOfPoints: Int): Response<PointsResponse>
}
