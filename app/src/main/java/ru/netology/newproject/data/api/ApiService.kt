package ru.netology.newproject.data.api

import io.reactivex.Single
import ru.netology.newproject.data.model.UserResponseDto

interface ApiService {

    fun getUsers():Single<UserResponseDto> //возвращает один раз данные, Observable - данные меняются динамически , данные постоянно поступают , когда меняются.
}