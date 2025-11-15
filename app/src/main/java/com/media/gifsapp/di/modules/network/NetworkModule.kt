/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.di.modules.network

import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.media.gifsapp.core.data.remote.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "EG8eIyaOBeDnl9t5nVn69Js10ZI6Q9ef"
const val BASE_URL = "https://api.giphy.com"

val networkModule = module {

    singleOf(::AuthInterceptor) { bind<AuthInterceptor>() }

    single {
        val interceptor = get<AuthInterceptor>()

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        okHttpClientBuilder.addInterceptor(
            HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }
        )

        okHttpClientBuilder.build().apply {
            dispatcher.maxRequests = 50
            dispatcher.maxRequestsPerHost = 50
        }
    }

    single {
        val okHttpClient = get<OkHttpClient>()
        val gson = GsonBuilder()
            .setStrictness(Strictness.STRICT)
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}