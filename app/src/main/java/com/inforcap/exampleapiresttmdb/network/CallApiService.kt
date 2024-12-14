package com.inforcap.exampleapiresttmdb.network

import com.inforcap.exampleapiresttmdb.model.FigureDetailEntity
import com.inforcap.exampleapiresttmdb.model.FigureEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CallApiService {

    @GET("figuras")
    suspend fun getAllFigures() : Response<List<FigureEntity>>

    @GET("figuras/{id}")
    suspend fun getDetail(
        @Path("id") figureId: Int
    ) : Response<FigureDetailEntity>



}