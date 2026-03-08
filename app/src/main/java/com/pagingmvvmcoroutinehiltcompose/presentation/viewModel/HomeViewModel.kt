package com.pagingmvvmcoroutinehiltcompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import com.pagingmvvmcoroutinehiltcompose.domain.useCase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userCase: GetAllCharactersUseCase
): ViewModel(){

    val characters : Flow<PagingData<Character>> =
        userCase().cachedIn(viewModelScope)

}