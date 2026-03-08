package com.pagingmvvmcoroutinehiltcompose.data.api

import com.pagingmvvmcoroutinehiltcompose.data.api.ApiRoutes.GET_ALL_CHARACTERS
import com.pagingmvvmcoroutinehiltcompose.data.api.ApiRoutes.GET_CHARACTER_DETAILS
import com.pagingmvvmcoroutinehiltcompose.data.dto.CharactersDto
import com.pagingmvvmcoroutinehiltcompose.data.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GET_ALL_CHARACTERS)
    suspend fun getAllCharacters(
    @Query("page") page : Int
    ): CharactersDto

    @GET(GET_CHARACTER_DETAILS)
    suspend fun getCharacterDetails(
        @Path("id") id : Int
    ): Result

}