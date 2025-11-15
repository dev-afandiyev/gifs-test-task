/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.di

import com.media.gifsapp.feature.data.remote.api.IGifsApi
import com.media.gifsapp.feature.data.repository.gif.GifsRepositoryImpl
import com.media.gifsapp.feature.domain.cases.home.HomeUseCases
import com.media.gifsapp.feature.domain.cases.home.list.GetGifsUseCase
import com.media.gifsapp.feature.domain.repository.IGifsRepository
import com.media.gifsapp.feature.presentation.home.HomeViewModel
import com.media.gifsapp.core.data.db.GifsDatabase
import com.media.gifsapp.feature.data.local.IGifsDao
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val homeModule = module {

    viewModelOf(::HomeViewModel)

    single<IGifsApi> { get<Retrofit>().create() }
    single<IGifsDao> { get<GifsDatabase>().gifsDao() }

    factory<IGifsRepository> { GifsRepositoryImpl(gifsDao = get(), gifsApi = get()) }
    factory<GetGifsUseCase> { GetGifsUseCase(repository = get()) }
    factory<HomeUseCases> { HomeUseCases(markGifHidden = get(), getGifs = get()) }

}