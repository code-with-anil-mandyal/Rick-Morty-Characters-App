package com.pagingmvvmcoroutinehiltcompose.data.dto


import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>
)