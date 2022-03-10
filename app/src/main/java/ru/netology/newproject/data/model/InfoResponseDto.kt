package ru.netology.newproject.data.model

import com.google.gson.annotations.SerializedName

data class InfoResponseDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("prev")
    val prev: String? = null
)