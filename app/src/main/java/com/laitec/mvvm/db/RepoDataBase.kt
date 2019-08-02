package com.laitec.mvvm.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.laitec.mvvm.model.RepoSearch


@Database(entities = [RepoSearch::class],version = 1,exportSchema = false)
abstract class RepoDataBase : RoomDatabase() {

    abstract fun repoDao() : RepoDao

    companion object{
        fun dataBase(context : Context) : RepoDataBase = Room.databaseBuilder(context,RepoDataBase::class.java,"repo").build()
    }

}