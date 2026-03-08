package com.pagingmvvmcoroutinehiltcompose.data.mapper

import com.pagingmvvmcoroutinehiltcompose.data.dto.Result
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character

fun Result.toDomainCharacter() : Character{
    return Character(
            episode?:emptyList(),
            gender,
            id,
            image,
            name,
            species,
            status,
            url)
}