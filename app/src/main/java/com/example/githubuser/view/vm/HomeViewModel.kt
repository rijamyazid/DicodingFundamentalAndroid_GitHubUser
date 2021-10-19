package com.example.githubuser.view.vm

import androidx.lifecycle.*
import com.example.githubuser.datasource.local.LocalSealed
import com.example.githubuser.datasource.local.model.UserModel
import com.example.githubuser.datasource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun refresh(query: String? = this.query.value) {
        _dataUsers = repository.getUsers().distinctUntilChanged()
        this._query.postValue(query)
    }

    private var _dataUsers = getUsers().distinctUntilChanged()
    val dataUsers get() = _dataUsers
    fun getUsers(): LiveData<LocalSealed<List<UserModel>>> {
        return repository.getUsers()
    }

    private var _query = MutableLiveData<String>()
    val query get() = _query
    fun setQuery(query: String?) {
        this._query.postValue(query)
    }

    private var _dataUsersByQuery = query.switchMap { getUsersByQuery(it) }.distinctUntilChanged()
    val dataUsersByQuery get() = _dataUsersByQuery
    fun getUsersByQuery(query: String?) = repository.getUsersByQuery(query)

}