package ru.netology.newproject.data.model

import com.google.gson.annotations.SerializedName

data class ResultResponseDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("image")
    val image: String,

)