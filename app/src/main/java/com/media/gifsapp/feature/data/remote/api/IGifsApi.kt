/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IGifsApi {

    @GET("v1/gifs/search")
    suspend fun getGifs(
        @Query("q") query: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("lang") lang: String?,
        @Query("rating") rating: String?,
        @Query("bundle") bundle: String?,
    ): Response<GifsQueryResponse>

}