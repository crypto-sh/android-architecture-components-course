package com.laitec.mvvm.db

import androidx.lifecycle.LiveData
import com.laitec.mvvm.model.RepoSearch
import java.util.concurrent.Executor

class LocalCache(
    private val db: RepoDataBase,
    private val ioExecutor: Executor
) {

    fun insertRepo(repos: List<RepoSearch>) {
        ioExecutor.execute {
            db.repoDao().insertRepos(repos)
        }
    }

    fun updateRepo(repo : RepoSearch) {
        ioExecutor.execute {
            db.repoDao().updateRepo(repo)
        }
    }

    fun deleteRepos() {
        ioExecutor.execute {
            db.repoDao().deleteRepos()
        }
    }

    fun deleteRepo(repo: RepoSearch) {
        ioExecutor.execute {
            db.repoDao().deleteRepo(repo)
        }
    }

    fun loadRepos(): LiveData<List<RepoSearch>> = db.repoDao().loadRepos()
}