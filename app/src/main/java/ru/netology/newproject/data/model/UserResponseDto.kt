package ru.netology.newproject.data.model

import com.google.gson.annotations.SerializedName

data class UserResponseDto (
    @SerializedName("info")
    val info:InfoResponseDto,

    @SerializedName("results")
    val results:List<ResultResponseDto>
)