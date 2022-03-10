package ru.netology.newproject.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import ru.netology.newproject.data.model.UserResponseDto

class ApiServiceImpl : ApiService {
    override fun getUsers(): Single<UserResponseDto> =
        Rx2AndroidNetworking.get("https://rickandmortyapi.com/api/character")
            .build()
            .getObjectSingle(UserResponseDto::class.java)
}