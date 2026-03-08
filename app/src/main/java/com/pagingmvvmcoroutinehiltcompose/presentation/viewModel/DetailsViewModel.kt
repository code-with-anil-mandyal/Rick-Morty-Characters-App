package com.pagingmvvmcoroutinehiltcompose.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagingmvvmcoroutinehiltcompose.core.common.Resource
import com.pagingmvvmcoroutinehiltcompose.domain.useCase.CharacterDetailsUseCase
import com.pagingmvvmcoroutinehiltcompose.presentation.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val useCase: CharacterDetailsUseCase) : ViewModel() {

    private val _characterDetailsState = MutableStateFlow(CharactersState())
    val characterDetailsState : StateFlow<CharactersState>
        get() = _characterDetailsState


    fun getCharacterDetails(id : Int){
        useCase(id).onEach {
            when(it){
                is Resource.Error ->{
                    _characterDetailsState.value = CharactersState().copy(errorMsg = it.msg.toString())
                }

                is Resource.Loading ->{
                    _characterDetailsState.value = CharactersState().copy(isLoading = true)
                }

                is Resource.Success ->{
                    _characterDetailsState.value = CharactersState().copy(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}