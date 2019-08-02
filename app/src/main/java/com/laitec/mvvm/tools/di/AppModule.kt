package com.laitec.mvvm.tools.di

import android.content.Context
import com.laitec.mvvm.api.GithubService
import com.laitec.mvvm.db.LocalCache
import com.laitec.mvvm.db.RepoDataBase
import com.laitec.mvvm.repository.DataRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideGithubServices() : GithubService {
        return GithubService.create()
    }

    @Provides
    fun provideRepoDataBase() : RepoDataBase {
        return RepoDataBase.dataBase(context)
    }

    @Provides
    fun provideLocalCache(db : RepoDataBase) : LocalCache {
        return LocalCache(db, Executors.newSingleThreadExecutor())
    }

    @Provides
    fun provideDataRepository(cache : LocalCache, service: GithubService) : DataRepository {
        return DataRepository(cache = cache,services = service)
    }
}