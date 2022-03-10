package ru.netology.newproject.data.repository

import io.reactivex.Single
import ru.netology.newproject.data.api.ApiHelper
import ru.netology.newproject.data.model.ResultResponseDto

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<ResultResponseDto>> { //список пользователей
        val response = apiHelper.getUsers()
        return response.map {
            it.results
        }
    }
}