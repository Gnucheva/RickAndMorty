package ru.netology.newproject.data.api

class ApiHelper (private val apiService:ApiService){
    fun getUsers()=apiService.getUsers()
}