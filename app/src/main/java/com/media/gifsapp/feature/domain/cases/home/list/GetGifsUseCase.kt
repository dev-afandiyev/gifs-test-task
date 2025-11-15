/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.home.list

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.media.gifsapp.feature.domain.repository.IGifsRepository
import com.media.gifsapp.core.domain.gifs.GifExternalModel
import com.media.gifsapp.core.domain.gifs.toGifExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGifsUseCase(private val repository: IGifsRepository) {

    suspend operator fun invoke(query: String): Flow<PagingData<GifExternalModel>> {
        return repository.getGifsList(query).flow.map { pagingData ->
            pagingData
                .filter { !it.isHidden }
                .map { it.toGifExternalModel() }
        }
    }

}