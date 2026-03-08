package com.pagingmvvmcoroutinehiltcompose.domain.useCase

import com.pagingmvvmcoroutinehiltcompose.domain.repository.CharacterRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    operator fun invoke() = characterRepository.getAllCharacters()
}