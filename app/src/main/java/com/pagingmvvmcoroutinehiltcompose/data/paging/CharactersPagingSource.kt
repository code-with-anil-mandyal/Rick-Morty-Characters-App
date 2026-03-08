package com.pagingmvvmcoroutinehiltcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pagingmvvmcoroutinehiltcompose.data.api.ApiService
import com.pagingmvvmcoroutinehiltcompose.data.mapper.toDomainCharacter
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character

class CharactersPagingSource(private val apiService: ApiService): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
      return  try {

            val page = params.key?:1
            val response = apiService.getAllCharacters(page)

            val characters = response.results.map {
                it.toDomainCharacter()
            }

            LoadResult.Page(
                data = characters,
                prevKey = if(page == 1) null else page -1,
                nextKey = if(page == response.info?.pages) null else page +1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}