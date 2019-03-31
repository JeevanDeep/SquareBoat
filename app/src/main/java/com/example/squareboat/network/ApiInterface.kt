package com.example.squareboat.network

import com.example.squareboat.model.icon.IconResponse
import com.example.squareboat.utils.ApiConstants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConstants.GET_ANIMAL_ICONS)
    fun getIcons(
        @Query("client_id") id: String = ApiConstants.CLIENT_ID,
        @Query("client_secret") secret: String = ApiConstants.CLIENT_SECRET
    ): Single<IconResponse>

    @GET(ApiConstants.GET_ANIMAL_ICONS)
    fun getMoreIcons(
        @Query("client_id") id: String = ApiConstants.CLIENT_ID,
        @Query("client_secret") secret: String = ApiConstants.CLIENT_SECRET,
        @Query("after") offset: Int
    ): Single<IconResponse>

    @GET(ApiConstants.SEARCH_ICON)
    fun searchIcons(
        @Query("client_id") id: String = ApiConstants.CLIENT_ID,
        @Query("client_secret") secret: String = ApiConstants.CLIENT_SECRET,
        @Query("offset") offset: Int,
        @Query("query") query: String,
        @Query("premium") premium: Boolean = false
    ): Single<IconResponse>
}