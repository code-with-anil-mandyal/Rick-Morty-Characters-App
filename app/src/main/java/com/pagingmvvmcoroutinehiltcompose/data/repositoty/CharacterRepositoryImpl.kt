package com.pagingmvvmcoroutinehiltcompose.data.repositoty

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pagingmvvmcoroutinehiltcompose.core.common.Resource
import com.pagingmvvmcoroutinehiltcompose.data.api.ApiService
import com.pagingmvvmcoroutinehiltcompose.data.mapper.toDomainCharacter
import com.pagingmvvmcoroutinehiltcompose.data.paging.CharactersPagingSource
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import com.pagingmvvmcoroutinehiltcompose.domain.repository.CharacterRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(private val apiService: ApiService) : CharacterRepository {
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService)
            }
        ).flow
    }

    override fun getCharacterDetails(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        try {

            val result = apiService.getCharacterDetails(id)

            emit(Resource.Success(result.toDomainCharacter()))

        }catch (e : Exception){
            emit(Resource.Error(e.message?: "Unknown Error"))
        }
    }

}