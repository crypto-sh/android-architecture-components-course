package com.laitec.mvvm.api

import com.laitec.mvvm.BuildConfig
import com.laitec.mvvm.model.RepoSearchResponse
import com.laitec.mvvm.model.RepoSearch
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//implementation 'com.squareup.retrofit2:retrofit:2.6.0'
//implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
//implementation 'com.squareup.okhttp3:logging-interceptor:3.12.0'


/**
 * you can add it on Retrofit by
 *.addCallAdapterFactory(CoroutineCallAdapterFactory())
 */
//implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
interface GithubService {

    @GET("search/repositories?sort=stargazers_count")
    fun searchRepos(@Query("q") query : String,
                    @Query("page") page : Int,
                    @Query("per_page") ItemsPerPage : Int) ://kotlin coroutines -> Deferred<RepoSearchResponse>
            Call<RepoSearchResponse>


    @GET("search/repositories?sort=stargazers_count")
    fun searchReposAsync(@Query("q") query : String,
                    @Query("page") page : Int,
                    @Query("per_page") ItemsPerPage : Int) : Deferred<RepoSearchResponse>

    companion object {

        fun create() : GithubService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)

        }
    }
}
private const val IN_QUALIFIER = "in:name,description"

fun searchRepos(
    service         : GithubService,
    query           : String,
    page            : Int,
    itemsPerPage    : Int,
    onError : (error : String) -> Unit,
    onSuccess : (repos : List<RepoSearch>) -> Unit
){
    val apiQuery = query + IN_QUALIFIER

    service.searchRepos(apiQuery,page,itemsPerPage).enqueue(object : Callback<RepoSearchResponse> {

        override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
            onError(t.message ?: "unknown error")
        }

        override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
            if (response.isSuccessful){
                response.body().let {
                    if (it != null)
                        onSuccess(it.items)
                    else
                        onError("empty response")
                }
            }else{
                onError("error connecting server")
            }
        }
    })

}

suspend fun searchReposCoroutines(
    service         : GithubService,
    query           : String,
    page            : Int,
    itemsPerPage    : Int) : RepoSearchResponse? {
    try {
        return service.searchReposAsync(query,page, itemsPerPage).await()
    } catch (e: Exception) {
        return null
    }
}