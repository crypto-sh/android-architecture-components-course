package com.laitec.mvvm.tools

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(val context: Context, params : WorkerParameters) : Worker(context,params) {

    val TAG = this::class.java.simpleName

    override fun doWork(): Result {
        //do something in background
        val value = inputData.getString("key")
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context,value,Toast.LENGTH_LONG).show()
        }
        Log.d(TAG,"value : $value")
        return Result.success()
    }

}