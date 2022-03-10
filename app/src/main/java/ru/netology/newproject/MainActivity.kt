package ru.netology.newproject

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.newproject.data.api.ApiHelper
import ru.netology.newproject.data.api.ApiServiceImpl
import ru.netology.newproject.data.model.ResultResponseDto
import ru.netology.newproject.ui.base.ViewModelFactory
import ru.netology.newproject.ui.main.adapter.MainAdapter
import ru.netology.newproject.ui.main.viewmodel.MainViewModel
import ru.netology.newproject.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
        setupViewModel()
        setupObserver()
    }

    fun setupUi() {
        rvUsersView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        rvUsersView.addItemDecoration(
            DividerItemDecoration( // автоматически разделяет элементы в ресайкл вью
                rvUsersView.context,
                (rvUsersView.layoutManager as LinearLayoutManager
                        ).orientation
            )
        )
        rvUsersView.adapter = adapter
    }

    fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    usersProgressView.visibility = View.GONE
                    it.data?.let { users -> //список из user
                        renderList(users)
                        rvUsersView.visibility = View.VISIBLE
                    }
                }
                Status.LOADING -> {
                    usersProgressView.visibility = View.VISIBLE
                    rvUsersView.visibility = View.GONE
                }
                Status.ERROR -> {
                    usersProgressView.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun renderList(users: List<ResultResponseDto>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    fun setupViewModel(){
        mainViewModel= ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }
}