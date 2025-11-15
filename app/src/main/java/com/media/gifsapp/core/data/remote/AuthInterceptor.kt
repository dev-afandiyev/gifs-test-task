/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.core.data.remote

import com.media.gifsapp.di.modules.network.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().also {
            it.addHeader("Accept", "application/json")
        }

        val request = builder.build().let { r ->
            r.newBuilder()
                .url(
                    r.url.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build()
                )
                .build()
        }

        return chain.proceed(request)
    }

}