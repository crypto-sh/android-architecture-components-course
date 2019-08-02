package com.laitec.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.laitec.mvvm.model.RepoSearch

@Dao
interface RepoDao {

    @Query("select * from RepoSearch ORDER BY id DESC limit 1")
    fun lastRepo() : LiveData<RepoSearch>

//    @Query("select * from RepoSearch where id == :_id")
//    fun loadRepo(_id : Long) : LiveData<RepoSearch>

    @Query("select * from RepoSearch")
    fun loadRepos() : LiveData<List<RepoSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos : List<RepoSearch>)

    @Update
    fun updateRepo(repo : RepoSearch)

    @Query("delete from RepoSearch")
    fun deleteRepos()

    @Delete
    fun deleteRepo(repo : RepoSearch)


}