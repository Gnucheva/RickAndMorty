package ru.netology.newproject.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.netology.newproject.data.model.ResultResponseDto
import ru.netology.newproject.data.repository.MainRepository
import ru.netology.newproject.utils.Resource

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ResultResponseDto>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ usersList ->
                    users.postValue(Resource.success(usersList))
                }, {
                    users.postValue(Resource.error(it.message !!, null))
                })
        )
    }

    override fun onCleared() { // отписаться , что бы не было утечки памяти
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers():LiveData<Resource<List<ResultResponseDto>>> = users //динамически данные изменяются

}