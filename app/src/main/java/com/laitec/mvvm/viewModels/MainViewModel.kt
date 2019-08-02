package com.laitec.mvvm.viewModels

import android.content.Context
import androidx.lifecycle.*
import com.laitec.mvvm.api.GithubService
import com.laitec.mvvm.db.LocalCache
import com.laitec.mvvm.db.RepoDataBase
import com.laitec.mvvm.repository.DataRepository
import com.laitec.mvvm.tools.SingleLiveEvent
import com.laitec.mvvm.ui.base.BaseViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainViewModel(val repoSearch : DataRepository) : BaseViewModel() {

    private val searchQuery = MutableLiveData<String>()

    val repo = Transformations.map(searchQuery) {
        repoSearch.searchRepoByQuery(it)
    }

    val data = Transformations.switchMap(repo){
        it.data
    }

    val status = Transformations.switchMap(repo){
        it!!.network
    }

    val onDataRequest : SingleLiveEvent<Any> = SingleLiveEvent()

    fun searchQuery(query : String){
        searchQuery.postValue(query)
        onDataRequest.postValue(Any())
    }

    data class SearchResult(val data : LiveData<List<String>>,val network : LiveData<String>)

    class Factory(val repo : DataRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repoSearch = repo) as T
        }
    }

}