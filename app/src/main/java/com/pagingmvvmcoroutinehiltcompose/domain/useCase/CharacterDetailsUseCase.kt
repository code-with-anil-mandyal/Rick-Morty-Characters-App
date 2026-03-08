package com.pagingmvvmcoroutinehiltcompose.domain.useCase

import com.pagingmvvmcoroutinehiltcompose.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterDetailsUseCase @Inject constructor(private val repository : CharacterRepository) {
    operator fun invoke(id : Int) = repository.getCharacterDetails(id)
}