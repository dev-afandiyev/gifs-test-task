/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.di

import com.media.gifsapp.feature.data.repository.gifdetails.GifDetailsRepositoryImpl
import com.media.gifsapp.feature.domain.cases.gif.GifDetailsUseCases
import com.media.gifsapp.feature.domain.cases.gif.GetGifDetailsUseCase
import com.media.gifsapp.feature.domain.cases.gif.mark.ClearGifCacheUseCase
import com.media.gifsapp.feature.domain.cases.gif.mark.GifDeleteUseCase
import com.media.gifsapp.feature.domain.repository.IGifDetailsRepository
import com.media.gifsapp.feature.presentation.gif.GifViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gifModule = module {

    viewModel { GifViewModel(appContext = androidContext(), useCases = get()) }

    factory<GetGifDetailsUseCase> { GetGifDetailsUseCase(repository = get()) }
    factory<GifDeleteUseCase> { GifDeleteUseCase(repository = get(), clearCache = get()) }
    factory<GifDetailsUseCases> { GifDetailsUseCases(getGifDetails = get(), markGifHidden = get()) }
    factory<ClearGifCacheUseCase> { ClearGifCacheUseCase(context = androidContext()) }
    factory<IGifDetailsRepository> { GifDetailsRepositoryImpl(gifsDao = get()) }

}
