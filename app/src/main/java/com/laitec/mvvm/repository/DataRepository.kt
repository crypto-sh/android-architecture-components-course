package com.laitec.mvvm.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.laitec.mvvm.api.GithubService
import com.laitec.mvvm.api.searchRepos
import com.laitec.mvvm.db.LocalCache
import com.laitec.mvvm.model.RepoSearch
import com.laitec.mvvm.model.RepoSearchResult

class DataRepository constructor(
    private val cache : LocalCache,
    private val services : GithubService
) {

    val network = MutableLiveData<Boolean>()

    fun searchRepoByQuery(query : String) : RepoSearchResult{
        network.postValue(true)

        val data = cache.loadRepos()

        searchRepos(service = services,query = query,page = 1,itemsPerPage = 21,onError = {
            network.postValue(false)
        }, onSuccess = {
            //insert db
            cache.insertRepo(it)
            //data.postValue(it)
            network.postValue(false)
        })
        return RepoSearchResult(data,network)
    }
}