package com.pagingmvvmcoroutinehiltcompose.domain.repository

import androidx.paging.PagingData
import com.pagingmvvmcoroutinehiltcompose.core.common.Resource
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters() : Flow<PagingData<Character>>

    fun getCharacterDetails(id: Int) : Flow<Resource<Character>>
}

