package com.laitec.mvvm.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.laitec.mvvm.R
import com.laitec.mvvm.adapter.RvAdapterRepo
import com.laitec.mvvm.databinding.ActivityMainBinding
import com.laitec.mvvm.repository.DataRepository
import com.laitec.mvvm.tools.UploadWorker
import com.laitec.mvvm.tools.di.AppModule
import com.laitec.mvvm.tools.di.DaggerAppComponent
import com.laitec.mvvm.ui.base.ActParent
import com.laitec.mvvm.viewModels.MainViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.concurrent.thread


class MainActivity : ActParent<MainViewModel, ActivityMainBinding>() {

    val TAG = this::class.java.simpleName

    @Inject
    lateinit var repo: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * viewModel
         */
        viewModel.data.observe(this, Observer {
            binding.adapter = RvAdapterRepo(it)
        })
        viewModel.status.observe(this, Observer {
            binding.loading = it!!
        })
        viewModel.onDataRequest.observe(this, Observer {
            //to do something
        })

        rxJavaCode()

//        val lib = Library()
//        Log.d(TAG, "library version ${lib.checkVersion()}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.searchQuery("android")

    }

    override fun inject() {
        DaggerAppComponent.builder()
            .appModule(AppModule(this@MainActivity))
            .build()
            .inject(this)
    }

    suspend fun testAwait() {
        delay(3000)
        print("hello")
    }

    fun coroutunes() {
        thread { }

        GlobalScope.launch {
            Log.d(TAG, "start coroutines")
            delay(3000)
            runBlocking {
                delay(100)
            }
            Log.d(TAG, "end coroutines")


        }


        runBlocking {
            launch {
                testAwait()
            }
        }


    }

    fun rxJavaCode() {
        val observer = Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(10)
                emitter.onNext(100)
                emitter.onNext(1000)
                emitter.onNext(10000)
                emitter.onNext(100000)
                emitter.onComplete()
            }
        })
        observer
            .observeOn(Schedulers.newThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "subscriber $it")
            }


    }


    fun backgroundWorker() {
        val uploadWorker = OneTimeWorkRequestBuilder<UploadWorker>()
            .setInitialDelay(1, TimeUnit.SECONDS)
            .setInputData(workDataOf("key" to "item value"))
            .build()
        val state = WorkManager.getInstance(this).enqueue(uploadWorker).state
        state.observe(this, Observer {
            Log.d(TAG, "state : $state")
        })
    }


    override fun getResourcelayoutId(): Int = R.layout.activity_main

    override fun getClassViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory = MainViewModel.Factory(repo)

    fun checkLifecucleStatus() {
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            //TODO do something
        }
    }
}


