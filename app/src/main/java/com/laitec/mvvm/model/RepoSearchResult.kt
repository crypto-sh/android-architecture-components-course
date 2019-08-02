package com.laitec.mvvm.model

import androidx.lifecycle.LiveData


data class RepoSearchResult(
    val data : LiveData<List<RepoSearch>>?,
    val network : LiveData<Boolean>?
)