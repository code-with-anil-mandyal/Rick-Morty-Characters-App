package com.pagingmvvmcoroutinehiltcompose.presentation.state

import com.pagingmvvmcoroutinehiltcompose.domain.model.Character

data class CharactersState(
    val data: Character? = null,
    val errorMsg: String? = "",
    val isLoading: Boolean = false
)